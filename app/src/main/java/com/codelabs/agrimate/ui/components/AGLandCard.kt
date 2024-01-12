package com.codelabs.agrimate.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.theme.AgrimateTheme
import com.codelabs.agrimate.ui.theme.GreyScale200
import com.codelabs.agrimate.ui.theme.GreyScale500

@Composable
fun AGLandCard(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 1.dp,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp)) {
            Image(
                painter = painterResource(R.drawable.news_image_dummy),
                contentDescription = null,
                modifier = Modifier
                    .width(91.dp)
                    .height(98.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.padding(end = 11.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Nama Lahan", style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 27.2.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreyScale200,
                    )
                )
                Spacer(Modifier.padding(bottom = 4.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    DetailLabel(
                        label = "Kec Bayongbong",
                        icon = R.drawable.location_icon,
                        contentDescription = "lokasi"
                    )
                    DetailLabel(
                        label = "5 Hektare",
                        icon = R.drawable.land_area_icon,
                        contentDescription = "luas lahan"
                    )
                    DetailLabel(
                        label = "Aktivitas terakhir pada 23 Mei 2023",
                        icon = R.drawable.calendar_icon,
                        contentDescription = "aktivitas terakhir"
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "aksi",
                    tint = GreyScale500
                )
            }
        }
    }
}

@Composable
private fun DetailLabel(
    modifier: Modifier = Modifier,
    label: String,
    icon: Int,
    contentDescription: String?,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(16.dp),
        )
        Text(
            label, style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 21.3.sp,
                fontWeight = FontWeight.Medium,
                color = GreyScale500,
            )
        )
    }
}

@Composable
@Preview
fun AGLandCardPreview() {
    AgrimateTheme {
        AGLandCard(onClick = {})
    }
}