package com.gabrielcarvalhotp.bmicalculatorapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DiagnosisActivity : AppCompatActivity() {

    lateinit var textWeight: TextView
    lateinit var textHeight: TextView
    lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diagnosis)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeViewComponents()

        val extras = intent.extras!!
        val weight = extras.getDouble("weight")
        val height = extras.getDouble("height")

        textWeight.text = "Peso informado: $weight kg"
        textHeight.text = "Altura informada: $height m"

        val bmi = weight / (height * height)

        textResult.text = when (bmi) {
            in 0.0..18.5 -> "Baixo"
            in 18.5..24.9 -> "Normal"
            in 25.0..29.9 -> "Sobrepeso"
            in 30.0..39.9 -> "Obesidade"
            else -> "Vai fazer uma dieta quando?"
        }
    }

    private fun initializeViewComponents() {
        textWeight = findViewById(R.id.text_weight)
        textHeight = findViewById(R.id.text_height)
        textResult = findViewById(R.id.text_result)
    }
}