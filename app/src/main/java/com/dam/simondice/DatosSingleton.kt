package com.dam.simondice

/**
 * Clase que contiene los datos que se van a usar en el juego
 * @property ronda Int que indica la ronda en la que estamos
 * @property secuencia List<Int> que contiene la secuencia de colores que se van a mostrar
 * @property secuenciaUsuario List<Int> que contiene la secuencia de colores que ha introducido el usuario
 * @property record Int que indica el record del juego
 * @property estado Enum que indica el estado del juego
 */
object DatosSingleton {
    var ronda = 0
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estado = Estado.INICIO
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
enum class Color () {
    ROJO,
    VERDE,
    AZUL,
    AMARILLO
}
