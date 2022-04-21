package com.example.dateretrofit.date.repository

import com.example.dateretrofit.date.api.AutorApi
import com.example.dateretrofit.date.model.Autor
import kotlinx.serialization.ExperimentalSerializationApi

class ApiAutorRepository(
    private val autorApi:AutorApi
) {

    @ExperimentalSerializationApi
    suspend fun getAutor():List<Autor> = autorApi.getAutor()
}