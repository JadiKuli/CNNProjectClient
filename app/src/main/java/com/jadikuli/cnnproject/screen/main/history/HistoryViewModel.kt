package com.jadikuli.cnnproject.screen.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.network.model.HistoryData
import com.jadikuli.cnnproject.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

    private val _history = MutableStateFlow<List<HistoryData?>>(emptyList())
    val history: StateFlow<List<HistoryData?>> = _history

    private val _latestHistory = MutableStateFlow<HistoryData?>(null)
    val latestHistory: StateFlow<HistoryData?> = _latestHistory

    init {
        fetchHistory()
        fetchLatestHistory()
    }

    private fun fetchHistory() {
        viewModelScope.launch {
            val response = historyRepository.getHistory()
            if (response != null) {
                _history.value = response
            }
        }
    }

    private fun fetchLatestHistory() {
        viewModelScope.launch {
            val response = historyRepository.getLatestHistory()
            if (response != null) {
                _latestHistory.value = response
            }
        }
    }
}