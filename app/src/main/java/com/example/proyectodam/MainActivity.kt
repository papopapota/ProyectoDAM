package com.example.proyectodam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.proyectodam.databinding.ActivityMainBinding
import androidx.recyclerview.widget.GridLayoutManager
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity(),OnClickListenerReceta, OnClickListenerProducto {

    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mActiveFragment: Fragment
    private lateinit var mFragmetManager: FragmentManager

    private lateinit var mAdaptadorReceta: RecetaAdaptador
    private lateinit var mAdaptadorProducto: ProductoAdaptador
    private lateinit var mGrindLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setContentView(R.layout.activity_main)

setupBottomNavigation()

    }

    private fun setupBottomNavigation(){
        val fragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val perfil=Perfil()
        val refrigera= Refrigera()
        val producto=Producto()
        val exit=Exit()
        mActiveFragment= homeFragment

        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, perfil,Perfil::class.java.name).hide(perfil).commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, refrigera,Refrigera::class.java.name).hide(refrigera).commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment,HomeFragment::class.java.name).hide(homeFragment).commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, producto,Producto::class.java.name).hide(producto).commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, exit,Exit::class.java.name).hide(exit).commit()

        mBinding.bottomNavegation.setOnNavigationItemReselectedListener{
            when(it.itemId){
                R.id.action_profile -> {
                    mFragmetManager.beginTransaction().hide(mActiveFragment).show(perfil).commit()
                    mActiveFragment = perfil
                    true
                }
                R.id.action_refrigerator -> {
                    mFragmetManager.beginTransaction().hide(mActiveFragment).show(refrigera).commit()
                    mActiveFragment = refrigera
                    true
                }
                R.id.action_home-> {
                    mFragmetManager.beginTransaction().hide(mActiveFragment).show(homeFragment).commit()
                    mActiveFragment = homeFragment
                    true
                }
                R.id.action_compra -> {
                    mFragmetManager.beginTransaction().hide(mActiveFragment).show(producto).commit()
                    mActiveFragment = producto
                    true
                }
                R.id.action_exit -> {
                    mFragmetManager.beginTransaction().hide(mActiveFragment).show(exit).commit()
                    mActiveFragment = exit
                    true
                }
                else -> false
            }
        }

        mBinding.btnSave.setOnClickListener {
            val receta = Receta(nombreReceta = mBinding.etNombre.text.toString().trim() ,descripcion = mBinding.etDescripcion.text.toString().trim() )
            Thread{
                RecetaApplication.database.RecetaDAO().addreceta(receta)

            }.start()
            mAdaptadorReceta.add(receta)

        }
        setupRecyclerView()
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
            val Recetas = RecetaApplication.database.RecetaDAO().getAllreceta()
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
            RecetaApplication.database.RecetaDAO().updatereceta(receta)
            queue.add(receta)

        }.start()
        mAdaptadorReceta.update(queue.take())
    }

    override fun onDeleteReceta(receta: Receta) {
        val queue = LinkedBlockingQueue<Receta>()
        Thread {
            RecetaApplication.database.RecetaDAO().deletereceta(receta)
            queue.add(receta)
        }.start()
        mAdaptadorReceta.delete(queue.take())
    }

    override fun onClick(producto: Producto) {

    }
}