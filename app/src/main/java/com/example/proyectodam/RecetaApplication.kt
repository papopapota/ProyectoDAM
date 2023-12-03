package com.example.proyectodam
import android.app.Application
import androidx.room.Room
class RecetaApplication: Application(){

    companion object{
        lateinit var database: RecetaDatabase
    }
    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(  this,
            RecetaDatabase::class.java,
                                "RecetaDatabase").build()
    }
}