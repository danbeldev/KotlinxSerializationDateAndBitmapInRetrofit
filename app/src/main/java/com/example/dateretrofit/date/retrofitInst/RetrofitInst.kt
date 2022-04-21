package com.example.dateretrofit.date.retrofitInst

import com.example.dateretrofit.date.api.AutorApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class RetrofitInst {

    @ExperimentalSerializationApi
    companion object{
        private val contentType = MediaType.parse("application/json")
        val apiAutor: AutorApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.cfif31.ru/")
                .addConverterFactory(Json.asConverterFactory(contentType!!))
                .build()
                .create(AutorApi::class.java)
        }
    }
}