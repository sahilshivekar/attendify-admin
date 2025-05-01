package com.attendify_admin.users.presentation.student_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.utils.FileUtil
import com.attendify_admin.users.data.dto.request.RemoveStudentImageRequest
import com.attendify_admin.users.domain.use_case.GetStudentBatchesByIdUseCase
import com.attendify_admin.users.domain.use_case.GetStudentDetailsByIdUseCase
import com.attendify_admin.users.domain.use_case.GetStudentDivisionsByIdUseCase
import com.attendify_admin.users.domain.use_case.GetStudentSemestersByIdUseCase
import com.attendify_admin.users.domain.use_case.RemoveStudentImageUseCase
import com.attendify_admin.users.domain.use_case.UpdateStudentImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class StudentDetailsViewModel @Inject constructor(
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
    private val getStudentBatchesByIdUseCase: GetStudentBatchesByIdUseCase,
    private val getStudentDivisionsByIdUseCase: GetStudentDivisionsByIdUseCase,
    private val getStudentSemestersByIdUseCase: GetStudentSemestersByIdUseCase,
    private val updateStudentImageUseCase: UpdateStudentImageUseCase,
    private val removeStudentImageUseCase: RemoveStudentImageUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state = MutableStateFlow(StudentDetailsState())
        private set


    init {
        state.onEach { studentDetailsState ->
            if (studentDetailsState.studentId != null && studentDetailsState.student == null) {
                getStudent(studentDetailsState.studentId)
                getSemesters()
                getDivisions()
                getBatches()
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: StudentDetailsEvent) {
        when (event) {
            StudentDetailsEvent.DismissAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = null
                )
            }

            is StudentDetailsEvent.ShowAlertDialog -> {
                state.value = state.value.copy(
                    dialogText = event.message
                )
            }

            StudentDetailsEvent.ToggleImageDialog -> {
                state.value = state.value.copy(
                    isImageDialogVisible = !state.value.isImageDialogVisible
                )
                if (!state.value.isImageDialogVisible) {
                    state.value = state.value.copy(
                        newUploadedImageFileName = null,
                        newUploadedImageFileUri = null,
                        newUploadedImageFile = null
                    )
                }
            }

            StudentDetailsEvent.RemoveImageClicked -> {
                removeStudentImage()
            }

            is StudentDetailsEvent.StudentNewImageUploaded -> {
                event.studentImageUri?.let { studentImageUri ->
                    viewModelScope.launch {
                        val file: File? = FileUtil.getFileFromUri(context, studentImageUri)
                        Log.d("image uploaded vm", file?.name ?: "no name")
                        state.value = state.value.copy(
                            newUploadedImageFile = file,
                            newUploadedImageFileUri = studentImageUri,
                        )
                    }
                }
            }

            StudentDetailsEvent.StudentImageFetchedFromUrl -> {
                state.value = state.value.copy(
                    isImageFetchedFromUrl = true
                )
            }

            StudentDetailsEvent.UpdateStudentImageClicked -> {
                state.value.newUploadedImageFile?.let { newImageFile ->
                    updateStudentImage(newImageFile)
                }
            }
        }
    }

    fun setStudentIdAndGetStudent(studentId: Int) {
        state.value = state.value.copy(
            studentId = studentId
        )
    }

    private fun updateStudentImage(newImageFile: File) {
        updateStudentImageUseCase(
            studentId = state.value.studentId.toString(),
            studentImageFile = newImageFile
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        isUpdatingImage = false,
                        dialogText = result.message,
                        newUploadedImageFileName = null,
                        newUploadedImageFileUri = null,
                        newUploadedImageFile = null,
                        isImageDialogVisible = false
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isUpdatingImage = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isUpdatingImage = false,
                        isImageDialogVisible = false,
                        newUploadedImageFile = null,
                        newUploadedImageFileUri = null,
                        newUploadedImageFileName = null,
                        dialogText = result.message,
                        student = state.value.student?.copy(
                            studentImgUrl = result.data?.data?.studentImgUrl,
                            studentImgPublicId = result.data?.data?.studentImgPublicId
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeStudentImage() {
        removeStudentImageUseCase(
            requestBody = RemoveStudentImageRequest(id = state.value.studentId!!)
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        isRemovingImage = false,
                        dialogText = result.message,
                        newUploadedImageFileName = null,
                        newUploadedImageFileUri = null,
                        newUploadedImageFile = null,
                        isImageDialogVisible = false
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isRemovingImage = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isRemovingImage = false,
                        isImageDialogVisible = false,
                        student = state.value.student?.copy(
                            studentImgUrl = null,
                            studentImgPublicId = null
                        ),
                        dialogText = result.message,
                        newUploadedImageFileName = null,
                        newUploadedImageFileUri = null,
                        newUploadedImageFile = null
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStudent(studentId: Int) {
        getStudentDetailsByIdUseCase(studentId = studentId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        dialogText = result.message
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoadingInitialStudentDetails = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoadingInitialStudentDetails = false,
                        student = result.data?.data
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getBatches() {
        state.value.studentId?.let { studentId ->
            getStudentBatchesByIdUseCase(
                studentId = studentId,
                semesterNumber = null
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            isBatchesLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            isBatchesLoading = true
                        )
                    }

                    is Resource.Success -> {
                        state.value = state.value.copy(
                            isBatchesLoading = false,
                            studentBatches = result.data?.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getDivisions() {
        state.value.studentId?.let { studentId ->
            getStudentDivisionsByIdUseCase(
                studentId = studentId,
                semesterNumber = null
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            isDivisionsLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            isDivisionsLoading = true
                        )
                    }

                    is Resource.Success -> {
                        state.value = state.value.copy(
                            isDivisionsLoading = false,
                            studentDivisions = result.data?.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getSemesters() {
        state.value.studentId?.let { studentId ->
            getStudentSemestersByIdUseCase(studentId = studentId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            isSemestersLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            isSemestersLoading = true
                        )
                    }

                    is Resource.Success -> {
                        val semesters = result.data?.data?.map {
                            it.Semester
                        }
                        Log.d("semester", semesters.toString())
                        state.value = state.value.copy(
                            isSemestersLoading = false,
                            semesters = semesters
                        )
                        Log.d("semester", semesters.toString())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}