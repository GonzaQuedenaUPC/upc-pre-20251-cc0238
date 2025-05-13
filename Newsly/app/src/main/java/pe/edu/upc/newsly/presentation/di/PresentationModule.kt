package pe.edu.upc.newsly.presentation.di

import pe.edu.upc.newsly.data.di.DataModule
import pe.edu.upc.newsly.presentation.viewmodels.FavoriteNewsListViewModel
import pe.edu.upc.newsly.presentation.viewmodels.NewsSearchViewModel

object PresentationModule {

    fun getNewsSearchViewModel(): NewsSearchViewModel {
        return NewsSearchViewModel(DataModule.getNewsRepository())
    }

    fun getFavoriteNewsListViewModel(): FavoriteNewsListViewModel {
        return FavoriteNewsListViewModel(DataModule.getFavoriteNewsRepository())
    }
}