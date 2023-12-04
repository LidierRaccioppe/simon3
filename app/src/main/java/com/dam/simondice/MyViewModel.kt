package com.dam.simondice

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * ViewModel del Juego
 */
class MyViewModel : ViewModel(){
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
    fun aumentarSecuenciaMaquina() {
        DatosSingleton.estado = Estado.SECUENCIA
        // Se le añade con el metodo add el color que se genera aleatoriamente usando el tamaño de la lista de colores como limite superior
        anadirColorSecuencia(generarNumeroAleatorio(Colores.values().size - 1))
        getSecuenciaMaquina()
    }
    /**
     * Aumentar la secuencia de colores del usuario
     */
    fun aumentarSecuenciaUsuario(color : Int) {
        DatosSingleton.estado = Estado.ENTRADA
        DatosSingleton.secuenciaUsuario.add(color)
        getSecuenciaUsuario()
    }
    /**
     * Obtiene el ultimo elemento de la secuencia del usuario
     */
    fun getUltimoElementoSecuenciaUsuario(): Int {
        return DatosSingleton.secuenciaUsuario.last()
    }
    /**
     * Comprueba si la secuencia del usuario es correcta
     * @return Boolean que indica si la secuencia del usuario es correcta
     */
    fun comprobarSecuenciaUsuario(): Boolean {
        DatosSingleton.estado = Estado.COMPROBANDO
        return DatosSingleton.secuencia == DatosSingleton.secuenciaUsuario
    }
    /**
     * Muestra la secuencia de colores del usuario
     */
    fun getSecuenciaUsuario() : MutableList<Int> {
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
     * Muestra la secuencia de colores y hace un log.d de la secuencia
     */
    fun getSecuenciaMaquina() : MutableList<Int> {
        DatosSingleton.estado = Estado.SECUENCIA
        Log.d(DatosSingleton.tag, "Maquina : "+DatosSingleton.secuencia.toString())
        return DatosSingleton.secuencia
    }
    /**
     * Muestra la secuencia de colores del usuario y hace un log.d de la secuencia
     */
    fun mostrarSecuenciaUsuario() : MutableList<Int> {
        Log.d(DatosSingleton.tag, "Usuario : " + DatosSingleton.secuenciaUsuario.toString())
        return DatosSingleton.secuenciaUsuario
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
     * Obtiene el ultimo elemento de la secuencia de la maquina
     * @return Int que indica el ultimo elemento de la secuencia de la maquina
     */
    fun getUltimoElementoSecuenciaMaquina(): Int {
        return DatosSingleton.secuencia.last()
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
    /**
     * Comprueba si la secuencia del usuario es correcta
     */
    fun comprobarSecuencia(): Boolean {
        return DatosSingleton.secuencia == DatosSingleton.secuenciaUsuario
    }

    /**
     * Oscurece el color
     */
    fun tintinearOscurecimiento(color: Color,factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }
    fun mostrarSecuenciaMaquina(time: Long) {
        Log.d("DijoSimon", "Mostramos la secuencia")
        viewModelScope.launch {
            for (i in DatosSingleton.secuencia) {
                Log.d("DijoSimon","Valor de la secuencia existente: ${DatosSingleton.secuencia}")
                DatosSingleton.colorAux= DatosSingleton.listaColores[i].value
                DatosSingleton.numeroDeColores[i].color.value= tintinearOscurecimiento(DatosSingleton.colorAux,0.5f)
                delay(time)
                DatosSingleton.numeroDeColores[i].color.value= DatosSingleton.colorAux
                delay(time)
                Log.d("DijoSimon", "Mostramos el color $i oscurecido")
            }
        }
    }
    fun mostrarSecuenciaMaquinaEjecutar(time:Long) = runBlocking {
        mostrarSecuenciaMaquina(time)
    }

    /**
     * Increment the user sequence
     */
    fun tintineaUsuarioBlancoMostrar(numColor: Int) = runBlocking {
        tintineaUsuarioBlanco(numColor)
    }
    fun tintineaUsuarioBlanco(color: Int) {
        Log.d("DijoSimon","Blanqueando")

        viewModelScope.launch {
            DatosSingleton.colorAux=DatosSingleton.listaColores[color].value
            DatosSingleton.listaColores[color].value= Color.White
            delay(75)
            DatosSingleton.listaColores[color].value= DatosSingleton.colorAux
        }
        Log.d("DijoSimon", "ilumando pulsado")
    }
    /**
     * Obtiene el estado del juego
     * @return Estado que indica el estado del juego
     */
    fun getEstado(): Estado {
        return DatosSingleton.estado
    }
    /**
     * Establece el estado del juego
     * @param estado Estado que indica el estado del juego
     */
    fun setEstado(estado: Estado) {
        DatosSingleton.estado = estado
    }
}