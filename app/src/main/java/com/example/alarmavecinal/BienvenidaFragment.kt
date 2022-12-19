package com.example.alarmavecinal

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmavecinal.databinding.FragmentBienvenidaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NOMBRE = "nombre"
private const val ARG_APELLIDO = "apellido"

/**
 * A simple [Fragment] subclass.
 * Use the [BienvenidaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BienvenidaFragment : Fragment() {
    private var _binding : FragmentBienvenidaBinding? = null
    private val binding get() = _binding!!
    private var notificador : Notificador? = null
    private var nombre: String? = null
    private var apellido: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(ARG_NOMBRE)
            apellido = it.getString(ARG_APELLIDO)
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
        _binding = FragmentBienvenidaBinding.inflate(layoutInflater,container,false)
        val misDatosFragment = MisDatosFragment()

        binding.txtMiNombre.text = nombre

        binding.btnEditarMisDatos.setOnClickListener { notificador!!.cambiarFragment(misDatosFragment) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BienvenidaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(nombre: String) =
            BienvenidaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NOMBRE, nombre)

                }
            }
    }

}
