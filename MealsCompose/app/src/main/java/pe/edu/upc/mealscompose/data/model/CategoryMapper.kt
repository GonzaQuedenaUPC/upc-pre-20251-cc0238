package pe.edu.upc.mealscompose.data.model

import pe.edu.upc.mealscompose.domain.Category

object CategoryMapper {

    fun toCategory(categoryResponse: CategoryResponse): Category {
        return Category(
            id = categoryResponse.id ?: "",
            name = categoryResponse.name ?: "",
            poster = categoryResponse.poster ?: "",
            description =  categoryResponse.description ?: ""
        )
    }
}