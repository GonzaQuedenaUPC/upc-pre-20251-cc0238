package pe.edu.upc.mealscompose.presentation.view

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.mealscompose.domain.model.Category
import pe.edu.upc.mealscompose.presentation.di.PresentationModule
import pe.edu.upc.mealscompose.presentation.viewmodel.CategoryListViewModel

@Preview
@Composable

fun CategoryListView(
    modifier: Modifier = Modifier,
    viewModel: CategoryListViewModel = PresentationModule.getCategoryListViewModel()
) {

    viewModel.getCategories()

    val categories = viewModel.categories.collectAsState()

    Scaffold { padding ->
        LazyColumn(modifier = modifier.padding(padding)) {
            items(categories.value) { category ->
                CategoryListItemView(category = category) { isFavorite ->
                    viewModel.toggleFavorite(isFavorite, category)

                }
            }
        }
    }

}

@Composable
fun CategoryListItemView(
    modifier: Modifier = Modifier,
    category: Category,
    onSave: (Boolean) -> Unit
) {

    val isFavorite = remember {
        mutableStateOf(category.isFavorite)
    }

    Card(modifier = modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = category.poster,
                contentDescription = null,

                )
            Column(
                modifier = modifier
                    .padding(8.dp)
                    .weight(1f),

                ) {

                Text(
                    category.name,
                    fontWeight = FontWeight.Bold
                )
                Text(category.description, maxLines = 2)

            }
            IconButton(
                onClick = {
                    isFavorite.value = !isFavorite.value
                    onSave(isFavorite.value)
                }
            ) {
                Icon(
                    if (isFavorite.value) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = null,
                    tint = if (isFavorite.value) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }

        }
    }

}