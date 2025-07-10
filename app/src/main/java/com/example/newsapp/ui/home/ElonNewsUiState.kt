package com.example.newsapp.ui.home

import com.example.newsapp.data.ElonNewsResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val news: ElonNewsResponse? = null,
    val error: String? = null
)

