package pe.edu.upc.countercompose.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    var count = mutableStateOf(0)

    fun increment(){
        count.value++
    }
}