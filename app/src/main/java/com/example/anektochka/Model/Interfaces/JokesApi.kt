package com.example.anektochka.Model.Interfaces

import com.example.anektochka.Model.Item.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApi {

    @GET("random_ten")
    suspend fun getJokes():MutableList<Post>

    @GET("jokes/{type}/random")
    suspend fun getJokeForType( @Path("type") type:String):MutableList<Post>
}