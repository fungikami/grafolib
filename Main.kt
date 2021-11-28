import ve.usb.grafoLib.*

/**
 * Universidad Simón Bolívar.
 * Algoritmos y Estructuras III. Prof. Guillermo Palma.
 *
 * Descripción: 
 * 
 * 
 */
fun main() {

    pruebasCicloEuleriano()

    println("------------------------------------------------------------")

    pruebasMetricas()

    println("------------------------------------------------------------")
    
    pruebasBipartito()
    
    println("------------------------------------------------------------")
    
    pruebasLCA()

    println("------------------------------------------------------------")

    pruebas2SAT()
}

fun pruebasCicloEuleriano() {
    println("\u001B[32mPrueba del Ciclo Euleriano: \u001B[0m\n")
    
    val EULER = "cicloEuleriano.txt"
    val EULER2 = "cicloEuleriano2.txt"

    // Prueba del ciclo euleriano
    val g = GrafoDirigido(EULER, false)
    val euler = CicloEuleriano(g)
    println(" ${g.tieneCicloEuleriano()}") // true
    for (c in euler.obtenerCicloEuleriano()) {println(c)}

    val g2 = GrafoDirigido(EULER2, false)
    val euler2 = CicloEuleriano(g2)
    println(" ${g2.tieneCicloEuleriano()}") // true
    for (c in euler2.obtenerCicloEuleriano()) {println(c)}

}

fun pruebasMetricas() {
    println("\u001B[32mPrueba de las métricas de un grafo no dirigido: \u001B[0m\n")

}

fun pruebasBipartito() {
    println("\u001B[32mPrueba de grafo bipartito: \u001B[0m\n")
    
    val BIPARTITO = "bipartito.txt"
    val BIPARTITO2 = "bipartito2.txt"

    // Prueba de grafo bipartito
    val g = GrafoNoDirigido(BIPARTITO, false)
    val bipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${bipartito.esDosColoreable()}\n")
    
    println("   -Se agrega un lado que rompe la condición de bipartición.\n")

    // Si se agrega, el grafo no es bipartito
    g.agregarArista(Arista(1, 3)) 
    val noBipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${noBipartito.esDosColoreable()}\n")

    val g2 = GrafoNoDirigido(BIPARTITO2, false)
    val bipartito2 = DosColoreable(g2)
    println("   -El grafo 2 es bipartito: ${bipartito2.esDosColoreable()}")
}

fun pruebasLCA() {
    println("\u001B[32mPrueba de detección del ancestro común más bajo: \u001B[0m\n")
}

fun pruebas2SAT() {
    println("\u001B[32mPrueba del solucionador de 2-SAT: \u001B[0m\n")
}