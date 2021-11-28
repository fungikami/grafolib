import ve.usb.grafoLib.*

val CARPETA = "pruebas"

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
    
    // val EULER = "$CARPETA/cicloEuleriano.txt"
    // val EULER2 = "$CARPETA/cicloEuleriano2.txt"
    val EULER = "$CARPETA/cicloEuleriano.txt"
    val EULER2 = "$CARPETA/cicloEuleriano2.txt"

    // Prueba del ciclo euleriano
    println("Prueba 1:")
    val g = GrafoDirigido(EULER, false)
    val euler = CicloEuleriano(g)
    println("  -Es un ciclo euleriano: ${euler.tieneCicloEuleriano()}") // true
    for (c in euler.obtenerCicloEuleriano()) { println(c) }

    println("\nPrueba 2:")
    val g2 = GrafoDirigido(EULER2, false)
    val euler2 = CicloEuleriano(g2)
    println("  -Es un ciclo euleriano: ${euler2.tieneCicloEuleriano()}") // true
    for (c in euler2.obtenerCicloEuleriano()) { println(c) }

}

fun pruebasMetricas() {
    println("\u001B[32mPrueba de las métricas de un grafo no dirigido: \u001B[0m\n")

    val METRICAS = "$CARPETA/metricas.txt"
    val METRICAS2 = "$CARPETA/metricas2.txt" 

    println("Prueba 1:")
    val g = GrafoNoDirigido(METRICAS, false)
    val met = MetricasDeGrafo(g)

    // Excentricidad
    println("   -Excentricidad(0) = ${met.excentricidad(0)}") // 3
    println("   -Excentricidad(1) = ${met.excentricidad(1)}") // 3
    println("   -Excentricidad(2) = ${met.excentricidad(2)}") // 2

    // Diámetro
    println("   -Diámetro = ${met.diametro()}")   // 3

    // Radio
    println("   -Radio = ${met.radio()}")     // 2
    println("   -Centro = ${met.centro()}")   // 2

    // Índice Wiener
    println("   -Índice Wiener = ${met.indiceWiener()}")   // 28
    // (1 + 2*4 + 3) + (1 + 2*3 + 3) + (1*3 + 2) + 1 

    println("\nPrueba 2:")
    val g2 = GrafoNoDirigido(METRICAS2, false)
    val met2 = MetricasDeGrafo(g2)

    // Excentricidad
    println("   -Excentricidad(0) = ${met2.excentricidad(0)}") // 3
    println("   -Excentricidad(1) = ${met2.excentricidad(1)}") // 3
    println("   -Excentricidad(2) = ${met2.excentricidad(2)}") // 2

    // Diámetro
    println("   -Diámetro = ${met2.diametro()}")   // 3

    // Radio
    println("   -Radio = ${met2.radio()}")     // 2
    println("   -Centro = ${met2.centro()}")   // 2

    // Índice Wiener
    println("   -Índice Wiener = ${met2.indiceWiener()}")   // 25

}

fun pruebasBipartito() {
    println("\u001B[32mPrueba de grafo bipartito: \u001B[0m\n")
    
    val BIPARTITO = "$CARPETA/bipartito.txt"
    val BIPARTITO2 = "$CARPETA/bipartito2.txt"

    // Prueba de grafo bipartito
    println("Prueba 1:")
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
    println("Prueba 1:")
    val g = GrafoDirigido(LCA, false)
    val lca = LCA(g)

    println("   -LCA(4, 3) = ${lca.obtenerLCA(4, 3)}") // 5
    println("   -LCA(5, 3) = ${lca.obtenerLCA(5, 3)}") // 1
    println("   -LCA(5, 4) = ${lca.obtenerLCA(5, 4)}") // 2
    println("   -LCA(1, 0) = ${lca.obtenerLCA(1, 0)}") // 0

    println("\nPrueba 2:")
    val g2 = GrafoDirigido(LCA2, false)
    val lca2 = LCA(g2)

    println("   -LCA(0, 1) = ${lca2.obtenerLCA(0, 1)}") // 1
    println("   -LCA(2, 4) = ${lca2.obtenerLCA(2, 4)}") // 6
    println("   -LCA(5, 6) = ${lca2.obtenerLCA(5, 6)}") // 6
    println("   -LCA(1, 2) = ${lca2.obtenerLCA(1, 2)}") // 2
}

fun pruebas2SAT() {
    println("\u001B[32mPrueba del solucionador de 2-SAT: \u001B[0m\n")

}