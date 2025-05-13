package pe.edu.upc.newsly.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.newsly.data.models.NewsEntity

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(entity: NewsEntity)

    @Delete
    suspend fun deleteNews(entity: NewsEntity)

    @Query("select * from news")
    suspend fun fetchNews(): List<NewsEntity>

    @Query("select * from news where title =:title ")
    suspend fun fetchNewsByTitle(title: String): List<NewsEntity>

}