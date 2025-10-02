package com.jadikuli.cnnproject.screen.main.profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.network.model.ProfileData
import com.jadikuli.cnnproject.repository.UserRepository
import com.jadikuli.cnnproject.screen.main.picture.UploadUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _profile = MutableStateFlow<ProfileData?>(null)
    val profile: StateFlow<ProfileData?> = _profile

    private val _uiState = MutableStateFlow<UploadUiStateProfile>(UploadUiStateProfile.Idle)
    val uiState: StateFlow<UploadUiStateProfile> = _uiState.asStateFlow()

    private val _toastEventProfile = MutableSharedFlow<String>()
    val toastEventProfile = _toastEventProfile.asSharedFlow()

    init {
        fetchProfile()
    }

    fun fetchProfile() {
        viewModelScope.launch {
            val response = userRepository.getProfile()
            if (response != null) {
                _profile.value = response
            }
        }
    }

    fun uploadProfileImage(context: Context, uri: Uri) {
        viewModelScope.launch {
            try {
                _uiState.value = UploadUiStateProfile.Loading

                val inputStream = context.contentResolver.openInputStream(uri) ?: return@launch
                val image = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
                image.outputStream().use { inputStream.copyTo(it) }

                userRepository.updateProfile(image)

                _uiState.value = UploadUiStateProfile.Success
            } catch (e: Exception) {
                _uiState.value = UploadUiStateProfile.Error(e.message ?: "Upload failed")
            }
        }
    }

    fun updateProfile(fullname: String, email: String, phoneNumber: String) {
        viewModelScope.launch {
            val success = userRepository.updateProfile(fullname, email, phoneNumber)
            if (success) {
                _toastEventProfile.emit("Update berhasil")
            } else {
                _toastEventProfile.emit("Update gagal. Coba lagi")
            }
        }
    }

    fun resetState() {
        _uiState.value = UploadUiStateProfile.Idle
    }
}

sealed class UploadUiStateProfile {
    object Idle : UploadUiStateProfile()
    object Loading : UploadUiStateProfile()
    object Success : UploadUiStateProfile()
    data class Error(val message: String) : UploadUiStateProfile()
}
