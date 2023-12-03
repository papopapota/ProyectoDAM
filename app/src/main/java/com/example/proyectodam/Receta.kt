package com.example.proyectodam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Receta")
data class Receta(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombreReceta:String= "",
    var descripcion:String = "",
    var favorite : Boolean = false
)


