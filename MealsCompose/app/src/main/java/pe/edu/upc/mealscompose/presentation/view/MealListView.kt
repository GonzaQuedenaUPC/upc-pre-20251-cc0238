package pe.edu.upc.mealscompose.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.mealscompose.domain.model.Meal
import pe.edu.upc.mealscompose.presentation.di.PresentationModule
import pe.edu.upc.mealscompose.presentation.viewmodel.MealListViewModel

@Preview
@Composable
fun MealListView(
    viewModel: MealListViewModel = PresentationModule.getMealListViewModel(),
    category: String = "beef",
) {

    val meals = viewModel.meals.collectAsState()
    viewModel.getMealsByCategory(category)

    LazyColumn(modifier = Modifier.padding()) {
        items(meals.value) { meal ->
            MealListItemView(meal)
        }

    }
}

@Composable
fun MealListItemView(meal: Meal) {
    Card(modifier = Modifier.padding(8.dp)) {
        Box {
            AsyncImage(
                meal.poster,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                meal.name, color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}