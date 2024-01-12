package com.codelabs.agrimate.screens.farmer.planthealth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle

@Composable
fun DiseaseDetailScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(bottom = 0.dp),
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) }) { paddingValues ->
        DiseaseDetailContent(
            Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun DiseaseDetailContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(209.dp),
                painter = painterResource(id = R.drawable.news_image_dummy),
                contentDescription = "penyakit-nama-penyakit",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(bottom = 17.dp))
            Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Penyebab utama dari penyakit busuk batang jagung (Stewart's wilt) adalah bakteri Erwinia stewartii. Bakteri ini menyebar melalui pergerakan air hujan atau pun air irigasi yang terkontaminasi oleh bakteri dan menyebabkan kerusakan pada batang jagung dan daun. Bakteri ini dapat masuk ke tanaman melalui luka-luka atau kerusakan pada batang, yang disebabkan oleh pemotongan batang yang terlalu keras, kerusakan pada batang selama transportasi, atau kerusakan pada batang yang disebabkan oleh serangan hama atau penyakit lain."
                )
            }
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }) {
        AGTopAppBarDefaultTitle(text = "Nama Penyakit Tanaman")
    }
}