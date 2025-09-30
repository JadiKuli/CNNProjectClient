package com.jadikuli.cnnproject.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.network.model.ProfileData
import com.jadikuli.cnnproject.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _profile = MutableStateFlow<ProfileData?>(null)
    val profile: StateFlow<ProfileData?> = _profile

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val response = userRepository.getProfile()
            if (response != null) {
                _profile.value = response
            }
        }
    }
}