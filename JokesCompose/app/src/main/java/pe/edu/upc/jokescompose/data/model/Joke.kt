package pe.edu.upc.jokescompose.data.model

import com.google.gson.annotations.SerializedName

data class Joke(
    val id: String,

    @SerializedName("value")
    val content: String
)