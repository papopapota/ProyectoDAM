package com.example.proyectodam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodam.databinding.ActivityListaProductoBinding

class ProductoAdaptador(private var productos: MutableList<Producto>,
                        private var listener: OnClickListenerProducto) :
                        RecyclerView.Adapter<ProductoAdaptador.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.activity_lista_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos.get(position)

        with(holder) {
            setListener(producto)
            binding.tvName.text = producto.name
            binding.tvPrecio.text = producto.price.toString()
        }
    }

    override fun getItemCount(): Int = productos.size
    fun add(producto: Producto) {
        productos.add(producto)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ActivityListaProductoBinding.bind(view)

        fun setListener(producto: Producto) {
            binding.root.setOnClickListener { listener.onClick(producto) }
        }
    }

}