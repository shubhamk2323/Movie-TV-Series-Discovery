package com.example.movietv.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movietv.data.remote.dto.TitleDetailsDto

@Composable
fun DetailsScreen(
    titleId: Int,
    viewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory()
    )
){
    LaunchedEffect(titleId) {
        viewModel.loadDetails(titleId)
    }

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is DetailsUiState.Loading -> {
            DetailsShimmer()
        }

        is DetailsUiState.Success -> {
            val data = (uiState as DetailsUiState.Success).data
            DetailsContent(data)
        }

        is DetailsUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text((uiState as DetailsUiState.Error).message)
            }
        }
    }
}

@Composable
fun DetailsContent(data: TitleDetailsDto) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AsyncImage(
            model = data.poster,
            contentDescription = data.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Text(
            text = data.title,
            style = MaterialTheme.typography.headlineSmall
        )

        data.year?.let {
            Text(
                text = "Release Year: $it",
                style = MaterialTheme.typography.bodySmall
            )
        }

        data.plot_overview?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DetailsShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(24.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant
                )
        )
    }
}
