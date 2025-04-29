package pe.edu.upc.mealscompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    fun getCategoryService(): CategoryService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryService::class.java)

    }
}