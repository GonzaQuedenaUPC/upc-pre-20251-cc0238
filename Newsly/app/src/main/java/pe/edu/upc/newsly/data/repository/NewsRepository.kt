package pe.edu.upc.newsly.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.newsly.data.local.NewsDao
import pe.edu.upc.newsly.data.models.NewsEntity
import pe.edu.upc.newsly.data.remote.NewsService
import pe.edu.upc.newsly.domain.models.News

class NewsRepository(private val newsService: NewsService, private val newsDao: NewsDao) {

    suspend fun findNews(description: String): List<News> = withContext(Dispatchers.IO) {

        val response = newsService.findNews(description)

        if (response.isSuccessful) {
            response.body()?.let { it ->
                return@withContext it.map { newsResponse ->
                    val news = newsResponse.toNews()
                    news.copy(
                        isFavorite = newsDao.fetchNewsByTitle(news.title).isNotEmpty()
                    )
                }
            }
        }
        return@withContext emptyList()
    }

    suspend fun insertNews(news: News) = withContext(Dispatchers.IO) {
        newsDao.insertNews(
            NewsEntity(
                title = news.title,
                author = news.author,
                description = news.description,
                poster = news.urlToImage
            )
        )

    }

    suspend fun deleteNews(news: News) = withContext(Dispatchers.IO) {
        newsDao.deleteNews(
            NewsEntity(
                title = news.title,
                author = news.author,
                description = news.description,
                poster = news.urlToImage
            )
        )

    }
}