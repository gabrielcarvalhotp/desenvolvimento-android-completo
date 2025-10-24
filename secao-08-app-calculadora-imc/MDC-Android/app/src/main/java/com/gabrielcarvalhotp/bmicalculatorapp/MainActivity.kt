package com.gabrielcarvalhotp.bmicalculatorapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var buttonCalculate: Button
    lateinit var editWeight : TextInputEditText
    lateinit var editHeight : TextInputEditText

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
        editWeight = findViewById(R.id.edit_weight)
        editHeight = findViewById(R.id.edit_height)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_calculate -> {
                editWeight.error = null
                editHeight.error = null

                val weight = editWeight.text.toString().toDoubleOrNull()
                val height = editHeight.text.toString().toDoubleOrNull()

                if (weight == null) {
                    editWeight.error = "Preencha o peso corretamente!"
                }

                if (height == null) {
                    editHeight.error = "Preencha a altura corretamente!"
                }

                if (weight == null || height == null) return

                val intent = Intent(this, DiagnosisActivity::class.java)
                intent.putExtra("weight", weight)
                intent.putExtra("height", height)
                startActivity(intent)
            }
        }
    }

}