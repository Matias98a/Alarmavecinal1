package com.example.alarmavecinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmavecinal.databinding.FragmentEstadisticasBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EstadisticasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EstadisticasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var email: String? = null
    private var param2: String? = null
    private var _binding : FragmentEstadisticasBinding? = null
    private val binding get() = _binding!!
    private var notificador : Notificador? = null

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
        _binding = FragmentEstadisticasBinding.inflate(layoutInflater,container, false)
        val agregarIndicadorFragment = AgregarIndicadorFragment.newInstance(email.toString())


        binding.btnAgregarIndicador.setOnClickListener { notificador!!.cambiarFragment(agregarIndicadorFragment) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EstadisticasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(email: String) =
            EstadisticasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, email)

                }
            }
    }
}