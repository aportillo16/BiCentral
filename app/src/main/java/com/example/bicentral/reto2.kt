package com.example.bicentral

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.api.local_api
import com.example.bicentral.databinding.Reto2Binding


class reto2 : AppCompatActivity() {

    private lateinit var binding: Reto2Binding

    private var topAnim: Animation? = null
    private var bottomAnim:Animation? = null

    lateinit var image: ImageView
    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var ingr: TextView
    lateinit var droidx: androidx.cardview.widget.CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Reto2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        image = binding.imgViewWallet
        user = binding.edTUser
        pass = binding.edTPass
        ingr = binding.txtButtonIngress
        droidx = binding.cardvw

        image.setAnimation(topAnim)
        user.setAnimation(bottomAnim)
        pass.setAnimation(bottomAnim)
        ingr.setAnimation(bottomAnim)
        droidx.setAnimation(bottomAnim)

        val mDelay: Long = 2000
        Handler(Looper.getMainLooper()).postDelayed({
        }, mDelay)

        ingr?.setOnClickListener {
            if(user.text.toString() != "" && pass.text.toString() != "")
                validaLogin(user.text.toString(), pass.text.toString())
            else
                showToast("No contiene texto...")
        }
    }

    private fun validaLogin(username: String, password: String){
        val sessionToken = local_api().validateUser(username, password)

        if(sessionToken != null){
            val intent = Intent(this, reto2_inicio::class.java).apply {
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
