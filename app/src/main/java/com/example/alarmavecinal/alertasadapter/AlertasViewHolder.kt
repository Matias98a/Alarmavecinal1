package com.example.alarmavecinal.alertasadapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmavecinal.R
import org.w3c.dom.Text

class AlertasViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val textoTitulo = view.findViewById<TextView>(R.id.txtTitulo)
    val textoAlerta = view.findViewById<TextView>(R.id.txtTextoAlerta)
    val botonEliminar = view.findViewById<Button>(R.id.btnEliminarAlerta)

    fun renderAlerta(alertasModel : ItemAlertas){
        textoTitulo.text = "${alertasModel.tipoDeAlerta}"
        textoAlerta.text = "${alertasModel.nombre} ${alertasModel.apellido} -DNI ${alertasModel.dni}," +
                "domicilio: ${alertasModel.domicilio} Barrio : ${alertasModel.barrio} alerto sobre ${alertasModel.tipoDeAlerta}" +
                "dirijase a la brevedad"
    }

}