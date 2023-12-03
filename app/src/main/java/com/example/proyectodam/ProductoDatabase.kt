package com.example.proyectodam

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductoEntity::class), version = 1)
abstract class ProductoDatabase : RoomDatabase(){
    abstract fun productoDAO(): ProductoDAO
}