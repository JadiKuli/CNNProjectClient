package com.jadikuli.cnnproject.screen.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.network.model.ArticleData
import com.jadikuli.cnnproject.network.model.HistoryData
import com.jadikuli.cnnproject.network.model.ProfileData
import com.jadikuli.cnnproject.repository.ArticleRepository
import com.jadikuli.cnnproject.repository.HistoryRepository
import com.jadikuli.cnnproject.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<ArticleData>>(emptyList())
    val articles: StateFlow<List<ArticleData>> = _articles

    private val _profile = MutableStateFlow<ProfileData?>(null)
    val profile: StateFlow<ProfileData?> = _profile

    private val _history = MutableStateFlow<HistoryData?>(null)
    val history: StateFlow<HistoryData?> = _history

    init {
        fetchArticles()
        fetchProfile()
        fetchHistory()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            val response = articleRepository.getArticles()
            if (response != null) {
                _articles.value = response
            }
        }
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val response = userRepository.getProfile()
            if (response != null) {
                _profile.value = response
            }
        }
    }

    private fun fetchHistory() {
        viewModelScope.launch {
            val response = historyRepository.getLatestHistory()
            if (response != null) {
                _history.value = response
            }
        }
    }
}
