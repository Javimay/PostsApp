package com.javimay.postsapp.data.model.typesconverter

import androidx.room.TypeConverter
import com.javimay.postsapp.data.model.Company
import org.json.JSONObject

class CompanyTypeConverter {

    @TypeConverter
    fun fromCompany(company: Company): String{
        return JSONObject().apply {
            put("_id", company._id)
            put("bs", company.bs)
            put("catchPhrase", company.catchPhrase)
            put("name",company.name)
        }.toString()
    }

    @TypeConverter
    fun toCompany(company: String): Company {
        val json = JSONObject(company)
        return Company(
            json.get("_id") as Int,
            json.get("bs") as String,
            json.get("catchPhrase") as String,
            json.get("name") as String,
        )
    }
}