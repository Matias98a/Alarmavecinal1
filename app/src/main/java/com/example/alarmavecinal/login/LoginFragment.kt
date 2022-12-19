package com.example.alarmavecinal.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.alarmavecinal.Notificador
import com.example.alarmavecinal.R
import com.example.alarmavecinal.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var loginInterface: LoginInterface? = null
    private var auth = FirebaseAuth.getInstance()

    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginInterface = context as LoginInterface
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
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val registryFragment = RegistryFragment()
        binding.btnIniciarSesion.setOnClickListener { iniciarSesion(binding.edtUser.text.toString(),
            binding.edtPass.text.toString()) }


        binding.btnRegistrarse.setOnClickListener {
            loginInterface!!.cambiarFragmentLogin(registryFragment) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun iniciarSesion(email: String, pass: String) {
        if (binding.edtUser.text.isNotEmpty() && binding.edtPass.text.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener { loginInterface!!.intentLogin(binding.edtUser.text.toString()) }.addOnFailureListener {
                    Toast.makeText(activity, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(activity, "Usuario y/o Contraseña vacios", Toast.LENGTH_SHORT).show()
        }
    }
}
