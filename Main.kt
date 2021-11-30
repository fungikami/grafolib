/**
 * Autor: Ka Fung & Christopher Gómez
 * Fecha: 30/Nov/2021.
 */

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
    try {
        pruebasCicloEuleriano()

        println("\n------------------------------------------------------------")

        pruebasMetricas()

        println("\n------------------------------------------------------------")
        
        pruebasBipartito()
        
        println("\n------------------------------------------------------------")
        
        pruebasLCA()

        println("\n------------------------------------------------------------")

        pruebas2SAT()

    } catch(e: NullPointerException) {
		println("Error fatal, operación con nulos.")
		e.printStackTrace()
	} catch(e: RuntimeException) {
		println("Error fatal, error durante la ejecución de una clase.")
		e.printStackTrace()
	} catch(e: IllegalArgumentException) {
		println("Error fatal, argumentos inválidos.")
		e.printStackTrace()	
	} catch(e: Exception) {
		println("Error fatal, el programa aborta.")
		e.printStackTrace()
    } finally {
		println("\n------------------------------------------------------------")
    }	
}

/**
 * Casos de prueba para la implementación de la clase CicloEuleriano
 * de la librería grafoLib.
 */
fun pruebasCicloEuleriano() {
    /* -------------------------------
    Prueba 1
    Prueba de cicloEuleriano.jpeg 
    Grafo de 6 lados.

    Prueba 2
    Prueba de cicloEuleriano2.jpeg 
    Grafo de 10 lados.

    Prueba 3
    Prueba de cicloEuleriano3.jpg
    Grafo de 28 lados.
    ------------------------------- */
    println("\n\u001B[32mCasos de prueba para CicloEuleriano: \u001B[0m")
    
    val PRUEBAS = arrayOf(
        "$CARPETA/cicloEuleriano.txt",
        "$CARPETA/cicloEuleriano2.txt",
        "$CARPETA/cicloEuleriano3.txt",
    )
    
    // Prueba del ciclo euleriano
    PRUEBAS.forEachIndexed { i, prueba ->
        println("\nPrueba ${i + 1}:\n($prueba)")
        val g = GrafoDirigido(prueba, false)
        val euler = CicloEuleriano(g)
        val esEuleriano = euler.tieneCicloEuleriano()
        println("  -Es un grafo euleriano: ${esEuleriano}") // true

        if (esEuleriano) {
            val ciclo = euler.obtenerCicloEuleriano()
            val n = ciclo.count()

            if (esEuleriano) {
                // Verificación de correctitud del circuito obtenido
                var sumideroAnterior = ciclo.first().fuente()
                var arcoAparece = mutableSetOf<Arco>()

                ciclo.forEach {
                    if (!arcoAparece.add(it)) {
                        println("  -Error: No se obtuvo un ciclo euleriano.")
                        return@forEach
                    }

                    val fuenteActual = it.fuente()
                    if (sumideroAnterior != fuenteActual) {
                        println("  -Error: No se obtuvo un ciclo euleriano.")
                        return@forEach
                    }
                    sumideroAnterior = it.sumidero()
                }

                val cicloStr = ciclo.joinToString(separator = " -> ") { 
                    "${it.fuente()}"
                }.plus(" -> ${ciclo.last().sumidero()}")
                println("  -Circuito euleriano: $cicloStr")
                
                println("   -Lados del grafo: ${g.obtenerNumeroDeLados()}")
                println("   -Lados del ciclo: $n")
            }

        }
    }
}

/**
 * Casos de prueba para la implementación de la clase MetricasDeGrafo
 * de la librería grafoLib.
 */
fun pruebasMetricas() {
    /* -------------------------------
    Prueba 1
    Prueba de metricas.png
    Excentricidades: [3, 3, 2, 3, 3, 2, 3]
    Diámetro: 3
    Radio: 2
    Centro: 2
    Índice Wiener: 40
    
    Prueba 2
    Prueba de metricas2.png
    Excentricidades: [5, 3, 4, 6, 6, 4, 3, 5, 5, 4, 4, 6]
    Diámetro: 6
    Radio: 3
    Centro: 1
    Índice Wiener: 180
    ------------------------------- */
    println("\u001B[32mCasos de prueba para MetricasDeGrafo: \u001B[0m")

    val PRUEBAS = arrayOf(
        "$CARPETA/metricas.txt",
        "$CARPETA/metricas2.txt",
    )

    PRUEBAS.forEachIndexed { i, prueba ->    
        println("\nPrueba ${i + 1}:\n($prueba)")
        val g = GrafoNoDirigido(prueba, false)
        val met = MetricasDeGrafo(g)
        
        // Excentricidad
        for (j in 0 until g.obtenerNumeroDeVertices()) {
            println("   -Excentricidad($j) = ${met.excentricidad(j)}")
        }
        
        // Diámetro
        println("   -Diámetro = ${met.diametro()}")

        // Radio
        println("   -Radio = ${met.radio()}")  
        println("   -Centro = ${met.centro()}")

        // Índice Wiener
        println("   -Índice Wiener = ${met.indiceWiener()}")
    }
}

/**
 * Casos de prueba para la implementación de la clase DosColoreable
 * de la librería grafoLib.
 */
fun pruebasBipartito() {
    /* -------------------------------
    Prueba 1
    Prueba del bipartito.png
    Tiene ciclo euleriano: true
    
    Prueba 2
    Prueba del bipartito2.png
    Tiene ciclo euleriano: true (si no se agrega arista (1, 3))
    ------------------------------- */

    println("\u001B[32mCasos de prueba para DosColoreable:\u001B[0m")

    val BIPARTITO = "$CARPETA/bipartito.txt"
    val BIPARTITO2 = "$CARPETA/bipartito2.txt"

    // Prueba de grafo bipartito
    println("\nPrueba 1:\n($BIPARTITO)")
    val g = GrafoNoDirigido(BIPARTITO, false)
    val bipartito = DosColoreable(g)
    println("   -El grafo es bipartito: ${bipartito.esDosColoreable()}")

    println("\nPrueba 2:\n($BIPARTITO2)")
    val g2 = GrafoNoDirigido(BIPARTITO2, false)
    val bipartito2 = DosColoreable(g2)
    println("   -El grafo es bipartito: ${bipartito2.esDosColoreable()}")
    
    println("   -Se agrega un lado que rompe la condición de bipartición.")

    // Si se agrega, el grafo no es bipartito
    g2.agregarArista(Arista(1, 3)) 
    val noBipartito = DosColoreable(g2)
    println("   -El grafo es bipartito: ${noBipartito.esDosColoreable()}")
}

/**
 * Casos de prueba para la implementación de la clase LCA 
 * de la librería grafoLib.
 */
fun pruebasLCA() {
    /* -------------------------------
    Prueba 1
    Prueba del enunciado del proyecto 1
    
    Prueba 2
    https://stackoverf/home/fung/Downloads/lca2.pnglow.com/questions/14865081/algorithm-to-find-lowest-common-ancestor-in-directed-acyclic-graph 
    ------------------------------- */
    
    println("\u001B[32mCasos de prueba para LCA: \u001B[0m")

    val LCA = "$CARPETA/lca.txt"
    val LCA2 = "$CARPETA/lca2.txt"

    // Prueba del ancestro común más bajo
    println("\nPrueba 1:\n($LCA)")
    val g = GrafoDirigido(LCA, false)
    val lca = LCA(g)

    println("   -LCA(4, 3) = ${lca.obtenerLCA(4, 3)}") //  5
    println("   -LCA(5, 3) = ${lca.obtenerLCA(5, 3)}") //  1
    println("   -LCA(5, 4) = ${lca.obtenerLCA(5, 4)}") //  2
    println("   -LCA(1, 0) = ${lca.obtenerLCA(1, 0)}") // -1
    println("   -LCA(1, 1) = ${lca.obtenerLCA(1, 1)}") //  2

    println("\nPrueba 2:\n($LCA2)")
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
 * Casos de prueba para la implementación de la clas Sol2SAT de la librería 
 * grafoLib.
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
    println("\u001B[32mCasos de prueba para Sol2SAT: \u001B[0m")

    // Prueba del solucionador de 2SAT
    val PRUEBAS = arrayOf(
        "$CARPETA/sol2sat.txt",
        "$CARPETA/sol2sat2.txt",
        "$CARPETA/sol2sat3.txt",
    )

    PRUEBAS.forEachIndexed { i, prueba ->
        println("\nPrueba ${i + 1}:\n($prueba)")
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