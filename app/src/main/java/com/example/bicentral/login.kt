package com.example.bicentral

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class login : AppCompatActivity() {

    private var topAnim: Animation? = null
    private var bottomAnim:Animation? = null

    lateinit var image: ImageView
    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var ingr: TextView
    lateinit var droidx: androidx.cardview.widget.CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        image = findViewById(R.id.imgView_wallet)
        user = findViewById(R.id.edTUser)
        pass = findViewById(R.id.edTPass)
        ingr = findViewById(R.id.txtButtonIngress)
        droidx = findViewById(R.id.cardvw)

        image.setAnimation(topAnim)
        user.setAnimation(bottomAnim)
        pass.setAnimation(bottomAnim)
        ingr.setAnimation(bottomAnim)
        droidx.setAnimation(bottomAnim)

        val mDelay: Long = 2000
        Handler(Looper.getMainLooper()).postDelayed({
        }, mDelay)

        pass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (user.text!=null) {
                    pass.backgroundTintList = ColorStateList.valueOf(R.color.colorAccent)
                    pass.isEnabled = true
                }

                 //pass.isEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}