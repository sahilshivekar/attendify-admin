package com.presencify_admin.home.feature_users.presentation.staff_details

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarAction
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.common.utils.FileUtil
import com.presencify_admin.home.feature_users.domain.use_case.GetStaffByIdUseCase
import com.presencify_admin.home.feature_users.domain.use_case.GetTeachingSubjectsUseCase
import com.presencify_admin.home.feature_users.domain.use_case.RemoveStaffImageUseCase
import com.presencify_admin.home.feature_users.domain.use_case.RemoveStaffUseCase
import com.presencify_admin.home.feature_users.domain.use_case.UpdateStaffImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
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
class StaffDetailsViewModel @Inject constructor(
    private val getStaffByIdUseCase: GetStaffByIdUseCase,
    private val updateStaffImageUseCase: UpdateStaffImageUseCase,
    private val removeStaffImageUseCase: RemoveStaffImageUseCase,
    private val removeStaffUseCase: RemoveStaffUseCase,
    private val getTeachingSubjectsUseCase: GetTeachingSubjectsUseCase,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _state = MutableStateFlow(StaffDetailsState())
    val state: StateFlow<StaffDetailsState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("staffId")?.let { staffId ->
            _state.update { it.copy(staffId = staffId) }
            getStaff(staffId)
            getAssignedCoursesOfStaffMember(staffId)
        }
    }

    fun onEvent(event: StaffDetailsEvent) {
        when (event) {
            StaffDetailsEvent.ToggleImageDialog -> {
                val newVisibility = !_state.value.isImageDialogVisible
                _state.update {
                    it.copy(
                        isImageDialogVisible = newVisibility,
                        newUploadedImageFileUri = if (!newVisibility) null else it.newUploadedImageFileUri
                    )
                }
            }

            StaffDetailsEvent.RemoveImageClicked -> removeStaffImage()

            is StaffDetailsEvent.StaffNewImageUploaded -> {
                _state.update { it.copy(newUploadedImageFileUri = event.staffImageUri) }
            }

            StaffDetailsEvent.StaffImageFetchedFromUrl -> {
                _state.update { it.copy(isImageFetchedFromUrl = true) }
            }

            StaffDetailsEvent.UpdateStaffImageClicked -> {
                val file = _state.value.newUploadedImageFileUri?.let {
                    FileUtil.getFileFromUri(context, it)
                }
                file?.let { updateStaffImage(it) }
            }

            StaffDetailsEvent.RemoveStaffClicked -> {
                viewModelScope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            message = "Are you sure you want to remove this staff member?",
                            action = SnackbarAction(
                                name = "Remove",
                                action = { removeStaff(_state.value.staffId!!) }
                            )
                        )
                    )
                }
            }
        }
    }

    private fun removeStaff(staffId: Int) {
        removeStaffUseCase(staffId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isRemovingStaffMember = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to remove staff"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isRemovingStaffMember = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(isRemovingStaffMember = false, isStaffMemberRemoved = true)
                    }
                    SnackbarController.sendEvent(SnackbarEvent("Staff member removed successfully"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAssignedCoursesOfStaffMember(staffId: Int) {
        getTeachingSubjectsUseCase(staffId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isLoadingAssignedSubjects = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to load courses assigned to staff members"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoadingAssignedSubjects = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoadingAssignedSubjects = false,
                            assignedCourses = result.data?.map { teacherTeaches ->
                                CourseData(
                                    courseId = teacherTeaches.course?.id!!,
                                    courseName = teacherTeaches.course.name,
                                    courseCode = teacherTeaches.course.code,
                                )
                            }?.toPersistentList() ?: persistentListOf()
                        )
                    }
                }

            }
        }.launchIn(viewModelScope)
    }


    private fun getStaff(staffId: Int) {
        getStaffByIdUseCase(staffId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isLoadingInitialStaffDetails = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to load staff details"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoadingInitialStaffDetails = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoadingInitialStaffDetails = false, staff = result.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateStaffImage(file: File) {
        updateStaffImageUseCase(
            staffId = _state.value.staffId!!,
            staffImageFile = file
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isUpdatingImage = false,
                            newUploadedImageFileUri = null,
                            isImageDialogVisible = false
                        )
                    }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to update image"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isUpdatingImage = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isUpdatingImage = false,
                            isImageDialogVisible = false,
                            newUploadedImageFileUri = null,
                            staff = it.staff?.copy(
                                staffImageUrl = result.data?.staffImageUrl,
                                staffImagePublicId = result.data?.staffImagePublicId
                            )
                        )
                    }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Image updated successfully"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeStaffImage() {
        removeStaffImageUseCase(
            staffId = _state.value.staffId!!
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
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to remove image"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isRemovingImage = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isRemovingImage = false,
                            isImageDialogVisible = false,
                            staff = it.staff?.copy(
                                staffImageUrl = null,
                                staffImagePublicId = null
                            ),
                            newUploadedImageFileUri = null
                        )
                    }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Image removed successfully"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}