package pe.edu.upc.mealscompose.data.di

import androidx.room.Room
import pe.edu.upc.mealscompose.MealsApplication
import pe.edu.upc.mealscompose.data.local.AppDatabase
import pe.edu.upc.mealscompose.data.local.CategoryDao
import pe.edu.upc.mealscompose.data.remote.ApiConstants.BASE_URL
import pe.edu.upc.mealscompose.data.remote.CategoryService
import pe.edu.upc.mealscompose.data.repository.CategoryRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCategoryService(): CategoryService {
        return getRetrofit().create(CategoryService::class.java)

    }

    fun getAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            MealsApplication.instance.applicationContext,
            AppDatabase::class.java, "meals-db"
        ).build()
    }

    fun getCategoryDao(): CategoryDao {
        return getAppDatabase().categoryDao()
    }

    fun getCategoryRepository(): CategoryRepository {
        return CategoryRepository(
            getCategoryService(),
            getCategoryDao()
        )
    }
}