package com.example.bicentral

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api.local_api
import com.example.bicentral.databinding.Reto3Binding

class reto3 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: Reto3Binding
    lateinit var user: EditText
    lateinit var pass: EditText
    var buttonAccept:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Reto3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        user = binding.edTU
        pass = binding.edTP
        buttonAccept = binding.loginButton

        buttonAccept?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.loginButton->{
                if(user.text.toString() != "" && pass.text.toString() != "")
                    validaLogin(user.text.toString(), pass.text.toString())
                else
                    showToast("No contiene texto...")
            }
        }
    }

    private fun validaLogin(username: String, password: String){
        val sessionToken = local_api().validateUser(username, password)

        if(sessionToken != null){
            val intent = Intent(this, reto3_menu::class.java).apply {
                putExtra("SESSION_TOKEN", sessionToken)
            }
            startActivity(intent)
        }else {
            showToast("Credenciales incorrectas...")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}