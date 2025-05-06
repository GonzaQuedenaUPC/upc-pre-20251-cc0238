package pe.edu.upc.mealscompose.domain.model

data class Category(

    val id: String,
    val name: String,
    val poster: String,
    val description: String,
    var isFavorite: Boolean = false
)