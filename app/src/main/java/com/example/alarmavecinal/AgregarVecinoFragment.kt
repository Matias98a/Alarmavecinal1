package com.example.alarmavecinal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.alarmavecinal.databinding.FragmentAgregarVecinoBinding
import com.example.alarmavecinal.vecinosadapter.itemUsu
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarVecinoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarVecinoFragment : Fragment() {
    private var _binding : FragmentAgregarVecinoBinding? = null
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
        _binding = FragmentAgregarVecinoBinding.inflate(layoutInflater, container, false)

        val vecinosFragment = MisVecinosFragment()

        binding.btnGuardarContacto.setOnClickListener { guardarVecino(
            binding.edtNombre.text.toString(),
            binding.edtApellido.text.toString(),
            binding.edtDomicilio.text.toString(),
            binding.edtNumero.text.toString()
        )
            notificador!!.cambiarFragment(vecinosFragment)}
        /* binding.btnGuardarContacto.setOnClickListener {if (binding.edtNombre.text.isNotEmpty() && binding.edtApellido.text.isNotEmpty()
            && binding.edtDomicilio.text.isNotEmpty() && binding.edtNumero.text.isNotEmpty()){
            VecinosProveedor.listaVecinos.add(  Vecinos(binding.edtNombre.text.toString(),
                binding.edtApellido.text.toString(),
                binding.edtDomicilio.text.toString(),
                binding.edtNumero.text.toString()))

                    notificador!!.cambiarFragment(vecinosFragment)
            }
        } */


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AgregarVecinoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AgregarVecinoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun guardarVecino(nombre : String, apellido : String, domicilio : String, numero : String){
        val vecino = hashMapOf("nombre" to nombre,
            "apeillido" to apellido,
            "domicilio" to domicilio,
            "numero" to numero)

        db.collection("vecinos").document("${nombre+apellido}")
            .set(vecino)
            .addOnSuccessListener { Toast.makeText(context, " Usuario guardado con exito", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { Toast.makeText(context, " No se pudo completar la accion", Toast.LENGTH_SHORT).show() }

    }
}