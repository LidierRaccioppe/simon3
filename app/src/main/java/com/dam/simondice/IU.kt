package com.dam.simondice

import android.annotation.SuppressLint
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


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
    fun columnaAgregar(cualColumnaAgregar: String, miViewModel: MyViewModel){


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
                    //miViewModel.aumentarSecuenciaUsuario(Color.Red.toArgb())
                    // todo Se debe de usar la clase enum Color
                    // Incrementar la secuenncia de colores del usuario
                    botonApretado(0, colorCual, miViewModel)
                    miViewModel.tintineaUsuarioBlanco(0)

                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colores.ROJO.color.value)){}
        }
        if (colorCual.equals("amarillo")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    //miViewModel.aumentarSecuenciaUsuario(Color.Yellow.toArgb())
                    // Incrementar la secuenncia de colores del usuario
                    botonApretado(1, colorCual, miViewModel)
                    miViewModel.tintineaUsuarioBlanco(1)
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colores.AMARILLO.color.value)){}
        }
        if (colorCual.equals("verde")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    // miViewModel.aumentarSecuenciaUsuario(Color.Green.toArgb())
                    // Incrementar la secuenncia de colores del usuario
                    botonApretado(2, colorCual, miViewModel)
                    miViewModel.tintineaUsuarioBlanco(2)
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colores.VERDE.color.value)){}
        }
        if (colorCual.equals("azul")) {
            Button(
                onClick = {
                    // Incrementar la secuenncia de colores del usuario
                    // miViewModel.aumentarSecuenciaUsuario(Color.Blue.toArgb())
                    // Incrementar la secuenncia de colores del usuario
                    botonApretado(3, colorCual, miViewModel)
                    miViewModel.tintineaUsuarioBlanco(3)
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                    colors = ButtonDefaults.buttonColors(Colores.AZUL.color.value)){}
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
                    // Reiniciar la secuencia de la maquina
                    myViewModel.reiniciarSecuencia()
                    // Reiniciar la secuencia del usuario
                    myViewModel.reiniciarSecuenciaUsuario()
                    Log.d(DatosSingleton.tag, "Reiniciado")


                }
                else{
                    Log.d(DatosSingleton.tag, "Start")
                    // Reiniciar la secuencia por si comenzo de antemano
                    myViewModel.reiniciarSecuencia()
                    // Reiniciar la secuencia del usuario
                    myViewModel.reiniciarSecuenciaUsuario()
                    // Al comenzar la primera ronda hay que aumentar la secuencia de la maquina
                    myViewModel.aumentarSecuenciaMaquina()
                    // Mostar el cambio en los colores
                    myViewModel.showSequenceRun(300)



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

    /**
     * Boton que se usa para enviar la secuencia del usuario, asi como comprobar si la secuencia del usuario esta bien.
     * A su vez el aumentar la ronda, asi como aumentar la secuencia de la maquina al aumentar la ronda
     * @param miViewModel MyViewModel que se usa para llamar a los metodos de la clase MyViewModel
     */
    @Composable
    fun botonEnviar(miViewModel: MyViewModel) {
        Button(
            onClick = {
                if(miViewModel.comprobarSecuencia()){
                    Log.d(DatosSingleton.tag, "Secuencia correcta")
                    // aumentar el valor de la ronda donde se muestra
                    miViewModel.aumentarRonda()
                    // ahora se debe de aumentar la secuencia de la maquina
                    miViewModel.aumentarSecuenciaMaquina()
                    // Mostar el cambio en los colores
                    miViewModel.showSequenceRun(300)

                    // Reiniciar la secuencia del usuario
                    miViewModel.reiniciarSecuenciaUsuario()

                    // ahora se debe de obtener la secuencia de la maquina y con eso obtener el ultimo valor de la lista para compararlos con la lista de colores y mostrar el color adecuado en la secuencia
                    //mostrarSecuenciaVisual((miViewModel.getSecuencia()[miViewModel.getSecuenciaColores().last()] ))
                    // mostrarSecuenciaVisual(miViewModel.getUltimoElementoSecuenciaMaquina())
                    // Hacer registro de que se ha apretado el boton
                    Log.d(DatosSingleton.tag, "enviar, aumentar la ronda ${miViewModel.getRonda()}")
                }else{
                    Log.d(DatosSingleton.tag, "Secuencia incorrecta")
                    // Reiniciar la secuencia de la maquina
                    miViewModel.reiniciarSecuencia()
                    // Reiniciar la secuencia del usuario
                    miViewModel.reiniciarSecuenciaUsuario()
                    // Reiniciar la ronda
                    miViewModel.reiniciarRonda()
                    // Hacer registro de que se ha apretado el boton
                    Log.d(DatosSingleton.tag, "enviar, reiniciar la ronda ${miViewModel.getRonda()}")
                }
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
    /**
     * Metodo que junta los metodos que deberian de usarse en los onClick de los botones
     * @param color Int que indica el color que se va a añadir a la secuencia
     * @param colorCual String que indica el color del boton que se ha apretado
     * @param miViewModel MyViewModel que se usa para llamar a los metodos de la clase MyViewModel
     */
    fun botonApretado(color: Int, colorCual: String, miViewModel: MyViewModel) {
        // Incrementar la secuenncia de colores del usuario
        miViewModel.aumentarSecuenciaUsuario(color)
        // Hacer registro de que se ha apretado el boton
        Log.d(DatosSingleton.tag, "boton apretado : $colorCual")
        // todo cambiar el color del boton apretado por uno ligeramente mas claros


    }
    /*
    Esta linea debe de estar fuera del botton
    var context = LocalContext.current


    Linea para hacer un toast en un onClick
    Toast.makeText(context, "Hakiado", Toast.LENGTH_SHORT).show()



     */
}