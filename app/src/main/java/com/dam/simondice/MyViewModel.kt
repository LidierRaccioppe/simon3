package com.dam.simondice

import android.util.Log

/**
 * ViewModel del Juego
 */
class MyViewModel {
    /**
     * Inicializo el juego
     */

    /**
     * Función que genera un número aleatorio entre 0 y el numeroLimiteSuperior
     * @param numeroLimiteSuperior Int que indica el límite superior del número aleatorio
     * @return Int que indica el número aleatorio generado
     */
    fun generarNumeroAleatorio(numeroLimiteSuperior: Int): Int {
        return (0..numeroLimiteSuperior).random()
    }

    /**
     * Añade un color a la secuencia del usuario
     * @param color Int que indica el color que se va a añadir a la secuencia del usuario
     */
    fun anadirColorSecuenciaUsuario(color: Int) {
        DatosSingleton.secuenciaUsuario.add(color)
    }

    /**
     * Aumenta la ronda en la que estamos en 1
     */
    fun aumentarRonda() {
        DatosSingleton.ronda.value++
    }

    /**
     * Reinicia la ronda en la que estamos
     */
    fun reiniciarRonda() {
        DatosSingleton.ronda.value = 0
    }
    /**
     * Añade un color a la secuencia
     * @param color Int que indica el color que se va a añadir a la secuencia
     */
    fun anadirColorSecuencia(color: Int) {
        DatosSingleton.secuencia.add(color)
    }
    /**
     * Aumenta la secuencia de colores
     * muestra al usuario la secuencia de colores
     */
    fun aumentarSecuencia() {
        DatosSingleton.estado = Estado.SECUENCIA
        // Se le añade con el metodo add el color que se genera aleatoriamente usando el tamaño de la lista de colores como limite superior
        anadirColorSecuencia(generarNumeroAleatorio(Color.values().size - 1))
        getSecuencia()
    }
    /**
     * Aumentar la secuencia de colores del usuario
     */
    fun aumentarSecuenciaUsuario(color : Int) {
        DatosSingleton.estado = Estado.ENTRADA
        DatosSingleton.secuenciaUsuario.add(color)
    }
    /**
     * Comprueba si la secuencia del usuario es correcta
     * @return Boolean que indica si la secuencia del usuario es correcta
     */
    fun comprobarSecuenciaUsuario(): Boolean {
        DatosSingleton.estado = Estado.COMPROBANDO
        aumentarRonda()
        return DatosSingleton.secuencia == DatosSingleton.secuenciaUsuario
    }
    /**
     * Muestra la secuencia de colores del usuario
     */
    fun getSecuenciaUsuario() : MutableList<Int> {
        DatosSingleton.estado = Estado.USUARIO
        Log.d(DatosSingleton.tag, "Usuario : " + DatosSingleton.secuenciaUsuario.toString())
        return DatosSingleton.secuenciaUsuario
    }
    /**
     * Getter de la secuencia de colores
     */
    fun getSecuenciaColores() : MutableList<Int> {
        return DatosSingleton.secuencia
    }
    /**
     * Getter de la secuencia de colores del usuario
     */
    fun getSecuenciaColoresUsuario() : MutableList<Int> {
        return DatosSingleton.secuenciaUsuario
    }
    /**
     * Muestra la secuencia de colores
     */
    fun getSecuencia() : MutableList<Int> {
        DatosSingleton.estado = Estado.SECUENCIA
        Log.d(DatosSingleton.tag, "Maquina : "+DatosSingleton.secuencia.toString())
        return DatosSingleton.secuencia
    }
    /**
     * Reinicia la secuencia
     */
    fun reiniciarSecuencia() {
        DatosSingleton.secuencia.clear()
    }
    /**
     * Reinicia la secuencia del usuario
     */
    fun reiniciarSecuenciaUsuario() {
        DatosSingleton.secuenciaUsuario.clear()
    }
    /**
     * Reinicia el estado del juego
     */
    fun reiniciarEstado() {
        DatosSingleton.estado = Estado.INICIO
    }
    /**
     * Reinicia el record del juego
     */
    fun reiniciarRecord() {
        DatosSingleton.record = 0
    }
    /**
     * Reinicia el juego
     */
    fun reiniciarJuego() {
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        reiniciarEstado()
        reiniciarRecord()
    }
    /**
     * Comprueba si el record se ha superado
     * @return Boolean que indica si el record se ha superado
     */
    fun comprobarRecord(): Boolean {
        return DatosSingleton.ronda.value > DatosSingleton.record
    }
    /**
     * Actualiza el record
     */
    fun actualizarRecord() {
        DatosSingleton.record = DatosSingleton.ronda.value
    }
    /**
     * Devuelve la ronda actual
     */
    fun getRonda(): Int {
        return DatosSingleton.ronda.value
    }


}