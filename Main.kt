

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

    println("\n------------------------------------------------------------")

    pruebasMetricas()

    println("\n------------------------------------------------------------")
    
    pruebasBipartito()
    
    println("\n------------------------------------------------------------")
    
    pruebasLCA()

    println("\n------------------------------------------------------------")

    pruebas2SAT()
}

/**
 * Implementación que prueba la clase CicloEuleriano
 * de la librería ve.usb.grafoLib.
 */
fun pruebasCicloEuleriano() {
    println("\u001B[32mPrueba del Ciclo Euleriano: \u001B[0m")
    
    val PRUEBAS = arrayOf(
        "$CARPETA/cicloEuleriano.txt",
        "$CARPETA/cicloEuleriano2.txt",
        "$CARPETA/cicloEuleriano3.txt",
    )
    
    // Prueba del ciclo euleriano
    PRUEBAS.forEachIndexed { i, prueba ->
        println("\nPrueba ${i+1}:")
        val g = GrafoDirigido(prueba, false)
        val euler = CicloEuleriano(g)
        val eulerBool = euler.tieneCicloEuleriano()
        println("  -Es un ciclo euleriano: ${eulerBool}") // true
        if (eulerBool) println("  ${euler.obtenerCicloEuleriano()}")
    }
}

/**
 * Implementación que prueba la clase MetricasDeGrafo
 * de la librería ve.usb.grafoLib.
 */
fun pruebasMetricas() {
    println("\u001B[32mPrueba de las métricas de un grafo no dirigido: \u001B[0m")

    val PRUEBAS = arrayOf(
        "$CARPETA/metricas.txt",
        "$CARPETA/metricas2.txt",
    )

    val METRICAS = "$CARPETA/metricas.txt"
    val METRICAS2 = "$CARPETA/metricas2.txt"

    println("\nPrueba 1:")
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
    println("   -Índice Wiener = ${met.indiceWiener()}")   // 40

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

/**
 * Implementación que prueba la clase DosColoreable
 * de la librería ve.usb.grafoLib.
 */
fun pruebasBipartito() {
    /* -------------------------------
    Prueba 1
    Prueba del enunciado del proyecto 1
    Tiene ciclo euleriano: true
    
    Prueba 2
    https://www.techiedelight.com/determine-given-graph-bipartite-graph-using-dfs/ 
    Tiene ciclo euleriano: true (si no se agrega arista (1, 3))
    ------------------------------- */

    println("\u001B[32mPrueba de grafo bipartito: \u001B[0m")

    val BIPARTITO = "$CARPETA/bipartito.txt"
    val BIPARTITO1 = "$CARPETA/bipartito1.txt"
    val BIPARTITO2 = "$CARPETA/bipartito2.txt"

    // Prueba de grafo bipartito
    println("\nPrueba 1:")
    val g = GrafoNoDirigido(BIPARTITO, false)
    val bipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${bipartito.esDosColoreable()}")

    println("\nPrueba 2:")
    val g1 = GrafoNoDirigido(BIPARTITO1, false)
    val bipartito1 = DosColoreable(g1)
    println("   -El grafo es bipartito: ${bipartito1.esDosColoreable()}")
    
    println("   -Se agrega un lado que rompe la condición de bipartición.")

    // Si se agrega, el grafo no es bipartito
    g1.agregarArista(Arista(1, 3)) 
    val noBipartito = DosColoreable(g1)
    println("   -El grafo es bipartito: ${noBipartito.esDosColoreable()}")

    println("\nPrueba 3:")
    val g2 = GrafoNoDirigido(BIPARTITO2, false)
    val bipartito2 = DosColoreable(g2)
    println("   -El grafo 2 es bipartito: ${bipartito2.esDosColoreable()}")
}

/**
 * Implementación que prueba la clase LCA 
 * de la librería ve.usb.grafoLib.
 */
fun pruebasLCA() {
    /* -------------------------------
    Prueba 1
    Prueba del enunciado del proyecto 1
    
    Prueba 2
    https://stackoverflow.com/questions/14865081/algorithm-to-find-lowest-common-ancestor-in-directed-acyclic-graph 
    ------------------------------- */
    
    println("\u001B[32mPrueba de detección del ancestro común más bajo: \u001B[0m")

    val LCA = "$CARPETA/lca.txt"
    val LCA2 = "$CARPETA/lca2.txt"

    // Prueba del ancestro común más bajo
    println("\nPrueba 1:")
    val g = GrafoDirigido(LCA, false)
    val lca = LCA(g)

    println("   -LCA(4, 3) = ${lca.obtenerLCA(4, 3)}") //  5
    println("   -LCA(5, 3) = ${lca.obtenerLCA(5, 3)}") //  1
    println("   -LCA(5, 4) = ${lca.obtenerLCA(5, 4)}") //  2
    println("   -LCA(1, 0) = ${lca.obtenerLCA(1, 0)}") // -1
    println("   -LCA(1, 1) = ${lca.obtenerLCA(1, 1)}") //  2

    println("\nPrueba 2:")
    val g2 = GrafoDirigido(LCA2, false)
    val lca2 = LCA(g2)

    println("   -LCA(0, 1) = ${lca2.obtenerLCA(0, 1)}") //  2
    println("   -LCA(2, 4) = ${lca2.obtenerLCA(2, 4)}") //  6
    println("   -LCA(5, 6) = ${lca2.obtenerLCA(5, 6)}") // -1
    println("   -LCA(1, 2) = ${lca2.obtenerLCA(1, 2)}") //  3
    println("   -LCA(1, 1) = ${lca2.obtenerLCA(1, 1)}") //  2
    println("   -LCA(1, 7) = ${lca2.obtenerLCA(1, 7)}") // -1
}

/**
 * Implementación que prueba la clase Sol2SAT de la librería 
 * ve.usb.grafoLib.
 */
fun pruebas2SAT() {
    /* -------------------------------
    Prueba 1
    Prueba del enunciado del proyecto 1
    Tiene asignación: true
    Asignación: [false, false, false]

    Prueba 2
    https://www.geeksforgeeks.org/2-satisfiability-2-sat-problem/
    Tiene asignación: false
    Asignación: []

    Prueba 3
    https://zerobone.net/blog/cs/hornsat-2sat-np-complete/
    Tiene asignación: true
    Asignación: [true, false, true, false]
    ------------------------------- */
    println("\u001B[32mPrueba del solucionador de 2-SAT: \u001B[0m")

    // Prueba del solucionador de 2SAT
    val PRUEBAS = arrayOf(
        "$CARPETA/sol2sat.txt",
        "$CARPETA/sol2sat2.txt",
        "$CARPETA/sol2sat3.txt",
    )

    PRUEBAS.forEachIndexed { i, prueba ->
        println("\nPrueba ${i+1}:")
        val sol = Sol2SAT(prueba)
        val asig = sol.tieneAsignacionVerdadera()
        
        println("   Tiene asignación verdadera: $asig")

        if (asig) {
            println("   Asignación:")
            var j = 0
            sol.asignacion().forEach{
                println("      x$j = $it")
                j++
            }
        }
    }
    
}