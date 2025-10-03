package com.jadikuli.cnnproject.screen.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.data.store.UserDataStore
import com.jadikuli.cnnproject.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataStore: UserDataStore
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000)

            dataStore.getToken().collect { token ->
                _isLoggedIn.value = !token.isNullOrEmpty()
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _toastEvent.emit("Tunggu Sebentar...")

            val token = repository.login(email, password)
            if (token != null) {
                dataStore.saveToken(token)
                _isLoggedIn.value = true
                _toastEvent.emit("Login berhasil")
            } else {
                _toastEvent.emit("Login gagal")
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _toastEvent.emit("Tunggu Sebentar...")

            val success = repository.register(name, email, password)
            if (success) {
                _toastEvent.emit("Registrasi berhasil")
                login(email, password)
            } else {
                _toastEvent.emit("Registrasi gagal, silakan coba lagi")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStore.clearToken()
            _isLoggedIn.value = false
        }
    }
}
