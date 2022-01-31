package com.javimay.postsapp.data.model.typesconverter

import androidx.room.TypeConverter
import com.javimay.postsapp.data.model.Geo
import org.json.JSONObject

class GeoTypeConverter {
    @TypeConverter
    fun fromGeo(geo: Geo): String{
        return JSONObject().apply {
            put("idGeo", geo.idGeo)
            put("lat", geo.lat)
            put("lng", geo.lng)
        }.toString()
    }

    @TypeConverter
    fun toGeo(geo: String): Geo {
        val json = JSONObject(geo)
        return Geo(
            json.get("idGeo") as Int,
            json.get("lat") as String,
            json.get("lng") as String,
        )
    }
}