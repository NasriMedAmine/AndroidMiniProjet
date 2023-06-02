package com.example.androidminiprojet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplasherActivity : AppCompatActivity() {

    private lateinit var topanim : Animation

    private lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splasher)

         imageView = findViewById(R.id.imageView2)
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        imageView.setAnimation(topanim)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent (this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        }, 3000)



    }
}