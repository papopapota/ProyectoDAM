package com.example.proyectodam

interface OnClickListenerProducto {

    fun onClick (productoEntity: ProductoEntity)
    fun onFavoriteProducto(productoEntity: ProductoEntity)
    fun onDeleteProducto(productoEntity: ProductoEntity)

}