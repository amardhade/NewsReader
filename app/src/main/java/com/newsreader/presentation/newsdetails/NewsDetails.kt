package com.newsreader.presentation.newsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newsreader.commonUI.AppImage
import com.newsreader.domain.models.News
import com.newsreader.presentation.newsfeed.NewsFeedScreenEvent
import com.newsreader.utlitites.LocalDimensions

@Composable
fun NewsDetails(
    selectedNews: News,
    onEvent: (NewsFeedScreenEvent) -> Unit
) {

    val localDimensions = LocalDimensions.current
    val tintColor = if (selectedNews.isBookmarked) Color.Blue else Color.LightGray

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        AppImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = localDimensions.dp2)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = localDimensions.dp8,
                        topEnd = localDimensions.dp8,
                        bottomStart = localDimensions.dp8,
                        bottomEnd = localDimensions.dp8
                    )
                ),
            imageUrl = selectedNews.urlToImage ?: ""
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localDimensions.dp8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier
                    .padding(top = localDimensions.dp8)
                    .weight(0.8f),
                text = selectedNews.title ?: "",
                fontSize = localDimensions.largeSp16,
                color = Color.Black,

                )

            IconButton(
                onClick = {
                    onEvent(NewsFeedScreenEvent.UpdateBookmarked(selectedNews))
//                    selectedNews.isBookmarked = !selectedNews.isBookmarked
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Back",
                    tint = tintColor
                )
            }
        }
        if (!selectedNews.source.isNullOrBlank())
            Text(
                modifier = Modifier
                    .padding(localDimensions.dp4)
                    .fillMaxWidth(),
                text = "Source: ${selectedNews.source}",
                fontSize = localDimensions.defaultSp14,
                textAlign = TextAlign.End,
                color = Color.Gray
            )

        Text(
            modifier = Modifier.padding(localDimensions.dp8),
            text = selectedNews.content ?: "",
            fontSize = localDimensions.defaultSp14,
            color = Color.Black.copy(alpha = 0.7f)
        )

    }
}

@Composable
@Preview
fun NewsDetailsPreview() {
    NewsDetails(
        selectedNews = News(
            id = 1,
            source = "Times od India",
            isBookmarked = false,
            urlToImage = "https://i.insider.com/667dbca250b021b5caea1fac?width=1200&format=jpeg",
            title = "Retirement savings golden age: Pensions, real estate, stock gains - Business Insider",
            description = "For a certain cohort of retirees, retirement has meant financial stability and peace of mind.",
            content = "These companies have a long track record of stellar performance.A resurgence in the popularity of stock splits has been front and center in 2024 as a number of high-profile stocks have taken the plun… [+5321 chars]"
        ),
        onEvent = {}
    )
}