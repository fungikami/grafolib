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
    println("\u001B[32mPrueba del Ciclo Euleriano: \u001B[0m\n")


    println("------------------------------------------------------------")

    println("\u001B[32mPrueba de las métricas de un grafo no dirigido: \u001B[0m\n")



    println("------------------------------------------------------------")

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

    println("------------------------------------------------------------")

    println("\u001B[32mPrueba de detección del ancestro común más bajo: \u001B[0m\n")



    println("------------------------------------------------------------")

    println("\u001B[32mPrueba del solucionador de 2-SAT: \u001B[0m\n")

}