package com.example.androidminiprojet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class DonateurMainActivity : AppCompatActivity() {

    private lateinit var drawerlayout : DrawerLayout
    private lateinit var imagemenu : ImageView
    private lateinit var navigationView : NavigationView
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_donateur_main)

        drawerlayout = findViewById(R.id.drawer_layout_donateur)
        imagemenu = findViewById(R.id.imagemenudonateur)
        navigationView = findViewById(R.id.navigationviewdonateur)
        navigationView.itemIconTintList = null

        navController = Navigation.findNavController(this, R.id.navHostFragmentDonateur)
        NavigationUI.setupWithNavController(navigationView, navController)

        imagemenu.setOnClickListener(View.OnClickListener {
            onclick()
        } )

    }
    private fun onclick() {
        drawerlayout?.openDrawer(GravityCompat.START)
    }

}