package com.codelabs.agrimate.screens.farmer.landdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGFilterButton
import com.codelabs.agrimate.ui.components.AGLandDetailCard
import com.codelabs.agrimate.ui.components.AGLandDetailFilterSheet
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.AgrimateTheme
import com.codelabs.agrimate.ui.theme.Green100
import com.codelabs.agrimate.ui.theme.GreyScale400
import com.codelabs.agrimate.ui.theme.GreyScale700
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandDetailScreen(modifier: Modifier = Modifier, navController: NavController) {
    var openFilterSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    val closeFilterSheet = {
        coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
            if (!bottomSheetState.isVisible) {
                openFilterSheet = false
            }
        }
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(bottom = 0.dp),
        topBar = {
            TopAppBar(onNavigationClick = { navController.popBackStack() })
        },
        floatingActionButton = { AddLandButton(onClick = { }) }) { paddingValues ->
        LandDetailContent(
            modifier = Modifier.padding(paddingValues),
            onFilterClick = { openFilterSheet = true },
            onLandCardClick = {
                navController.navigate(AGRoute.Farmer.Main.Land.Detail.Activity.Detail.route)
            },
        )
    }

    if (openFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { openFilterSheet = false },
            sheetState = bottomSheetState,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(bottom = 0),
            containerColor = Color.White
        ) {
            AGLandDetailFilterSheet(onClose = { closeFilterSheet() })
        }
    }
}

@Composable
fun LandDetailContent(
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit,
    onLandCardClick: (String) -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 18.dp, vertical = 19.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    HeadContent(totalArea = "5", totalAreaUsed = "4")
                    AGFilterButton(onClick = onFilterClick)
                }
            }
            repeat(10) { index ->
                item(index) {
                    AGLandDetailCard(onClick = { onLandCardClick("land-$index") })
                }
            }
        }
    }
}

@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onNavigationClick: () -> Unit) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }) {
        AGTopAppBarDefaultTitle(text = "Nama Lahan")
    }
}

@Composable
private fun HeadContent(modifier: Modifier = Modifier, totalArea: String, totalAreaUsed: String) {
    val textHighlightStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 42.12.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
    val textLabelStyle = TextStyle(
        fontSize = 11.sp,
        lineHeight = 19.3.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    )
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = GreyScale700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("$totalArea Hektare", style = textHighlightStyle, color = Green100)
                Text("Total luas", style = textLabelStyle, color = GreyScale400)
            }
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("$totalAreaUsed Hektare", style = textHighlightStyle, color = GreyScale400)
                Text("Luas terpakai", style = textLabelStyle, color = GreyScale400)
            }

        }
    }
}

@Composable
private fun AddLandButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier, shape = RoundedCornerShape(100),
        color = Color.White,
        onClick = onClick,
        shadowElevation = 4.dp
    ) {
        Box(Modifier.padding(16.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = Green100)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun LandDetailContentPreview() {
    AgrimateTheme {
        Surface(color = Color.White) {
            LandDetailContent(onFilterClick = {}, onLandCardClick = {})
        }
    }
}