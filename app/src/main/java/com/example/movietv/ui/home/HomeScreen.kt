package com.example.movietv.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movietv.data.remote.dto.TitleDto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory()
    ),
    onItemClick: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    var selectedTab by remember { mutableStateOf(0) } // 0 = Movies, 1 = TV
    val snackbarHostState = remember { SnackbarHostState() }

    val isRefreshing = uiState is HomeUiState.Loading

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.refresh() }
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .pullRefresh(pullRefreshState)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                ToggleTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )

                when (uiState) {

                    is HomeUiState.Loading -> {
                        ShimmerList()
                    }

                    is HomeUiState.Success -> {
                        val data =
                            if (selectedTab == 0)
                                (uiState as HomeUiState.Success).movies
                            else
                                (uiState as HomeUiState.Success).tvShows

                        TitlesList(
                            items = data,
                            onItemClick = onItemClick
                        )
                    }

                    is HomeUiState.Error -> {
                        val message = (uiState as HomeUiState.Error).message
                        LaunchedEffect(message) {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ToggleTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(selectedTabIndex = selectedTab) {
        Tab(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            text = { Text("Movies") }
        )
        Tab(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            text = { Text("TV Shows") }
        )
    }
}

@Composable
fun TitlesList(
    items: List<TitleDto>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item.id) }
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    item.year?.let {
                        Text(
                            text = "Year: $it",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(10) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(20.dp)
                    )
                }
            }
        }
    }
}
