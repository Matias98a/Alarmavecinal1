package com.example.alarmavecinal.login

import androidx.fragment.app.Fragment

interface LoginInterface {
    fun cambiarFragmentLogin(fragment : Fragment)
    fun intentRegistro(email: String)
    fun intentLogin(email : String)
}