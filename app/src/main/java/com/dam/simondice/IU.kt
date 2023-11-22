package com.dam.simondice

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.simondice.ui.theme.SimonDiceTheme

/**
 * Interfaz de usuario
 */
class IU (miViewModel: MyViewModel) {

    @Composable
    fun simon(miViewModel: MyViewModel) {
        Row {
            columnaAgregar("izquierda", miViewModel)
            columnaAgregar("derecha", miViewModel)
        }
    }

    @Composable
    fun columnaAgregar(cualColumnaAgregar: String, miViewModel: MyViewModel) {
        if (cualColumnaAgregar.equals("izquierda")) {
            Column {
                botonColor("rojo", miViewModel)
                botonColor("verde", miViewModel)
                botonStartYRestar(miViewModel)
            }
        }
        if (cualColumnaAgregar.equals("derecha")) {
            Column {
                textoRonda(miViewModel)
                botonColor("amarillo", miViewModel)
                botonColor("azul", miViewModel)
                botonEnviar(miViewModel)
            }
        }
    }

    @Composable
    fun botonColor(colorCual: String, miViewModel: MyViewModel) {
        var strApretado = "boton apretado"
        if (colorCual.equals("rojo")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    miViewModel.aumentarSecuenciaUsuario(Color.Red.toArgb())
                    // Hacer registro de que se ha apretado el boton
                    Log.d(strApretado, "boton apretado : rojo")
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.Red)){ Color.Red }
        }
        if (colorCual.equals("verde")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    miViewModel.aumentarSecuenciaUsuario(Color.Green.toArgb())
                    // Hacer registro de que se ha apretado el boton
                    Log.d(strApretado, "boton apretado : verde")
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)){ Color.Green}
        }
        if (colorCual.equals("amarillo")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    miViewModel.aumentarSecuenciaUsuario(Color.Yellow.toArgb())
                    // Hacer registro de que se ha apretado el boton
                    Log.d(strApretado, "boton apretado : amarillo")
                    },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.Yellow)){ Color.Yellow }
        }
        if (colorCual.equals("azul")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    miViewModel.aumentarSecuenciaUsuario(Color.Blue.toArgb())
                    // Hacer registro de que se ha apretado el boton
                    Log.d(strApretado, "boton apretado : azul")
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue)) { Color.Blue }
        }
    }

    /**
     * Boton que se usa para empezar el juego y para reiniciarlo
     */
    @Composable
    fun botonStartYRestar(myViewModel: MyViewModel) {
        // Al darle al boton start hay que cambiarle el texto
        Button(
            onClick = {
                if (myViewModel.getRonda()!=0){
                    myViewModel.reiniciarRonda()
                    Log.d("Apretado", "Reiniciado")
                }
                else{
                    Log.d("Apretado", "Start")
                }

            },
            modifier = Modifier
                .height(35.dp)
                .width((350 / 2).dp)
                .padding(horizontal = 30.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            if(myViewModel.getRonda()==0){
                Text(text = "Start")
            }else{
                Text(text = "Restart")
            }
        }
    }

    @Composable
    fun botonEnviar(miViewModel: MyViewModel) {
        Button(
            onClick = {
                miViewModel.aumentarRonda()
                // aumentar el valor de la ronda donde se muestra

                Log.d("Apretado", "enviar, aumentar la ronda ${miViewModel.getRonda()}")

            },
            modifier = Modifier
                .height(30.dp)
                .width((300 / 2).dp)
                .padding(horizontal = 30.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Image(
                painter = painterResource(R.drawable.very),
                contentDescription = "Icono2"
            )
        }
    }
    /**
     * Texto que se usa para mostrar la ronda actual
     */
    @Composable
    fun textoRonda(miViewModel: MyViewModel) {
        Text(text = "Ronda: ${miViewModel.getRonda()}")
    }
}