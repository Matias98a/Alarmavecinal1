package com.example.alarmavecinal.vecinosadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmavecinal.R
import com.example.alarmavecinal.Vecinos

class VecinosAdapter(var vecinosList : ArrayList<itemUsu>) : RecyclerView.Adapter<VecinosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VecinosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VecinosViewHolder(layoutInflater.inflate(R.layout.item_vecinos, parent, false))
    }

    override fun onBindViewHolder(holder: VecinosViewHolder, position: Int) {
        val item = vecinosList[position]
        holder.render(item)
        holder.nombre.text = item.nombre
        holder.apellido.text = item.apellido
        holder.domicilio.text = item.domicilio
        holder.numero.text = item.numero
    }

    override fun getItemCount() : Int = vecinosList.size

}