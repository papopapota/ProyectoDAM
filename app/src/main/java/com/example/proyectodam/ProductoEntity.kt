package com.example.proyectodam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductoEntity")
data class ProductoEntity(@PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre: String = "",
    var precio: Int = 0,
    var stock: Int = 0,
    var favorite : Boolean = false
)



