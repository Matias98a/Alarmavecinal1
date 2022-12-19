package com.example.alarmavecinal.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.alarmavecinal.MainActivity
import com.example.alarmavecinal.R
import com.example.alarmavecinal.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity(), LoginInterface {
    private lateinit var binding : ActivityLoginBinding
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val loginFragment = LoginFragment()
        val registryFragment = RegistryFragment()
        launchFragment(loginFragment)


        setContentView(binding.root)
    }


    fun launchFragment (fragment : Fragment){
    val launcher =  supportFragmentManager.beginTransaction()
        launcher.replace(binding.conteinerLogin.id, fragment, "login").addToBackStack("login")
        launcher.commit()
    }

    override fun cambiarFragmentLogin(fragment: Fragment) {
        launchFragment(fragment)
    }

    override fun intentRegistro(email: String) {
        val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email).apply {
            Toast.makeText(this@LoginActivity, "User",Toast.LENGTH_SHORT).show()}
        startActivity(intent)
    }

    override fun intentLogin(email: String) {
        val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email).apply {
            Toast.makeText(this@LoginActivity, "Iniciaste Sesion",Toast.LENGTH_SHORT).show()
        }
        startActivity(intent)
    }
}