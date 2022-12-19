package com.example.alarmavecinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.alarmavecinal.databinding.FragmentAgregarIndicadorBinding
import com.example.alarmavecinal.databinding.FragmentAgregarVecinoBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarIndicadorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarIndicadorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var email: String? = null
    private var param2: String? = null
    private var _binding : FragmentAgregarIndicadorBinding? = null
    private val binding get() = _binding!!
    private var notificador : Notificador? = null
    private var db = FirebaseFirestore.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notificador = context as Notificador
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgregarIndicadorBinding.inflate(layoutInflater, container, false)
        val estadisticasFragment = EstadisticasFragment()

        binding.btnAgregarInd.setOnClickListener {
            agregarIndicador(binding.edtFecha.text.toString(),
            binding.edtHoraI.text.toString(),
            binding.edtLatitud.text.toString(),
            binding.edtLongitud.text.toString(),
            binding.edtPais.text.toString(),
            binding.edtProvincia.text.toString(),
            binding.edtCiudad.text.toString(),
            binding.edtBarrio.text.toString(),
            binding.edtDelito.text.toString())
            Toast.makeText(context, "INDICADOR AGREGADO", Toast.LENGTH_LONG).show()
            notificador!!.cambiarFragment(estadisticasFragment) }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AgregarIndicadorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
            AgregarIndicadorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, email)

                }
            }
    }

    fun agregarIndicador(fecha : String, hora : String , latitud : String, longitud : String, pais : String,
    provincia : String, ciudad: String, barrio : String, tipoDeDelito : String){

        val mapaIndicador = hashMapOf("fecha" to fecha,
        "hora" to hora,
        "latitud" to latitud,
        "longitud" to longitud,
        "pais" to pais,
        "provincia" to provincia,
        "ciudad" to ciudad,
        "barrio" to barrio,
        "tipo de delito" to tipoDeDelito)

        db.collection("Indicadores").document("${email}").set(mapaIndicador)
    }
}