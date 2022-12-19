package com.example.alarmavecinal.alertasadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmavecinal.R

class AlertasAdapter(var alertasList : ArrayList<ItemAlertas>) : RecyclerView.Adapter<AlertasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlertasViewHolder(layoutInflater.inflate(R.layout.item_alertas, parent, false))
    }

    override fun onBindViewHolder(holder: AlertasViewHolder, position: Int) {
        val item = alertasList[position]
        holder.renderAlerta(item)
        holder.textoTitulo.text = "${item.tipoDeAlerta}"
        holder.textoAlerta.text = "${item.nombre} ${item.apellido} -DNI ${item.dni}," +
                "domicilio: ${item.domicilio} Barrio : ${item.barrio} alerto sobre ${item.tipoDeAlerta}" +
                "dirijase a la brevedad"
    }

    override fun getItemCount(): Int = alertasList.size
}