package com.example.proyectodam
import androidx.room.Room
class RecetaApplication {

    companion object{
        lateinit var database: RecetaDatabase
    }
    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(this,
            RecetaDatabase::class.java,
            "recetaDatabase").build()
    }
}