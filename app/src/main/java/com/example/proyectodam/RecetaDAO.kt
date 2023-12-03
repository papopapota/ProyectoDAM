package com.example.proyectodam
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecetaDAO {
        @Query("Select * FROM Receta")
        fun getAllreceta():MutableList<Receta>

        @Insert
        fun addreceta(receta: Receta)
        @Update
        fun  updatereceta(receta: Receta)

        @Delete
        fun deletereceta(receta: Receta)

}