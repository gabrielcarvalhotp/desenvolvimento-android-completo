package com.gabrielcarvalhotp.alcoholorgasolineapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielcarvalhotp.alcoholorgasolineapp.ui.theme.AlcoholOrGasolineAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AlcoholOrGasolineAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues = PaddingValues()) {
    var alcoholTextValue by remember { mutableStateOf("") }
    var gasolineTextValue by remember { mutableStateOf("") }
    var resultTextValue by remember { mutableStateOf("Resultado") }
    var alcoholTextError by remember { mutableStateOf("") }
    var gasolineTextError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Image(
                painter = painterResource(R.drawable.logo),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Saiba qual a melhor opção para abastecer seu carro!",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = alcoholTextValue,
                onValueChange = { alcoholTextValue = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Digite o preço do álcool ex: 1.90") },
                isError = alcoholTextError.isNotEmpty(),
                supportingText = {
                    if (alcoholTextError.isNotEmpty()) {
                        Text(alcoholTextError, Modifier.clearAndSetSemantics { })
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = gasolineTextValue,
                onValueChange = { gasolineTextValue = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Digite o preço da gasolina ex: 4.90") },
                isError = gasolineTextError.isNotEmpty(),
                supportingText = {
                    if (gasolineTextError.isNotEmpty()) {
                        Text(gasolineTextError, Modifier.clearAndSetSemantics { })
                    }
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    alcoholTextError = ""
                    gasolineTextError = ""

                    val alcohol = alcoholTextValue.toDoubleOrNull()
                    val gasoline = gasolineTextValue.toDoubleOrNull()

                    if (alcohol == null) {
                        alcoholTextError = "Digite um valor válido para o álcool!"
                        return@Button
                    }
                    if (gasoline == null) {
                        gasolineTextError = "Digite um valor válido para a gasolina!"
                        return@Button
                    }
                    resultTextValue = calculateBestFuel(alcohol, gasoline)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Calcular")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = resultTextValue)
        }
    )
}

private fun calculateBestFuel(alcohol: Double, gasoline: Double): String {
    return if ((alcohol / gasoline) >= 0.7) {
        "Melhor utilizar gasolina!"
    } else {
        "Melhor utilizar álcool!"
    }
}

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
fun MainPreview() {
    AlcoholOrGasolineAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainContent(innerPadding)
        }
    }
}