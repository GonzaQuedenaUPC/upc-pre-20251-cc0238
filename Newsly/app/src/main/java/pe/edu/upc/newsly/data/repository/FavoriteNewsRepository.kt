package pe.edu.upc.newsly.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.newsly.data.local.NewsDao
import pe.edu.upc.newsly.data.models.NewsEntity
import pe.edu.upc.newsly.domain.models.FavoriteNews

class FavoriteNewsRepository(private val newsDao: NewsDao) {

    suspend fun fetchNews(): List<FavoriteNews> = withContext(Dispatchers.IO) {

        return@withContext newsDao.fetchNews().map {
            FavoriteNews(
                author = it.author,
                title = it.title,
                poster = it.poster,
                description = it.description
            )
        }
        return@withContext emptyList()
    }

    suspend fun deleteNews(favoriteNews: FavoriteNews) = withContext(Dispatchers.IO) {
        newsDao.deleteNews(NewsEntity(
            title = favoriteNews.title,
            author = favoriteNews.author,
            poster = favoriteNews.poster,
            description = favoriteNews.description
        ))
    }
}