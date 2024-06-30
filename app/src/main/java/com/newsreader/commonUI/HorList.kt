package com.newsreader.commonUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newsreader.utlitites.LocalDimensions

@Composable
fun HorList(
    modifier: Modifier,
    items: List<CatListItem>,
    onItemSelected: (item: CatListItem) -> Unit = {}
) {
    val localDimensions = LocalDimensions.current

    LazyRow(modifier = modifier) {
        items(items, key = { item -> item.id }) { item ->
            Text(
                text = item.title, modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .clickable {
                        onItemSelected(item)
                    },
                fontSize = localDimensions.largeSp16,
                fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

data class CatListItem(
    val title: String,
    val id: String,
    var isSelected: Boolean
)

@Composable
@Preview
fun HorListPreview() {
//    HorList(
//        modifier = Modifier.fillMaxWidth(),
//        items = mutableListOf<List<ListItem>>()
//    )

}