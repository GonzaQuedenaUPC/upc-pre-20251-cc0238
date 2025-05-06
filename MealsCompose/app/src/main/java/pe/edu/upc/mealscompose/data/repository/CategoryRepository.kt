package pe.edu.upc.mealscompose.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.mealscompose.data.local.CategoryDao
import pe.edu.upc.mealscompose.data.model.CategoryEntity
import pe.edu.upc.mealscompose.data.model.CategoryMapper
import pe.edu.upc.mealscompose.data.remote.CategoryService
import pe.edu.upc.mealscompose.domain.model.Category

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

    suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        val response = categoryService.getCategories()
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return@withContext  it.categories.map { categoryResponse ->
                    val category = CategoryMapper.toCategory(categoryResponse)
                    category.copy(
                        isFavorite =  categoryDao.isFavorite(category.id).isNotEmpty()
                    )
                }



            }
        }
        return@withContext emptyList()
    }
}