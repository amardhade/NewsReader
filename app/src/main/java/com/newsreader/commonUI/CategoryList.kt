package com.newsreader.commonUI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoryList(
    onItemSelected: (item: CatListItem) -> Unit = {},
    categories: List<CatListItem>
) {
    HorList(
        modifier = Modifier.fillMaxWidth(),
        items = categories
    ) { selectedItem -> onItemSelected(selectedItem) }
}

@Composable
@Preview
fun CategoryListPreview() {
    CategoryList(
        categories = listOf(
            CatListItem(title = "All Category", id = "", isSelected = true),
            CatListItem(title = "Business", id = "business", isSelected = false),
            CatListItem(title = "Entertainment", id = "entertainment", isSelected = false),
            CatListItem(title = "General", id = "general", isSelected = false),
            CatListItem(title = "Health", id = "health", isSelected = false),
            CatListItem(title = "Science", id = "science", isSelected = false),
            CatListItem(title = "Sports", id = "sports", isSelected = false),
            CatListItem(title = "Technology", id = "technology", isSelected = false)
        )
    )
}