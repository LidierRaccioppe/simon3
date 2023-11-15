package com.dam.simondice

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
     * Aumenta la ronda en la que estamos
     */
    fun aumentarRonda() {
        DatosSingleton.ronda++
    }

    /**
     * Reinicia la ronda en la que estamos
     */
    fun reiniciarRonda() {
        DatosSingleton.ronda = 0
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
        anadirColorSecuencia(generarNumeroAleatorio(4))
        mostrarSecuencia()
        val color = generarNumeroAleatorio(4)
        DatosSingleton.estado = Estado.SECUENCIA
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
    }// TODO recordar añadir el metodo para el estado finalizado
    /**
     * Muestra la secuencia de colores
     */
    fun mostrarSecuencia() {
        DatosSingleton.estado = Estado.SECUENCIA
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
        return DatosSingleton.ronda > DatosSingleton.record
    }
    /**
     * Actualiza el record
     */
    fun actualizarRecord() {
        DatosSingleton.record = DatosSingleton.ronda
    }
    /**
     * Devuelve la ronda actual
     */
    fun getRonda(): Int {
        return DatosSingleton.ronda
    }


}