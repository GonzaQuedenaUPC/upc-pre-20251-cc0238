package pe.edu.upc.mealscompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.mealscompose.data.repository.CategoryRepository
import pe.edu.upc.mealscompose.domain.Category

class CategoryListViewModel(val categoryRepository: CategoryRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getCategories()
        }
    }

     fun toggleFavorite(isFavorite: Boolean, category: Category) {
         viewModelScope.launch {
             if (isFavorite) {
                 insertCategory(
                     category.id,
                     category.name
                 )
             } else {
                 deleteCategory(
                     category.id,
                     category.name
                 )
             }
             getCategories()
         }

    }

     fun insertCategory(id: String, name: String) {
        viewModelScope.launch {
            categoryRepository.insertCategory(id, name)
        }
    }

     fun deleteCategory(id: String, name: String) {
        viewModelScope.launch {
            categoryRepository.deleteCategory(id, name)
        }
    }
}