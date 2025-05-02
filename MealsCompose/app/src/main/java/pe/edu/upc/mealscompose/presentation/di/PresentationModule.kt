package pe.edu.upc.mealscompose.presentation.di

import pe.edu.upc.mealscompose.data.di.DataModule
import pe.edu.upc.mealscompose.presentation.viewmodel.CategoryListViewModel

object PresentationModule {

    fun getCategoryListViewModel(): CategoryListViewModel {
        return CategoryListViewModel(DataModule.getCategoryRepository())
    }
}