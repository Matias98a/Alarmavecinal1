package com.example.alarmavecinal.chatprueba

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmavecinal.Notificador
import com.example.alarmavecinal.R
import com.example.alarmavecinal.databinding.FragmentListaDeChatsBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaDeChatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaDeChatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var db = FirebaseFirestore.getInstance()
    private var notificador : Notificador? = null
    private var _binding : FragmentListaDeChatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
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
        _binding = FragmentListaDeChatsBinding.inflate(layoutInflater,container, false)
        val chatFragment = ChatFragment()


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListaDeChatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaDeChatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun obtenerChats(){

        // OBTENER CHATS X USUARIO

        var userRef = db.collection("Usuarios Registrados").document("matias_arg@live.com.ar")
        userRef.collection("chats").get()
            .addOnSuccessListener { chats ->
                val listChat = chats.toObjects(Chat::class.java)
                // holdear lista en recycler
        }

    }

    fun newChat(){
        val miUsuario = "matias_arg@live.com.ar"
        val userId =UUID.randomUUID().toString()
        val otherUser = binding.edtUserChat.text.toString()
        val users = listOf(miUsuario, otherUser)

        val chat = Chat(
            id = userId,
            name = "chat con ${otherUser}",
            users = users)
        db.collection("chats").document(userId).set(chat)
        db.collection("Usuarios Registrados").document(miUsuario).collection("chats")
            .document(userId).set(chat)
        db.collection("Usuarios Registrados").document(otherUser).collection("chats")
            .document(userId).set(chat)

        // cambiar de fragment y enviar mi id y mi user VIDEO 38:14
        //otificador!!.cambiarFragment(chatFragment)

    }
}