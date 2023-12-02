package com.example.proyectodam
import androidx.room.Database
import androidx.room.RoomDatabase

abstract class RecetaDatabase {

    @Database(entities = arrayOf(Receta::class) , version = 1)
    abstract class recetaDatabase:RoomDatabase() {
        abstract fun recetaDAO():RecetaDAO;
    }
}