package com.example.proyectodam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductoDAO {
    @Query("Select name, price FROM producto")
    fun getAllProducto():MutableList<Producto>

    @Insert
    fun addProducto(producto: Producto)

    @Update
    fun updateProducto(producto: Producto)

    @Delete
    fun deleteProducto(producto: Producto)
}