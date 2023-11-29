package com.dam.simondice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Clase que contiene los datos que se van a usar en el juego
 * @property ronda Int que indica la ronda en la que estamos
 * @property secuencia List<Int> que contiene la secuencia de colores que se van a mostrar
 * @property secuenciaUsuario List<Int> que contiene la secuencia de colores que ha introducido el usuario
 * @property record Int que indica el record del juego
 * @property estado Enum que indica el estado del juego
 */
object DatosSingleton {
    // la variable ronda debe de ser un int que reaccione a los cambios que se le hagan en la UI
    var ronda = mutableStateOf<Int>(0)
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estado = Estado.INICIO
    val tag = "DijoSimon"
}
/**
 * Enum que indica el estado del juego
 */
enum class Estado {
    INICIO, SECUENCIA, USUARIO, ESPERANDO, ENTRADA, COMPROBANDO, FINALIZADO
}
/**
 * Color que se va a usar en el juego
 */
enum class Colores (color: MutableState<Color>) {
    ROJO(mutableStateOf(Color.Red)),
    VERDE(mutableStateOf(Color.Green)),
    AZUL(mutableStateOf(Color.Blue)),
    AMARILLO(mutableStateOf(Color.Yellow))
}
