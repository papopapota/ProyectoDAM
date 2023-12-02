package com.example.proyectodam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.proyectodam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mActiveFragment: Fragment
    private lateinit var mFragmetManager: FragmentManager
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

        mBinding.bottomNavegation {
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
    }
}