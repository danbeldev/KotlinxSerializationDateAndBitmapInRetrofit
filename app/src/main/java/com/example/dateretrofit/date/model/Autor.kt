package com.example.dateretrofit.date.model

import android.graphics.Bitmap
import com.example.dateretrofit.date.serialization.BitmapSerialization
import com.example.dateretrofit.date.serialization.DateSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import java.util.*

@ExperimentalSerializationApi
@Serializable
data class Autor(
    val id:Int,
    val firstName:String? = null,
    val lastName:String? = null,
    val middleName:String? = null,
    @Serializable(with = DateSerialization::class)
    val birthDate:Date?,
    @Serializable(with = DateSerialization::class)
    val deadDate:Date?,
    val description:String? = null,
    @Serializable(with = BitmapSerialization::class)
    val photo:Bitmap?
)