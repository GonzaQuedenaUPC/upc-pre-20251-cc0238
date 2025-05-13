package pe.edu.upc.newsly.domain.models

data class News(
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val url: String,
    val publishedAt: String,
    val content: String,
    val source: String,
    var isFavorite: Boolean = false
)
