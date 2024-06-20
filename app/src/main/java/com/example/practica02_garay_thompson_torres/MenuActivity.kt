package com.example.practica02_garay_thompson_torres

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica02_garay_thompson_torres.ui.fragments.RegisterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .commit()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.boton_navegacion)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_register -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RegisterFragment())
                        .commit()
                    true
                }
                //R.id.navigation_list -> {
                //    supportFragmentManager.beginTransaction()
                //        .replace(R.id.fragment_container, ListFragment())
                //        .commit()
                //    true
                //}
                else -> false
            }
        }
    }
}
