package com.jadikuli.cnnproject.screen.main.picture

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UploadUiState>(UploadUiState.Idle)
    val uiState: StateFlow<UploadUiState> = _uiState.asStateFlow()

    fun uploadImage(context: Context, uri: Uri) {
        viewModelScope.launch {
            try {
                _uiState.value = UploadUiState.Loading

                val inputStream = context.contentResolver.openInputStream(uri) ?: return@launch
                val image = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
                image.outputStream().use { inputStream.copyTo(it) }

                repository.uploadImage(image)

                _uiState.value = UploadUiState.Success
            } catch (e: Exception) {
                _uiState.value = UploadUiState.Error(e.message ?: "Upload failed")
            }
        }
    }

    fun resetState() {
        _uiState.value = UploadUiState.Idle
    }
}

sealed class UploadUiState {
    object Idle : UploadUiState()
    object Loading : UploadUiState()
    object Success : UploadUiState()
    data class Error(val message: String) : UploadUiState()
}
