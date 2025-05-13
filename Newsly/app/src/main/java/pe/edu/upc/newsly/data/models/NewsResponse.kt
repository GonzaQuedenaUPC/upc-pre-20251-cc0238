package pe.edu.upc.newsly.data.models

import pe.edu.upc.newsly.domain.models.News

data class NewsResponse(
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String?,
    val publishedAt: String?,
    val content: String?,
    val source: SourceResponse?
) {
    fun  toNews(): News {
        return News(
            author = author ?: "",
            title = title ?: "",
            description = description ?: "",
            urlToImage = urlToImage ?: "",
            url = url ?: "",
            publishedAt = publishedAt ?: "",
            content = content ?: "",
            source = source?.name ?: "",

            )
    }
}

data class SourceResponse(
    val name: String?
)
