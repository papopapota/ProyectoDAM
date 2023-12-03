package com.example.proyectodam

interface OnClickListenerReceta {
    fun onClick(receta: Receta)
    fun onFavorite(receta: Receta)
    fun onDeleteReceta(receta: Receta)
}