package com.example.proyectodam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.proyectodam.databinding.ActivityMainBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectodam.databinding.ActivityRegistrarProductoBinding
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity(),OnClickListenerReceta {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdaptadorReceta: RecetaAdaptador
    val recetaDatabase = RecetaApplication.database
    private lateinit var mGrindLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        mBinding.btnSave.setOnClickListener{

            val receta = Receta(
                nombreReceta = mBinding.etNombre.text.toString().trim(),
                descripcion = mBinding.etDescripcion.text.toString().trim()
            )
            Thread {
                recetaDatabase.RecetaDAO().addreceta(receta)
            }.start()
            mAdaptadorReceta.add(receta)
        }

    }

    private fun setupRecyclerView(){
        mAdaptadorReceta = RecetaAdaptador(mutableListOf(),this)
        mGrindLayout = GridLayoutManager(this,2)
        getRecetas()

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGrindLayout
            adapter = mAdaptadorReceta
        }
    }

    private fun getRecetas() {
        val queue = LinkedBlockingQueue<MutableList<Receta>>()
        Thread{
            val Recetas = recetaDatabase.RecetaDAO().getAllreceta()
            queue.add(Recetas)
        }.start()
        mAdaptadorReceta.setReceta(queue.take())
    }

    override fun onClick(receta: Receta) {

    }

    override fun onFavorite(receta: Receta) {
        receta.favorite = !receta.favorite
        val queue = LinkedBlockingQueue<Receta>()
        Thread{
            recetaDatabase.RecetaDAO().updatereceta(receta)
            queue.add(receta)

        }.start()
        mAdaptadorReceta.update(queue.take())
    }

    override fun onDeleteReceta(receta: Receta) {
        val queue = LinkedBlockingQueue<Receta>()
        Thread {
            recetaDatabase.RecetaDAO().deletereceta(receta)
            queue.add(receta)
        }.start()
        mAdaptadorReceta.delete(queue.take())
    }
}