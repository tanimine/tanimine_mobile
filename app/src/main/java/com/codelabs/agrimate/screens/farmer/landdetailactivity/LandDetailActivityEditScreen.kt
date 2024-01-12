package com.codelabs.agrimate.screens.farmer.landdetailactivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGLabelChip
import com.codelabs.agrimate.ui.components.AGTextOverview
import com.codelabs.agrimate.ui.components.AGTextWithPrefix
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.theme.Green100
import com.codelabs.agrimate.ui.theme.Red1

@Composable
fun LandDetailActivityEditScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(bottom = 0.dp),
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) },
        bottomBar = {
            ActionBottomBar(
                onHarvestFailedClick = { /*TODO*/ },
                onHarvestSuccessClick = { /*TODO*/ })
        }
    ) { paddingValues ->
        LandDetailActivityEditContent(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
        )
    }
}

@Composable
fun LandDetailActivityEditContent(modifier: Modifier = Modifier) {
    Box(modifier) {
        Column {
            Spacer(Modifier.padding(bottom = 22.dp))
            Column(
                modifier = Modifier.padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AGInputLayout(label = "Status Aktivitas Lahan") {
                    AGLabelChip(text = "Belum Panen")
                }
                AGInputLayout(label = "Komoditas") {
                    AGTextOverview(text = "Jagung", modifier = Modifier.fillMaxWidth())
                }
                AGInputLayout(label = "Luas Lahan Tanam") {
                    AGTextOverview(text = "3 Hektare", modifier = Modifier.fillMaxWidth())
                }
                AGInputLayout(label = "Tanggal Tanam") {
                    AGTextOverview(text = "23 Mei", modifier = Modifier.fillMaxWidth())
                }
                AGInputLayout(label = "Jumlah Tanam") {
                    AGTextOverview(text = "20 kg", modifier = Modifier.fillMaxWidth())
                }
                AGInputLayout(label = "Pola Tanam") {
                    AGTextOverview(text = "Persegi", modifier = Modifier.fillMaxWidth())
                }
                AGInputLayout(label = "Komoditas") {
                    AGTextWithPrefix(text = "20.000.000", prefix = "Rp")
                }
            }
            Spacer(Modifier.padding(bottom = 11.dp))
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }) {
        AGTopAppBarDefaultTitle(text = "Aktivitas Lahan")
    }
}

@Composable
private fun ActionBottomBar(
    modifier: Modifier = Modifier,
    onHarvestFailedClick: () -> Unit,
    onHarvestSuccessClick: () -> Unit,
) {
    Surface(
        modifier = modifier.windowInsetsPadding(WindowInsets(bottom = 0.dp)),
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AGButton(
                modifier = Modifier.weight(1f),
                onClick = onHarvestFailedClick,
                containerColor = Red1,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Gagal Panen",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
            AGButton(
                modifier = Modifier.weight(1f),
                onClick = onHarvestSuccessClick,
                containerColor = Green100,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Panen",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}