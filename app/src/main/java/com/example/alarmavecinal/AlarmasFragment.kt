package com.example.alarmavecinal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.alarmavecinal.databinding.FragmentAlarmasBinding
import com.example.alarmavecinal.vecinosadapter.itemUsu
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import androidx.core.content.ContextCompat.getSystemService as getSystemService1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmasFragment : Fragment() {

    private val channelId = "ChannelId"
    private val channelName = "ChannelName"

    private var _binding :FragmentAlarmasBinding? = null
    private val binding get() = _binding!!

    private lateinit var nombre : String
    private lateinit var apellido : String
    private lateinit var domicilio : String
    private lateinit var dni : String
    private lateinit var barrio : String

    private lateinit var userList : ArrayList<DatosUsuarios>
    private var db = FirebaseFirestore.getInstance()
    private var email: String? = null
    private var param2: String? = null
    private var notificador : Notificador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notificador = context as Notificador
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAlarmasBinding.inflate(layoutInflater, container, false)

        val tipoDeAlerta1 = "ROBO DE CASA"
        val tipoDeAlerta2 = "SOSPECHOSO"
        val tipoDeAlerta3 = "POLICIA"

        obtenerDatosUser(email.toString())

        binding.btnRoboCasa.setOnClickListener{

            notificador!!.notiAlertaRoboDeCasa(nombre,apellido, dni, domicilio, barrio)
            guardarNotificacionDb(tipoDeAlerta1,nombre, apellido, dni, domicilio, barrio)

        }

        binding.btnSospechoso.setOnClickListener{

            notificador!!.notiAlertaSospechoso(nombre, apellido, dni, domicilio, barrio)
            guardarNotificacionDb(tipoDeAlerta2, nombre, apellido, dni, domicilio, barrio)

        }

        binding.btnPolicia.setOnClickListener{

            notificador!!.notiAlertaPolicia(nombre, apellido, dni, domicilio, barrio)
            guardarNotificacionDb(tipoDeAlerta3, nombre, apellido, dni, domicilio, barrio)
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlarmasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
            AlarmasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, email)

                }
            }
    }

    /// OBTENER DATOS
    fun obtenerDatosUser(email: String) {
        nombre = ""
        apellido = ""
        dni = ""
        domicilio = ""
        barrio = ""


        val userData = db.collection("Usuarios Registrados").document("$email")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("tag", "datos : ${document.data}")
                    val prueba = document.data
                    nombre = prueba?.get("nombre").toString()
                    apellido = document["apellido"].toString()
                    dni = document.get("dni").toString()
                    domicilio = document.get("domicilio").toString()
                    barrio = document.get("barrio").toString()

                    Log.d("TAG", "probando probando prueba $nombre y $dni")




                }

            }

        }


        fun guardarNotificacionDb(tipoDeAlerta:String,
            nombre: String, apellido: String, dni: String,
            domicilio: String, barrio: String
        ) {
            val datoAlarma = hashMapOf(
                "nombre" to nombre,
                "apellido" to apellido,
                "dni" to dni,
                "domicilio" to domicilio,
                "barrio" to barrio
            )
            db.collection("Alertas").document("$tipoDeAlerta")
                .set(datoAlarma)
        }

// TAREA
// OBTENER LISTA DE DATOS DEL USUARIO Y GUARDARLO EN LISTOF<DatosUsuarios>
// ENVIAR DATOS DE ESA LISTA MEDIANTE INTERFAZ POR LA FUNCION DE NOTIFICACION Y MOSTRAR DATOS
//TAREA2
// CADA VEZ QUE SE PRESIONA NOTIFICACION GUARDAR LA NOTIFICACION CON SUS DATOS EN BASE DE DATOS
// EN UNA NUEVA COLECCION "NOTIFICACION ROBO / HURTO / POLICIA "
// TAREA 3
// CARGAR UN RECYCLER VIEW EN ESTADISTICAS MOSTRANDO TODAS LAS NOTIFICACIONES PULSADAS
    }