package com.example.proyectodam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var price: Int,
    var stock: Int,
    var favorite: Boolean = false
)
