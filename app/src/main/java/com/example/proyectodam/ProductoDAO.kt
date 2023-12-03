package com.example.proyectodam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductoDAO {
    @Query("Select * FROM ProductoEntity")
    fun getAllProductos():MutableList<ProductoEntity>

    @Insert
    fun addProducto(productoEntity: ProductoEntity)

    @Update
    fun updateProducto(productoEntity: ProductoEntity)

    @Delete
    fun deleteProducto(productoEntity: ProductoEntity)
}