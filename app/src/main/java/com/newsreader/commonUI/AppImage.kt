package com.newsreader.commonUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.newsreader.R

@Composable
fun AppImage(
    modifier: Modifier,
    imageUrl: String
) {

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.placeholder),
        contentDescription = stringResource(R.string.description),
        contentScale = ContentScale.Crop
    )

}

@Composable
@Preview(showBackground = true)
fun AppImagePreview() {
    AppImage(
        modifier = Modifier
            .width(130.dp)
            .height(80.dp)
            .background(color = Color.LightGray)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        imageUrl = "https://ichef.bbci.co.uk/news/1024/branded_news/9111/live/73f7b260-350b-11ef-b606-993eb74348a6.jpg"
    )
}