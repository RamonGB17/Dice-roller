package com.example.diceroller // Asegúrate de que el nombre del paquete coincida con tu proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Importación para el delegate 'by'
import androidx.compose.runtime.mutableStateOf // Importación para el manejo de estado
import androidx.compose.runtime.remember // Importación para el manejo de estado
import androidx.compose.runtime.setValue // Importación para el delegate 'by'
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.R
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

// ------------------------------------------------------------------

/**
 * Función principal y punto de entrada de la aplicación Dice Roller.
 */
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

// ------------------------------------------------------------------

/**
 * Función que contiene la lógica, el estado y el diseño de la app.
 *
 * @param modifier Modificador heredado para la alineación y el tamaño.
 */
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // 1. Manejo del Estado (Lógica del dado)
    // Inicializa el resultado del dado en 1 y permite que se active la recomposición al cambiar
    var result by remember { mutableStateOf(1) }

    // Lógica condicional para mapear el resultado al recurso de imagen
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Contenedor de diseño vertical (Columna)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 2. Imagen del dado
        Image(
            // El 'painter' ahora usa el recurso de imagen dinámico
            painter = painterResource(imageResource),
            // La descripción de contenido refleja el valor actual del dado
            contentDescription = result.toString()
        )

        // Espaciador entre la imagen y el botón
        Spacer(modifier = Modifier.height(16.dp))

        // 3. Botón de lanzamiento
        Button(
            // Al hacer clic, se actualiza la variable 'result' con un número aleatorio entre 1 y 6
            onClick = { result = (1..6).random() }
        ) {
            Text(stringResource(R.string.roll))
        }
    }
}