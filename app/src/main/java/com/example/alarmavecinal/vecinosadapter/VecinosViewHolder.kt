package com.example.alarmavecinal.vecinosadapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmavecinal.R
import com.example.alarmavecinal.Vecinos
import org.w3c.dom.Text

class VecinosViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val nombre = view.findViewById<TextView>(R.id.txtNombreV)
    val apellido = view.findViewById<TextView>(R.id.txtApelliidoV)
    val domicilio = view.findViewById<TextView>(R.id.txtDomicilioV)
    val numero = view.findViewById<TextView>(R.id.txtNumeroV)

    fun render(vecinosModel: itemUsu){
        nombre.text = vecinosModel.nombre
        apellido.text = vecinosModel.apellido
        domicilio.text = vecinosModel.domicilio
        numero.text = vecinosModel.numero
    }
}