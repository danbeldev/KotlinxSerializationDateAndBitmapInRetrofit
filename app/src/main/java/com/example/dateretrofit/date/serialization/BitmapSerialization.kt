package com.example.dateretrofit.date.serialization

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.ByteArrayOutputStream


@ExperimentalSerializationApi
@Serializer(forClass = Bitmap::class)
internal object BitmapSerialization : KSerializer<Bitmap?> {

    override fun deserialize(decoder: Decoder): Bitmap? {
        val byteArray = Base64.decode(decoder.decodeString(), Base64.NO_WRAP)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override fun serialize(encoder: Encoder, value: Bitmap?) {
        if (value == null)
            encoder.encodeNull()
        else {
            val outputStream = ByteArrayOutputStream()
            value.compress(Bitmap.CompressFormat.JPEG, 10, outputStream)
            encoder.encodeString(outputStream.toByteArray().toString())
        }
    }
}