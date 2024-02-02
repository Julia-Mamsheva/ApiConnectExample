package com.example.apiconnect.api

import com.example.apiconnect.model.RickAndMorty
import retrofit2.http.GET

interface Api {
    @GET("character")
    suspend fun getCharacter(): RickAndMorty

    companion object {
        val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}