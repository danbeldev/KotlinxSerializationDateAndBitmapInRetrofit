package com.example.dateretrofit.date.api

import com.example.dateretrofit.date.model.Autor
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.http.GET

interface AutorApi {

    @ExperimentalSerializationApi
    @GET("/library/autors")
    suspend fun getAutor():List<Autor>
}