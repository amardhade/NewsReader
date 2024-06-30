package com.newsreader.commonUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newsreader.domain.models.News
import com.newsreader.utlitites.LocalDimensions

@Composable
fun NewsItem(
    news: News,
    onItemPress: (News) -> Unit
) {

    val localDimensions = LocalDimensions.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = localDimensions.dp10)
            .clickable(onClick = { onItemPress(news) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(2f, true)
        ) {
            Text(
                modifier = Modifier
                    .widthIn(max = 250.dp),
                text = news.title ?: "",
                fontSize = localDimensions.largeSp16,
                fontWeight = FontWeight.Normal,
                softWrap = true,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(modifier = Modifier.weight(1f, true)) {
            AppImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(80.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = localDimensions.dp8,
                            topEnd = localDimensions.dp8,
                            bottomStart = localDimensions.dp8,
                            bottomEnd = localDimensions.dp8
                        )
                    ), imageUrl = news.urlToImage ?: ""
            )
        }
    }
    HorizontalDivider(
        thickness = localDimensions.dp1,
        color = Color.LightGray
    )
}


@Composable
@Preview
fun NewsPreview() {
    NewsItem(
        news = News(
            id = 1,
            title = "News Title News Title ",
            urlToImage = null
        ), onItemPress = {}
    )
}