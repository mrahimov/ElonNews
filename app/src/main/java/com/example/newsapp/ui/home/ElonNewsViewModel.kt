package com.example.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.source.ElonNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@HiltViewModel
class ElonNewsViewModel @Inject constructor(
    private val repository: ElonNewsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchElonNews()
    }

    fun fetchElonNews() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            val result = repository.getElonNews()

            if (result.isSuccess && result.getOrNull() != null) {
                val date = formatPublishedDate(result.getOrNull()!!.publishDate)
                result.getOrNull()?.publishDate = date
                _uiState.value = HomeUiState(news = result.getOrNull())
            } else {
                _uiState.value = HomeUiState(error = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun formatPublishedDate(rawDate: String): String {
        return try {
            if(rawDate.isNullOrBlank()) return ""
            val zonedDate = ZonedDateTime.parse(rawDate)
            val formattedDate = DateTimeFormatter.ofPattern("MMMM, d, yyyy, h:mm a")
            zonedDate.format(formattedDate)
        } catch (e: Exception) {
            rawDate
        }
    }
}