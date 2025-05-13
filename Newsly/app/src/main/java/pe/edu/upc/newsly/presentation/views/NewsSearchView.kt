package pe.edu.upc.newsly.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.newsly.domain.models.News
import pe.edu.upc.newsly.presentation.viewmodels.NewsSearchViewModel

@Composable
fun NewsSearchView(viewModel: NewsSearchViewModel) {

    val description = remember {
        mutableStateOf("")
    }

    val news = viewModel.news.collectAsState()

    Column(modifier = Modifier.fillMaxSize())
    {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = description.value,
            onValueChange = { description.value = it },
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.findNews(description.value)
                    }
                ) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        )
        LazyColumn {
            items(news.value) { item ->
                NewsListItemView(item) { isFavorite ->
                    if (isFavorite) {
                        viewModel.insertNews(item)
                    } else {
                        viewModel.deleteNews(item)

                    }
                }
            }
        }
    }
}

@Composable
fun NewsListItemView(
    news: News,
    toggleFavorite: (Boolean) -> Unit
) {
    val isFavorite = remember {
        mutableStateOf(news.isFavorite)
    }

    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            Box {
                AsyncImage(
                    model = news.urlToImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        news.isFavorite = isFavorite.value
                        toggleFavorite(isFavorite.value)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        if (isFavorite.value) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder

                        },
                        contentDescription = null
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(news.title, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(news.author)

            }
        }
    }
}