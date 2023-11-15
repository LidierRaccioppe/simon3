package com.dam.simondice

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.simondice.ui.theme.SimonDiceTheme

/**
 * Interfaz de usuario
 */
class IU (miViewModel: MyViewModel){
    var myViewModel = miViewModel
    @Composable
    fun simon(myViewModel: MyViewModel){
        columnaAgregar("izquierda", myViewModel)
        columnaAgregar("derecha", myViewModel)
    }
    @Composable
    fun columnaAgregar(cualColumnaAgregar :String, miViewModel: MyViewModel){
        if (cualColumnaAgregar.equals("izquierda")){
            Column {
                botonColor("rojo")
                botonColor("verde")
                botonStartYRestar()
            }
        }
        if (cualColumnaAgregar.equals("derecha")){
            Column {
                Column {
                    Text(text = "Ronda")
                    Text("${miViewModel.getRonda()}")
                }
                botonColor("amarillo")
                botonColor("azul")
                botonEnviar()
            }
        }
    }
    @Composable
    fun botonColor(colorCual : String){
        if (colorCual.equals("rojo") ){
            Button(onClick = {//incrementa la ronda
                // ronda++
            },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors()) { Color.Red }
        }
        if (colorCual.equals("verde") ){
            Button(onClick = {//incrementa la ronda
                // ronda++
            },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors()) { Color.Green }
        }
        if (colorCual.equals("amarillo") ){
            Button(onClick = {//incrementa la ronda
                // ronda++
            },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors()) { Color.Yellow }
        }
        if (colorCual.equals("azul") ){
            Button(onClick = {//incrementa la ronda
                // ronda++
            },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors()) { Color.Blue }
        }
    }

    /**
     * Boton que se usa para empezar el juego y para reiniciarlo
     */
    @Composable
    fun botonStartYRestar(){
        // Al darle al boton start hay que cambiarle el texto
        Button(
            onClick = {
                Log.d("Apretado", "${myViewModel.aumentarRonda()}")

                      },
            modifier = Modifier
                .height(30.dp)
                .width((300 / 2).dp)
                .padding(horizontal = 30.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(text = "Start")
        }
    }
    @Composable
    fun botonEnviar(){
        Button(
            onClick = {
                Log.d("Apretado", "${myViewModel.aumentarRonda()}")
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
}