package pe.edu.upc.todocompose.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.edu.upc.todocompose.domain.model.Task

@Preview
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<Task> = emptyList(),
    onAdd: () -> Unit = {},
    onDelete: (Int) -> Unit = {}
) {


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAdd()
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }

    ) { padding ->
        LazyColumn(modifier = modifier.padding(padding)) {
            items(tasks) { task ->
                Row {
                    Text(task.title)
                    IconButton(
                        onClick = {
                            onDelete(task.id)
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
    }

}
