package com.codelabs.agrimate.screens.farmer.landdetailactivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.components.AGInputDate
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGInputWithPrefix
import com.codelabs.agrimate.ui.components.AGInputWithSuffix
import com.codelabs.agrimate.ui.components.AGSelectInputWithSearch
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle

@Composable
fun LandDetailActivityAddScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(bottom = 0),
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) }
    ) { paddingValues ->
        LandDetailActivityAddContent(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
        )
    }
}

@Composable
fun LandDetailActivityAddContent(modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(modifier = Modifier) {
            Spacer(Modifier.padding(bottom = 22.dp))
            Column(
                modifier = Modifier.padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AGInputLayout(label = "Komoditas") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Komoditas",
                        placeholder = "Pilih Komoditas",
                        options = listOf()
                    )
                }
                AGInputLayout(label = "Luas Lahan Tanam") {
                    AGInputWithSuffix(
                        value = "",
                        onValueChange = {},
                        placeholder = "0",
                        suffix = "Hektare"
                    )
                }
                AGInputLayout(label = "Tanggal Tanam") {
                    AGInputDate(value = null, onValueChange = {})
                }
                AGInputLayout(label = "Jumlah Tanam") {
                    AGInputWithSuffix(
                        value = "",
                        onValueChange = {},
                        placeholder = "0",
                        suffix = "kg"
                    )
                }
                AGInputLayout(label = "Pola Tanam") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Pola Tanam",
                        placeholder = "Pilih Pola Tanam",
                        options = listOf()
                    )
                }
                AGInputLayout(label = "Biaya Produksi") {
                    AGInputWithPrefix(
                        value = "",
                        onValueChange = {},
                        placeholder = "0",
                        prefix = "Rp"
                    )
                }
            }
            Spacer(Modifier.padding(bottom = 24.dp))
            Box(Modifier.padding(horizontal = 18.dp)) {
                AGButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Simpan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.padding(bottom = 30.dp))
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }) {
        AGTopAppBarDefaultTitle(text = "Tambah Aktivitas Lahan")
    }
}