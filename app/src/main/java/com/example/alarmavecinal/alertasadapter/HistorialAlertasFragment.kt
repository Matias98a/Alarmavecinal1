package com.example.alarmavecinal.alertasadapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmavecinal.R
import com.example.alarmavecinal.databinding.FragmentHistorialAlertasBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistorialAlertasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistorialAlertasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var alertList : ArrayList<ItemAlertas>
    private lateinit var alertAdapter : AlertasAdapter
    private val db = FirebaseFirestore.getInstance()
    private var _binding : FragmentHistorialAlertasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistorialAlertasBinding.inflate(layoutInflater, container, false)

        llamarRecyclerConAlertas()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistorialAlertasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistorialAlertasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun llamarRecyclerConAlertas(){
        alertList = ArrayList()
        alertAdapter = AlertasAdapter(alertList)
        db.collection("Alertas").get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val alertItem = document.toObject(ItemAlertas::class.java)
                    alertItem.tipoDeAlerta = document.id
                    alertItem.nombre = document["nombre"].toString()
                    alertItem.apellido = document["apellido"].toString()
                    alertItem.dni = document["dni"].toString()
                    alertItem.domicilio = document["domicilio"].toString()
                    alertItem.barrio = document["barrio"].toString()
                    binding.recyclerAlertas.layoutManager = LinearLayoutManager(context)
                    binding.recyclerAlertas.adapter = alertAdapter
                    alertList.add(alertItem)

                }
            }
    }
}