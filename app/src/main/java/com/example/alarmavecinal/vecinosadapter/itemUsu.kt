package com.example.alarmavecinal.vecinosadapter

import com.example.alarmavecinal.DatosUsuarios

data class itemUsu (
    var apellido : String = "",
    var domicilio : String = "",
    var nombre : String = "",
    var numero: String = ""){

    companion object{
        val listaVecinos = ArrayList<itemUsu>()
        val datosObtenidos = ArrayList<DatosUsuarios>()
    }
}