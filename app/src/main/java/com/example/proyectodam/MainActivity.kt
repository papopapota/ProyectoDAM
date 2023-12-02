package com.example.proyectodam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectodam.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity() {

    private  lateinit var mBinding:ActivityMainBinding
    private  lateinit var mAdaptadorReceta: RecetaAdaptador
    private  lateinit var mGrindLayout: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            mBinding.btnSave.setOnClickListener {
                val receta = Receta(nombreReceta = mBinding.etNombre.text.toString().trim() ,descripcion = mBinding.etDescripcion.text.toString().trim() )
                Thread{
                    RecetaApplication.database.RecetaDAO().addreceta(receta)

                }.start()
                mAdaptadorReceta.add(receta)

            }
            setupRecyclerView()
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
            val jugadores = RecetaApplication.database.RecetaDAO().getAllreceta()
            queue.add(jugadores)
        }.start()
        mAdaptadorReceta.setReceta(queue.take())
    }

    fun onClick(receta: Receta) {

    }
    fun onDeleteJugador(receta: Receta){
        val queue = LinkedBlockingQueue<Receta>()
        Thread{
            RecetaApplication.database.RecetaDAO().deletereceta(receta)
            queue.add(receta)

        }.start()
        mAdaptadorReceta.delete(queue.take())
    }
}