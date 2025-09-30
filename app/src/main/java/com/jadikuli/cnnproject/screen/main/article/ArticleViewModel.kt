package com.jadikuli.cnnproject.screen.main.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadikuli.cnnproject.network.model.ArticleDetailData
import com.jadikuli.cnnproject.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _article = MutableStateFlow<ArticleDetailData?>(null)
    val article: StateFlow<ArticleDetailData?> = _article

    fun fetchArticle(id: Int) {
        viewModelScope.launch {
            val data = repository.getArticleDetail(id)
            _article.value = data
        }
    }
}
