package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithTwoDiceAndButtons(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithTwoDiceAndButtons(modifier: Modifier = Modifier) {
    var dice1Result by remember { mutableStateOf(1) }
    var dice2Result by remember { mutableStateOf(1) }
    var showSecondDice by remember { mutableStateOf(true) }

    // Función auxiliar para obtener la imagen del dado según el valor
    fun diceImageFor(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar los dados (el segundo solo si showSecondDice es true)
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(diceImageFor(dice1Result)),
                contentDescription = dice1Result.toString(),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            if (showSecondDice) {
                Image(
                    painter = painterResource(diceImageFor(dice2Result)),
                    contentDescription = dice2Result.toString(),
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón 1: lanza solo el primer dado
        Button(onClick = {
            dice1Result = (1..6).random()
            showSecondDice = false
        }) {
            Text(stringResource(R.string.roll_one_dice))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón 2: lanza los dos dados
        Button(onClick = {
            dice1Result = (1..6).random()
            dice2Result = (1..6).random()
            showSecondDice = true
        }) {
            Text(stringResource(R.string.roll_two_dice))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mostrar el número de puntos de la tirada
        Text(
            text = if (showSecondDice) {
                stringResource(R.string.result_two, dice1Result + dice2Result)
            } else {
                stringResource(R.string.result_one, dice1Result)
            }
        )
    }
}
