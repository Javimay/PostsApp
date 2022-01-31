package com.javimay.postsapp.data.model.typesconverter

import androidx.room.TypeConverter
import com.javimay.postsapp.data.model.Address
import org.json.JSONObject

class AddressTypeConverter {
    @TypeConverter
    fun fromAddress(address: Address): String{
        return JSONObject().apply {
            put("_id", address._id)
            put("city", address.city)
//            put("geo", address.geo)
            put("street",address.street)
            put("suite",address.suite)
            put("zipcode",address.zipcode)
        }.toString()
    }

    @TypeConverter
    fun toAddress(address: String): Address {
        val json = JSONObject(address)
        return Address(
            json.get("_id") as Int,
            json.get("city") as String,
//            json.get("geo") as Geo,
            json.get("street") as String,
            json.get("suite") as String,
            json.get("zipcode") as String
        )
    }
}