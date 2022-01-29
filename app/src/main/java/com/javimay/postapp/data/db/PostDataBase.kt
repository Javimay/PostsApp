package com.javimay.postsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.javimay.postsapp.data.model.*
import com.javimay.postsapp.data.model.typesconverter.AddressTypeConverter
import com.javimay.postsapp.data.model.typesconverter.CompanyTypeConverter

@Database(
    entities = [
        User::class,
        Post::class,
        Address::class,
        Company::class,
        Geo::class,
        Comment::class],
    version = 1,
    exportSchema = false)
@TypeConverters(
    AddressTypeConverter::class,
    CompanyTypeConverter::class,
    /*GeoTypeConverter::class*/)
abstract class PostDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}