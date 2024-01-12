package com.codelabs.agrimate.screens.farmer.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGInputSearch
import com.codelabs.agrimate.ui.components.AGNewsCard
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.effect.GreenStatusBarEffect
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.AgrimateTheme

@Composable
fun NewsScreen(modifier: Modifier = Modifier, navController: NavController) {
    GreenStatusBarEffect()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(onNavigationClick = {
                navController.popBackStack()
            })
        },
        contentWindowInsets = WindowInsets(bottom = 0.dp)
    ) { paddingValues ->
        NewsContent(
            modifier = Modifier.padding(paddingValues),
            navigateToDetail = { navController.navigate(AGRoute.Farmer.Main.News.Detail.route) }
        )
    }
}

@Composable
private fun NewsContent(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier.padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 18.dp,
                top = 24.dp
            )
        ) {
            AGInputSearch(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                placeholder = "Cari berdasarkan desa"
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp, bottom = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(10) {
                AGNewsCard(onClick = {
                    navigateToDetail("asda")
                })
            }
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }
    ) {
        AGTopAppBarDefaultTitle(text = "Berita")
    }
}

@Composable
@Preview
fun NewsScreenPreview() {
    AgrimateTheme {
        NewsContent(navigateToDetail = {})
    }
}