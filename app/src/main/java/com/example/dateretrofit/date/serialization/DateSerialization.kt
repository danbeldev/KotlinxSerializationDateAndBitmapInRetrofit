package com.example.dateretrofit.date.serialization

import android.annotation.SuppressLint
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalSerializationApi
@Serializer(forClass = Date::class)
internal object DateSerialization : KSerializer<Date?> {

    @SuppressLint("SimpleDateFormat")
    private val df = SimpleDateFormat("dd-MM-yyyy")

    override fun deserialize(decoder: Decoder): Date? {
        return df.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Date?) {
        if (value == null){
            encoder.encodeNull()
        }else{
            encoder.encodeString(df.format(value))
        }
    }
}