package com.codelabs.agrimate.screens.farmer.landdetailactivity

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGFilledCardWithAction
import com.codelabs.agrimate.ui.components.AGInputBox
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGLabelChip
import com.codelabs.agrimate.ui.components.AGTab
import com.codelabs.agrimate.ui.components.AGTabBar
import com.codelabs.agrimate.ui.components.AGTextOverview
import com.codelabs.agrimate.ui.components.AGTextWithPrefix
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.theme.GreyScale500

private val mainContentTabTitles = listOf("Detail Aktivitas", "Aktivitas", "Riwayat Laporan")
private val detailActivityTabTitles = listOf("Tanam", "Panen")

private enum class MainContentScreen {
    DETAIL_ACTIVITY, ACTIVITY, REPORT_HISTORY
}

private enum class DetailActivityContentScreen {
    PLAN, HARVEST
}

@Composable
fun LandDetailActivityScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(bottom = 0.dp),
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) }) { paddingValues ->
        LandDetailActivityContent(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
        )
    }
}

@Composable
fun LandDetailActivityContent(modifier: Modifier = Modifier) {
    val tabBarValues = MainContentScreen.values()
    val selectedTab = remember { mutableStateOf(MainContentScreen.DETAIL_ACTIVITY) }
    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(Modifier.padding(top = 8.dp, bottom = 10.dp, start = 18.dp, end = 18.dp)) {
                AGTabBar {
                    AGTab(
                        titles = mainContentTabTitles,
                        enumValues = tabBarValues,
                        tabSelected = selectedTab.value,
                        onTabSelected = { newTab ->
                            selectedTab.value = newTab
                        })
                }
            }
            Crossfade(targetState = selectedTab.value, label = "main-content") { targetState ->
                when (targetState) {
                    MainContentScreen.DETAIL_ACTIVITY -> {
                        DetailActivityContent()
                    }

                    MainContentScreen.ACTIVITY -> {
                        ActivityContent()
                    }

                    MainContentScreen.REPORT_HISTORY -> {
                        ReportHistoryContent()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReportHistoryContent() {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AGInputBox(text = "Laporkan Masalah", onClick = {}, modifier = Modifier.fillMaxWidth())
        repeat(10) { index ->
            key(index) {
                AGFilledCardWithAction(
                    modifier = Modifier,
                    title = "Bencana Alam",
                    description = "diberikan pada 23 Mei 2023 | 08.00 WIB ",
                    trailing = {
                        AGLabelChip(
                            text = "Dalam Review",
                            compact = true,
                            shape = RoundedCornerShape(8.dp)
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ActivityContent() {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AGInputBox(text = "Tambah Aktifitas", onClick = {}, modifier = Modifier.fillMaxWidth())
        repeat(10) { index ->
            key(index) {
                AGFilledCardWithAction(
                    modifier = Modifier,
                    title = "Pemberian Pupuk",
                    description = "diberikan pada 23 Mei 2023 | 08.00 WIB ",
                    trailing = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "aksi",
                                tint = GreyScale500
                            )
                        }
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun DetailActivityContent() {
    val tabBarValue = DetailActivityContentScreen.values()
    val selectedTab = remember { mutableStateOf(DetailActivityContentScreen.PLAN) }

    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AGInputLayout(label = "Status Aktivitas Lahan") {
            AGLabelChip(text = "Panen")
        }
        AGInputLayout(label = "Komoditas") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "Jagung")
        }
        AGInputLayout(label = "Luas Lahan Tanam") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "3 Hetare")
        }
        AGTabBar {
            AGTab(
                titles = detailActivityTabTitles,
                enumValues = tabBarValue,
                tabSelected = selectedTab.value,
                onTabSelected = { newTab ->
                    selectedTab.value = newTab
                })
        }
        AnimatedContent(
            targetState = selectedTab.value,
            label = "land-activity-content-type",
        ) { targetState ->
            when (targetState) {
                DetailActivityContentScreen.PLAN -> {
                    LandPlanContent()
                }

                DetailActivityContentScreen.HARVEST -> {
                    LandHarvestContent()
                }
            }
        }
    }
}

@Composable
private fun LandPlanContent() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        AGInputLayout(label = "Tanggal Tanam") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "23 Mei 2023")
        }

        AGInputLayout(label = "Jumlah Tanam") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "20 kg")
        }

        AGInputLayout(label = "Pola Tanam") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "Persegi")
        }

        AGInputLayout(label = "Biaya Produksi") {
            AGTextWithPrefix(modifier = Modifier.fillMaxWidth(), prefix = "Rp", text = "20.000.000")
        }
    }
}

@Composable
private fun LandHarvestContent() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        AGInputLayout(label = "Tanggal Panen") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "23 November 2023")
        }

        AGInputLayout(label = "Jumlah Panen") {
            AGTextOverview(modifier = Modifier.fillMaxWidth(), text = "100 kg")
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(modifier = modifier, navigationIcon = {
        AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick)
    }) {
        AGTopAppBarDefaultTitle(text = "Aktivitas Lahan")
    }
}