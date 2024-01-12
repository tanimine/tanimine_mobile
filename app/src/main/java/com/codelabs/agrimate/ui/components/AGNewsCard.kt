package com.codelabs.agrimate.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.theme.GreyScale200
import com.codelabs.agrimate.ui.theme.GreyScale500

@Composable
fun AGNewsCard(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 18.dp, spotColor = Color(0x08000000)),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        onClick = { onClick("id") }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                modifier = Modifier
                    .width(121.dp)
                    .height(113.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(R.drawable.news_image_dummy),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Title",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 27.2.sp,
                    color = GreyScale200,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Sumber",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 21.3.sp,
                        color = GreyScale500
                    )
                    Text(
                        text = "Tanggal Publis",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 21.3.sp,
                        color = GreyScale500
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun AGNewsCardPreview() {
    AGNewsCard(modifier = Modifier.background(Color.White), onClick = {})
}