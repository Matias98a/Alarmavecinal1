package com.example.alarmavecinal

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.alarmavecinal.databinding.ActivityMainBinding
import com.example.alarmavecinal.vecinosadapter.itemUsu
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), Notificador {
    private val channelId = "ChannelId"
    private val channelName = "ChannelName"

    var notificacionRobo = 1
    var notificacionSospecha = 2
    var notificacionPolicia = 3
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
    binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)



        // OBTENCION DE EMAIL MEDIANTE BUNDLE
        val bundle = intent.extras
        var dato = bundle?.getString("email")
        Log.d("TAG", "dato obtenido $dato")
        // OBTENCION DE EMAIL MEDIANTE BUNDLE





        val bienvenidaFragment = BienvenidaFragment.newInstance(dato.toString())
        val alarmasFragment = AlarmasFragment.newInstance(dato.toString())
        val vecinosFragment = MisVecinosFragment()
        val policiaFragment = PoliciaFragment()
        val utilidadesFragment = UtilidadesFragment()
        val agregarIndicadorFragment = AgregarIndicadorFragment.newInstance(dato.toString())
        val estadisticasFragment = EstadisticasFragment.newInstance(dato.toString())
        launcherFragment(bienvenidaFragment)



        binding.btnAlarma.setOnClickListener{ launcherFragment(alarmasFragment)}
        binding.btnVecinos.setOnClickListener { launcherFragment(vecinosFragment) }
        binding.btnPoliciaFrag.setOnClickListener { launcherFragment(policiaFragment) }
        binding.btnUtilidades.setOnClickListener { launcherFragment(utilidadesFragment) }
        binding.btnEstadistica.setOnClickListener { launcherFragment(estadisticasFragment) }
        createNotificationChannel()




        setContentView(binding.root)
    }

    fun launcherFragment(fragment : Fragment){
    val launcher = supportFragmentManager.beginTransaction()
        launcher.replace(binding.contenedorMain.id, fragment, "main").addToBackStack("main")
        launcher.commit()
    }


    fun activarNotificacion(){
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.msj)
            .setContentTitle("Notificacion")
            .setContentText("Se ha pulsado notificacion POLICI")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(this)){
            notify(notificacionRobo.toInt(), builder.build())}
    }
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = channelName.toString()
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    // INTERFAZ NOTIFICACIONES DE ALERTAS

    override fun notiAlertaRoboDeCasa(
        nombre: String,
        apellido : String,
        dni: String,
        domicilio: String,
        barrio: String
    ) {
            var builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.msj)
                .setContentTitle("ALERTA ROBO DE CASA")
                .setContentText("$nombre $apellido DNI $dni, domicilio : $domicilio, B. $barrio PRESIONO BOTON ROBO DE CASA ASISTIR URGENTE")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            with(NotificationManagerCompat.from(this)){
                notify(notificacionRobo.toInt(), builder.build()) }

    }

    override fun notiAlertaSospechoso(
        nombre: String,
        apellido: String,
        dni: String,
        domicilio: String,
        barrio: String
    ) {
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.msj)
            .setContentTitle("ALERTA SOSPECHOSO")
            .setContentText("$nombre $apellido dni: $dni alerto de un sospechoso en $domicilio barrio $barrio, dirijase a la brevedad")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(this)){
            notify(notificacionSospecha.toInt(), builder.build())
        }
    }

    override fun notiAlertaPolicia(
        nombre: String,
        apellido: String,
        dni: String,
        domicilio: String,
        barrio: String
    ) {
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.msj)
            .setContentTitle("LLAMADO A LA POLICIA")
            .setContentText("$nombre $apellido dni: $dni presiono boton antipanico de llamado a la policia" +
                    "dirijase a la brevedad. domicilio: $domicilio, barrio : $barrio")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            with(NotificationManagerCompat.from(this)){
                notify(notificacionPolicia.toInt(), builder.build())
            }
    }

    override fun cambiarFragment(fragment: Fragment) {
        launcherFragment(fragment)
    }






    }
