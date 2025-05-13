package pe.edu.upc.newsly.data.di

import androidx.room.Room
import pe.edu.upc.newsly.NewslyApplication
import pe.edu.upc.newsly.data.local.AppDatabase
import pe.edu.upc.newsly.data.local.NewsDao
import pe.edu.upc.newsly.data.remote.ApiConstants
import pe.edu.upc.newsly.data.remote.NewsService
import pe.edu.upc.newsly.data.repository.FavoriteNewsRepository
import pe.edu.upc.newsly.data.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNewsService(): NewsService {
        return getRetrofit().create(NewsService::class.java)
    }

    fun getAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            NewslyApplication.instance.applicationContext,
            AppDatabase::class.java,
            "news-db"
        ).build()
    }

    fun getNewsDao(): NewsDao {
        return getAppDatabase().newsDao()
    }

    fun getNewsRepository(): NewsRepository {
        return NewsRepository(getNewsService(), getNewsDao())
    }

    fun getFavoriteNewsRepository(): FavoriteNewsRepository {
        return FavoriteNewsRepository(getNewsDao())
    }
}