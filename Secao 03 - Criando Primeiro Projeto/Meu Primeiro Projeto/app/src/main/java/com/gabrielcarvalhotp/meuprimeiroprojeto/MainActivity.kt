package com.gabrielcarvalhotp.meuprimeiroprojeto

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnClickHere: Button
    private lateinit var txtDrawResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnClickHere = findViewById<Button>(R.id.btnClickHere)
        txtDrawResult = findViewById<Button>(R.id.txtDrawResult)

        btnClickHere.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnClickHere -> handleBtnClickHere()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleBtnClickHere() {
        val randomNumber = Random.nextInt(11)
        txtDrawResult.text = "Número: $randomNumber"
    }

}