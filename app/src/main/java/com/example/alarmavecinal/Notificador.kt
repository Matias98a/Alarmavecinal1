package com.example.alarmavecinal

import androidx.fragment.app.Fragment

interface Notificador {
    fun notiAlertaRoboDeCasa(nombre: String,apellido : String,dni : String, domicilio : String , barrio : String)
    fun notiAlertaSospechoso(nombre: String,apellido : String,dni : String, domicilio : String , barrio : String)
    fun notiAlertaPolicia(nombre: String,apellido : String,dni : String, domicilio : String , barrio : String)
    fun cambiarFragment(fragment : Fragment)


}