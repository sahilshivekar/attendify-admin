package com.attendify_admin.home.feature_users.presentation.student_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarAction
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.common.utils.FileUtil
import com.attendify_admin.home.feature_users.domain.use_case.GetDropoutDetailsOfStudentUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentBatchesByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDivisionsByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentSemestersByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.RemoveStudentImageUseCase
import com.attendify_admin.home.feature_users.domain.use_case.RemoveStudentUseCase
import com.attendify_admin.home.feature_users.domain.use_case.UpdateStudentImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
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
    private val removeStudentUseCase: RemoveStudentUseCase,
    private val getDropoutDetailsOfStudentUseCase: GetDropoutDetailsOfStudentUseCase,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _state = MutableStateFlow(StudentDetailsState())
    val state: StateFlow<StudentDetailsState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("studentId")?.let { studentId ->
            _state.update {
                it.copy(studentId = studentId)
            }
            if (_state.value.studentId != null) {
                getStudent(studentId)
                getSemesters()
                getDivisions()
                getBatches()
                getStudentDropoutDetails(studentId)
            }
        }

        _state.onEach { studentDetailsState ->
            if (studentDetailsState.studentId != null && studentDetailsState.student == null) {
                getStudent(studentDetailsState.studentId)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: StudentDetailsEvent) {
        when (event) {
            StudentDetailsEvent.ToggleImageDialog -> {
                val newVisibility = !state.value.isImageDialogVisible
                _state.update {
                    it.copy(
                        isImageDialogVisible = newVisibility,
                    )
                }
                if (newVisibility == false) {
                    _state.update {
                        it.copy(
                            newUploadedImageFileUri = null
                        )
                    }
                }
            }

            StudentDetailsEvent.RemoveImageClicked -> {
                removeStudentImage()
            }

            is StudentDetailsEvent.StudentNewImageUploaded -> {
                event.studentImageUri?.let { studentImageUri ->
                    viewModelScope.launch {
                        _state.update {
                            it.copy(
                                newUploadedImageFileUri = studentImageUri,
                            )
                        }
                    }
                }
            }

            StudentDetailsEvent.StudentImageFetchedFromUrl -> {
                _state.update {
                    it.copy(
                        isImageFetchedFromUrl = true
                    )
                }
            }

            StudentDetailsEvent.UpdateStudentImageClicked -> {
                var file: File? = null
                // Access value from _state
                _state.value.newUploadedImageFileUri?.let { uri ->
                    file = FileUtil.getFileFromUri(context, uri)
                }
                file?.let {
                    updateStudentImage(it)
                }
            }

            StudentDetailsEvent.RemoveStudentClicked -> {
                viewModelScope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Are you sure you want to remove this student?",
                            action = SnackbarAction(
                                name = "Remove",
                                action = { removeStudent(_state.value.studentId!!) }
                            )
                        )
                    )
                }
            }
        }
    }

    private fun getStudentDropoutDetails(studentId: Int){
        getDropoutDetailsOfStudentUseCase(studentId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(areDropoutDetailsLoading = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            dropoutDetails = result.data.orEmpty().toImmutableList(),
                            areDropoutDetailsLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update { it.copy(areDropoutDetailsLoading = false) }

                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching dropout details"
                            )
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun removeStudent(studentId: Int) {
        removeStudentUseCase(studentId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isRemovingStudent = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to remove staff"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isRemovingStudent = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(isRemovingStudent = false, isStudentRemoved = true)
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Student removed successfully"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateStudentImage(newImageFile: File) {
        // Access value from _state
        updateStudentImageUseCase(
            studentId = _state.value.student?.id!!,
            studentImageFile = newImageFile
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isStudentImageFileUploading = false,
                            newUploadedImageFileUri = null,
                            isImageDialogVisible = false
                        )
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Failed to update image"
                            )
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isStudentImageFileUploading = true)
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isStudentImageFileUploading = false,
                            isImageDialogVisible = false,
                            newUploadedImageFileUri = null,
                            student = it.student?.copy( // Access value from _state for 'it.student'
                                studentImgUrl = result.data?.studentImgUrl,
                                studentImgPublicId = result.data?.studentImgPublicId
                            )
                        )
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Image updated successfully"
                            )
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeStudentImage() {
        removeStudentImageUseCase(
            _state.value.student?.id!!
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isRemovingImage = false,
                            newUploadedImageFileUri = null,
                            isImageDialogVisible = false
                        )
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Failed to remove image"
                            )
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isRemovingImage = true)
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isRemovingImage = false,
                            isImageDialogVisible = false,
                            student = it.student?.copy( // Access value from _state for 'it.student'
                                studentImgUrl = null,
                                studentImgPublicId = null
                            ),
                            newUploadedImageFileUri = null,
                        )
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Image removed successfully"
                            )
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStudent(studentId: Int) {
        getStudentDetailsByIdUseCase(studentId = studentId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoadingInitialStudentDetails = false)
                    }
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Failed to load student details"
                            )
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoadingInitialStudentDetails = true)
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoadingInitialStudentDetails = false,
                            student = result.data
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBatches() {
        // Access value from _state
        _state.value.studentId?.let { studentId ->
            getStudentBatchesByIdUseCase(
                studentId = studentId,
                semesterNumber = null // Consider if this should be dynamic from state
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isBatchesLoading = false)
                        }
                        // Optionally show snackbar for batch loading errors
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "Failed to load batches"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isBatchesLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isBatchesLoading = false,
                                studentBatches = result.data
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getDivisions() {
        // Access value from _state
        _state.value.studentId?.let { studentId ->
            getStudentDivisionsByIdUseCase(
                studentId = studentId,
                semesterNumber = null // Consider if this should be dynamic from state
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isDivisionsLoading = false)
                        }
                        // Optionally show snackbar for division loading errors
                        // viewModelScope.launch { SnackbarController.sendEvent(SnackbarEvent(message = result.message ?: "Failed to load divisions")) }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isDivisionsLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isDivisionsLoading = false,
                                studentDivisions = result.data
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getSemesters() {
        // Access value from _state
        _state.value.studentId?.let { studentId ->
            getStudentSemestersByIdUseCase(studentId = studentId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isSemestersLoading = false)
                        }
                        // Optionally show snackbar for semester loading errors
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "Failed to load semesters"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isSemestersLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        val semesters = result.data?.map {
                            it.semester
                        }
                        Log.d("semester", semesters.toString())
                        _state.update {
                            it.copy(
                                isSemestersLoading = false,
                                semesters = semesters
                            )
                        }
                        Log.d(
                            "semester_after_update",
                            _state.value.semesters.toString()
                        ) // Log after update
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}