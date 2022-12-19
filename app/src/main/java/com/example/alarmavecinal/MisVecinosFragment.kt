package com.example.alarmavecinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmavecinal.vecinosadapter.VecinosAdapter
import com.example.alarmavecinal.databinding.FragmentMisVecinosBinding
import com.example.alarmavecinal.vecinosadapter.itemUsu
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MisVecinosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MisVecinosFragment : Fragment() {
    private var _binding : FragmentMisVecinosBinding? = null
    private val binding get() = _binding!!
    private var notificador : Notificador? = null
    private var db = FirebaseFirestore.getInstance()

    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notificador = context as Notificador

    }

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
        _binding = FragmentMisVecinosBinding.inflate(layoutInflater, container, false)


        val agregarVecino = AgregarVecinoFragment()
        binding.btnContactos.setOnClickListener { initRecyclerView() }
        //initRecyclerView2()
        binding.btnAgregarVecinoFragment.setOnClickListener { notificador!!.cambiarFragment(agregarVecino) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MisVecinosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MisVecinosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecyclerView(){

        db.collection("vecinos")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val wallitem = document.toObject(itemUsu::class.java)

                    wallitem.apellido = document["apellido"].toString()
                    wallitem.domicilio = document["domicilio"].toString()
                    wallitem.nombre = document["nombre"].toString()
                    wallitem.numero = document["numero"].toString()
                    itemUsu.listaVecinos.add(wallitem)

                    binding.recyclerVecinos.layoutManager = LinearLayoutManager(context)
                    val vecinosCargados = VecinosAdapter(itemUsu.listaVecinos)
                    binding.recyclerVecinos.adapter = vecinosCargados


                }
            }
    }

    private fun initRecyclerView2(){
        binding.recyclerVecinos.layoutManager = LinearLayoutManager(context)
        val vecinosCargados = VecinosAdapter(itemUsu.listaVecinos)
        binding.recyclerVecinos.adapter = vecinosCargados
    }
}