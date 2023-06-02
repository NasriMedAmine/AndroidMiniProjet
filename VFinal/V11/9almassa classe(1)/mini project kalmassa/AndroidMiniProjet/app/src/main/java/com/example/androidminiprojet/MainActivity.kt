package com.example.androidminiprojet

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var drawerlayout : DrawerLayout
    private lateinit var imagemenu : ImageView
    private lateinit var navigationView : NavigationView
    private lateinit var navController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)



        drawerlayout = findViewById(R.id.drawer_layout)
        imagemenu = findViewById(R.id.imagemenu)
        navigationView = findViewById(R.id.navigationView)
        navigationView.itemIconTintList = null

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)

        imagemenu.setOnClickListener(View.OnClickListener {
            onclick()
        } )
    }
    private fun onclick() {
        drawerlayout?.openDrawer(GravityCompat.START)
    }





}