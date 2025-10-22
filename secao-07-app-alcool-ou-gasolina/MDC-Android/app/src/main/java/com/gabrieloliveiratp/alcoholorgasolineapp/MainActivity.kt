package com.gabrieloliveiratp.alcoholorgasolineapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textAlcoholPrice: TextInputLayout
    private lateinit var textGasolinePrice: TextInputLayout
    private lateinit var editAlcoholPrice: TextInputEditText
    private lateinit var editGasolinePrice: TextInputEditText
    private lateinit var buttonCalculate: Button
    private lateinit var textResult: TextView

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
        buttonCalculate.setOnClickListener { calculateTheBestChoice() }
    }

    private fun initializeViewComponents() {
        textAlcoholPrice = findViewById(R.id.text_alcohol_price)
        textGasolinePrice = findViewById(R.id.text_gasoline_price)
        editAlcoholPrice = findViewById(R.id.edit_alcohol_price)
        editGasolinePrice = findViewById(R.id.edit_gasoline_price)
        buttonCalculate = findViewById(R.id.button_calculate)
        textResult = findViewById(R.id.text_result)
    }

    private fun calculateTheBestChoice() {
        val alcoholPrice = editAlcoholPrice.text.toString()
        val gasolinePrice = editGasolinePrice.text.toString()

        if (!validateFields(alcoholPrice, gasolinePrice)) return

        if (alcoholPrice.toDouble() / gasolinePrice.toDouble() >= 0.7 ) {
            textResult.text = "Melhor Utilizar Gasolina"
        } else {
            textResult.text = "Melhor Utilizar Álcool"
        }
    }

    private fun validateFields(alcoholPrice: String, gasolinePrice: String): Boolean {
        textAlcoholPrice.error = null
        textGasolinePrice.error = null

        if (alcoholPrice.isEmpty()) {
            textAlcoholPrice.error = "Preecha o valor do álcool corretamente!"
            return false
        }
        if (gasolinePrice.isEmpty()) {
            textGasolinePrice.error = "Preecha o valor da gasolina corretamente!"
            return false
        }
        return true
    }
}