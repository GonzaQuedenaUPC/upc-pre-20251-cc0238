package pe.edu.upc.jokescompose.data.remote

import pe.edu.upc.jokescompose.data.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface JokeService {

    @GET("random")
    fun getRandomJoke(): Call<Joke>
}