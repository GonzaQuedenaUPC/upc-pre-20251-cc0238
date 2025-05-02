package pe.edu.upc.mealscompose.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.mealscompose.data.local.CategoryDao
import pe.edu.upc.mealscompose.data.model.CategoryEntity
import pe.edu.upc.mealscompose.data.model.CategoryResponse
import pe.edu.upc.mealscompose.data.remote.CategoryService

class CategoryRepository(
    val categoryService: CategoryService,
    val categoryDao: CategoryDao
) {

    suspend fun insertCategory(id: String, name: String) = withContext(Dispatchers.IO) {
        categoryDao.insertCategory(CategoryEntity(id, name))
    }

    suspend fun deleteCategory(id: String, name: String) = withContext(Dispatchers.IO) {
        categoryDao.deleteCategory(CategoryEntity(id, name))
    }

    suspend fun fetchCategories(): List<CategoryEntity> {
        return categoryDao.fetchCategories()
    }

    suspend fun getCategories(): List<CategoryResponse> = withContext(Dispatchers.IO) {
        val response = categoryService.getCategories()
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return@withContext it.categories
            }
        }
        return@withContext emptyList()
    }
}