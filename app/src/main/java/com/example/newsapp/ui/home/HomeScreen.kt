package com.example.newsapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ElonNewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            if (uiState.news == null || uiState.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.news?.title ?: "No news available",
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }

            AsyncImage(
                model = uiState.news?.urlImage,
                contentDescription = uiState.news?.title,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Title: ")
                    }
                    append(uiState.news?.title)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Source: ")
                    }
                    append(uiState.news?.source)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Description: ")
                    }
                    append(uiState.news?.description)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Publish Date: ")
                    }
                    append(uiState.news?.publishDate)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            )
        }

        Button(
            onClick = {
                viewModel.fetchElonNews()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color.Blue,
                contentColor = androidx.compose.ui.graphics.Color.White
            ),
            modifier = modifier
                .padding(bottom = 40.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Next Article")
        }
    }
}
