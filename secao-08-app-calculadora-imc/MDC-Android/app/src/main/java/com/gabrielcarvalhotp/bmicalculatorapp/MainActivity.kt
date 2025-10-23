package com.gabrielcarvalhotp.bmicalculatorapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeViewComponents()
        buttonCalculate.setOnClickListener(this)
    }

    private fun initializeViewComponents() {
        buttonCalculate = findViewById(R.id.button_calculate)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_calculate -> {
                val intent = Intent(this, DiagnosisActivity::class.java)

                startActivity(intent)
            }
        }
    }

}