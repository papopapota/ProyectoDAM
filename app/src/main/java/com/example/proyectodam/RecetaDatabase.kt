package com.example.proyectodam
import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(entities = arrayOf(Receta::class) , version = 1)
    abstract class RecetaDatabase:RoomDatabase() {
        abstract fun RecetaDAO():RecetaDAO;
    }
