import ve.usb.grafoLib.*

CARPETA = "pruebas"

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
    
    val EULER = "$CARPETA/cicloEuleriano.txt"
    val EULER2 = "$CARPETA/cicloEuleriano2.txt"

    // Prueba del ciclo euleriano
    println("\nPrueba 1:")
    val g = GrafoDirigido(EULER, false)
    val euler = CicloEuleriano(g)
    println(" ${g.tieneCicloEuleriano()}") // true
    for (c in euler.obtenerCicloEuleriano()) { println(c) }

    println("\nPrueba 2:")
    val g2 = GrafoDirigido(EULER2, false)
    val euler2 = CicloEuleriano(g2)
    println(" ${g2.tieneCicloEuleriano()}") // true
    for (c in euler2.obtenerCicloEuleriano()) { println(c) }

}

fun pruebasMetricas() {
    println("\u001B[32mPrueba de las métricas de un grafo no dirigido: \u001B[0m\n")

    val METRICAS = "$CARPETA/metricas.txt"
    //val METRICAS2 = 

    println("\nPrueba 1:")
    val g = GrafoNoDirigido(METRICAS, false)
    val met = MetricasDeGrafo(g)

    // Excentricidad
    println("   -Excentricidad(0) = ${met.excentricidad(1)}") // 3
    println("   -Excentricidad(1) = ${met.excentricidad(1)}") // 3
    println("   -Excentricidad(2) = ${met.excentricidad(1)}") // 2

    // Diámetro
    println("   -Diámetro = ${met.diametro()}")   // 3

    // Radio
    println("   -Radio = ${met.radio()}")     // 2
    println("   -Centro = ${met.centro()}")   // 2

    // Índice Wiener
    println("   -Índice Wiener = ${met.centro()}")   // 28
    // (1 + 2*4 + 3) + (1 + 2*3 + 3) + (1*3 + 2) + 1 

    println("\nPrueba 2:")

}

fun pruebasBipartito() {
    println("\u001B[32mPrueba de grafo bipartito: \u001B[0m\n")
    
    val BIPARTITO = "$CARPETA/bipartito.txt"
    val BIPARTITO2 = "$CARPETA/bipartito2.txt"

    // Prueba de grafo bipartito
    println("\nPrueba 1:")
    val g = GrafoNoDirigido(BIPARTITO, false)
    val bipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${bipartito.esDosColoreable()}\n")
    
    println("   -Se agrega un lado que rompe la condición de bipartición.\n")

    // Si se agrega, el grafo no es bipartito
    g.agregarArista(Arista(1, 3)) 
    val noBipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${noBipartito.esDosColoreable()}\n")

    println("\nPrueba 2:")
    val g2 = GrafoNoDirigido(BIPARTITO2, false)
    val bipartito2 = DosColoreable(g2)
    println("   -El grafo 2 es bipartito: ${bipartito2.esDosColoreable()}")
}

fun pruebasLCA() {
    println("\u001B[32mPrueba de detección del ancestro común más bajo: \u001B[0m\n")

    val LCA = "$CARPETA/lca.txt"
    val LCA2 = "$CARPETA/lca2.txt"

    // Prueba del ancestro común más bajo
    println("\nPrueba 1:")
    val g = GrafoNoDirigido(LCA, false)
    val lca = LCA(g)

    println("   -LCA(4, 3) = ${lca.obtenerLCA(4, 3)}") // 5
    println("   -LCA(5, 3) = ${lca.obtenerLCA(5, 3)}") // 1
    println("   -LCA(5, 4) = ${lca.obtenerLCA(5, 4)}") // 2
    println("   -LCA(1, 0) = ${lca.obtenerLCA(5, 4)}") // 0

    println("\nPrueba 2:")
    val g2 = GrafoNoDirigido(LCA2, false)
    val lca2 = LCA(g2)

    println("   -LCA(0, 1) = ${lca2.obtenerLCA(0, 1)}") // 0
    println("   -LCA(2, 4) = ${lca2.obtenerLCA(2, 4)}") // 1
    println("   -LCA(5, 6) = ${lca2.obtenerLCA(5, 6)}") // 1
    println("   -LCA(1, 2) = ${lca2.obtenerLCA(1, 2)}") // 0
}

fun pruebas2SAT() {
    println("\u001B[32mPrueba del solucionador de 2-SAT: \u001B[0m\n")

}