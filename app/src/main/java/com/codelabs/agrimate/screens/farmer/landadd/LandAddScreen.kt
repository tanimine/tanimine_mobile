package com.codelabs.agrimate.screens.farmer.landadd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.components.AGInputFullAddress
import com.codelabs.agrimate.ui.components.AGInputId
import com.codelabs.agrimate.ui.components.AGInputImage
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGInputWithSuffix
import com.codelabs.agrimate.ui.components.AGSelectInputWithSearch
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.effect.GreenStatusBarEffect
import com.codelabs.agrimate.ui.theme.AgrimateTheme

@Composable
fun LandAddScreen(modifier: Modifier = Modifier, navController: NavController) {

    GreenStatusBarEffect()

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) }) { paddingValues ->
        LandAddContent(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun LandAddContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(top = 19.dp, bottom = 29.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 18.dp,
                    end = 18.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AGInputLayout(label = "Nama Lahan") {
                    AGInputId(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = "Masukkan nama lahan"
                    )
                }

                AGInputLayout(label = "Provinsi") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Provinsi",
                        placeholder = "Pilih Provinsi",
                        options = listOf()
                    )
                }

                AGInputLayout(label = "Kota") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Kota",
                        placeholder = "Pilih Kota",
                        options = listOf()
                    )
                }

                AGInputLayout(label = "Kecamatan") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Kecamatan",
                        placeholder = "Pilih Kecamatan",
                        options = listOf()
                    )
                }

                AGInputLayout(label = "Kelurahan") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Kelurahan",
                        placeholder = "Pilih Kelurahan",
                        options = listOf()
                    )
                }

                AGInputLayout(label = "Alamat lengkap") {
                    AGInputFullAddress(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = "contoh: Jl. Gatot Subroto, No. 123, RT.09/RW.01"
                    )
                }

                AGInputLayout(label = "Gambar Lahan") {
                    AGInputImage(modifier = Modifier.fillMaxWidth(), onClick = {})
                }

                AGInputLayout(label = "Luas Tanah") {
                    AGInputWithSuffix(
                        value = "",
                        onValueChange = {},
                        placeholder = "0",
                        suffix = "Hektare"
                    )
                }

                AGInputLayout(label = "Pola Tanam") {
                    AGSelectInputWithSearch(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = "Persegi",
                        placeholder = "Pilih Pola Tanam",
                        options = listOf()
                    )
                }
            }
            Box(Modifier.padding(horizontal = 18.dp)) {
                AGButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
                ) {
                    Text(text = "Simpan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }
    ) {
        AGTopAppBarDefaultTitle(text = "Tambah Lahan")
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1400)
fun LandAddContentPreview() {
    AgrimateTheme {
        LandAddContent()
    }
}