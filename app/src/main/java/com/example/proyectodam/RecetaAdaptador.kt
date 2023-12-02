package com.example.proyectodam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodam.databinding.CvRecetaBinding

class RecetaAdaptador (
    private var recetas:MutableList<Receta>,
    private var listener: MainActivity
    ) :
    RecyclerView.Adapter<RecetaAdaptador.ViewHolder>() {

        private lateinit var mContext:Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            mContext = parent.context

            var view = LayoutInflater.from(mContext).inflate(R.layout.cv_receta,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var receta = recetas.get(position)
            with(holder){
                setListener(receta)
                binding.Nombre.text =receta.nombreReceta
                binding.Descripcion.text=receta.descripcion
                binding.cbLiga.isChecked=receta.favorite

            }
        }


        override fun getItemCount(): Int = recetas.size
        fun add(receta: Receta){
            recetas.add(receta)
            notifyDataSetChanged()
        }
        fun setReceta(recetas: MutableList<Receta>){
            this.recetas = recetas
            notifyDataSetChanged()
        }
        fun update(receta: Receta){
            val index = recetas.indexOf(receta)
            if (index != -1){
                recetas.set(index,receta)
                notifyItemChanged(index)
            }
        }
        fun delete(receta: Receta){
            val index = recetas.indexOf(receta)
            if (index != -1){
                recetas.removeAt(index)
                notifyItemRemoved(index)
            }
        }
        inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val binding = CvRecetaBinding.bind(view)
            fun setListener(receta: Receta){
                with(binding.root){
                    setOnClickListener { listener.onClick(receta) }
                    setOnLongClickListener {
                        listener.onDeleteReceta(receta)
                        true
                    }
                }
                binding.cbLiga.setOnClickListener {
                    listener.onFavorite(receta)
                }
            }
        }
    }