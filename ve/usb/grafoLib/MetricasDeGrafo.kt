/**
 * Autor: Ka Fung & Christopher Gómez
 * Fecha: 30/Nov/2021.
 */

package ve.usb.grafoLib

import java.util.LinkedList

/** 
 * Clase que computa el diámetro, el radio, el centro , el índice de Wiener
 * y las excentricidades cada vértices de un grafo no dirigido conexo.
 * 
 * @param [g]: grafo no dirigido sobre el que se calcula sus métricas.
 *
 * @throws [RuntimeException] Si el grafo de entrada no es conexo.
 */ 
public class MetricasDeGrafo(val g: GrafoNoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }
    private val dist = IntArray(n) { Integer.MAX_VALUE }
    
    private val excentricidad = IntArray(n)
    private var diametro = Integer.MIN_VALUE
    private var radio = Integer.MAX_VALUE
    private var centro = 0
    private var wiener = 0

    init {
        if (!esConexo()) throw RuntimeException("El grafo no es conexo.")

        // Calcula las excentricidades, radio y diametro
        for (u in 0 until n) {
            for (i in 0 until n) color[i] = Color.BLANCO
            for (i in 0 until n) dist[i] = Integer.MAX_VALUE

            excentricidad[u] = bfsExcentricidad(g, u)

            // Mínimo excentricidad
            if (excentricidad[u] < radio) {
                radio = excentricidad[u]
                centro = u
            }

            // Máximo excentricidad
            if (excentricidad[u] > diametro) {
                diametro = excentricidad[u]
            }

            // Calcular las distancias de s hasta los demas vertices
            for (v in u + 1 until n) wiener += dist[v]
        }
    }

    /**
     * Retorna un booleano indicando si [g] es un grafo conexo
     * 
     * Tiempo de ejecución: O(|E|).
     * Precondición: True.
     * Postcondición: [esConexo] es: -True si [g] es conexo.
     *                               -False de otra forma.
     */
    private fun esConexo(): Boolean {
        bfsExcentricidad(g, 0)
        return color.all{ it == Color.NEGRO }
    }
    
    /**
     * Explora iterativamente el grafo [g] con BFS desde el
     * vértice [s], para hallar la excentricidad de [s].
     * 
     * Tiempo de ejecución: O(|E|).
     * Precondición: [g] es un grafo.
     *               [s] es un vértice perteneciente al grafo.
     * Postcondición: [bfsExcentricidad] es la longitud del camino
     *                más largo desde [s] hasta cualquier otro vértice.
     */
    private fun bfsExcentricidad(g: Grafo, s: Int): Int {
        dist[s] = 0
        color[s] = Color.GRIS
        val Q = LinkedList<Int>()
        Q.add(s)
        
        var excent = 0

        while (!Q.isEmpty()) {
            val u = Q.poll()

            g.adyacentes(u).forEach {
                // Se selecciona el adyacente
                val v = it.elOtroVertice(u)

                if (color[v] == Color.BLANCO) {
                    color[v] = Color.GRIS
                    dist[v] = dist[u] + 1
                    Q.add(v)
                    
                    // Actualizo excentricidad
                    if (dist[v] > excent) excent = dist[v]
                }
            }
            color[u] = Color.NEGRO
        }

        return excent
    }

    /**
     * Retorna la excentricidad del vértice [s]. 
     *
     * @throws [RuntimeException] El vértice está fuera del intervalo [0..|V|).
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: [v] pertenece al conjunto de vértices del grafo.
     * Postcondición: [excentricidad] es la longitud del camino
     *                más largo desde [s] hasta cualquier otro vértice.
     */
    fun excentricidad(s: Int) : Int {
        g.chequearVertice(s)
        return excentricidad[s]
    } 

    /**
     * Retorna el diámetro del grafo [g]. 
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición:   [diametro] es la excentricidad del grafo [g],
     *                  es el mayor valor de excentricidad que se 
     *                  puede obtener de los vértices del grafo [g].
     */
    fun diametro(): Int = diametro

    /**
     * Retorna el radio del grafo [g]. 
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición:   [radio] es la excentricidad del grafo [g],
     *                  es el menor valor de excentricidad que se 
     *                  puede obtener de los vértices del grafo [g].
     */
    fun radio(): Int = radio

    /**
     * Retorna el centro del grafo [g]. 
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición:   [centro] es el vértice v con el que el menor valor  
     *                  de excentricidad que se puede obtener de los
     *                  vértices del grafo [g].
     */
    fun centro(): Int = centro

    /**
     * Retorna el índice Wiener de un grafo [g]. 
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición:   [indiceWiener] es la suma de todos los caminos más
     *                  cortos entre todos los pares distintos de vértices
     *                  de un grafo [g].
     */
    fun indiceWiener(): Int = wiener
}