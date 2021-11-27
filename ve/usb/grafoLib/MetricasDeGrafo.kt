package ve.usb.grafoLib

import java.util.LinkedList

/** 
 * Clase que computa varias métricas sobre un grafo no dirigido conexo.
 * 
 * @throws [RuntimeException] Si el grafo de entrada no es conexo.
 *
 */ 
public class MetricasDeGrafo(val g: GrafoNoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private var color = Array<Color>(n) { Color.BLANCO }
    
    private val excentricidad = IntArray(n)
    private var diametro = Integer.MIN_VALUE
    private var radio = Integer.MAX_VALUE
    private var centro = 0

    init {
        if (!esConexo()) throw RuntimeException("El grafo no es conexo.")

        /* Calcula las excentricidades, radio y diametro
        La excentricidad de un vértice s consiste en la longitud del camino
        más corto desde s hasta un vértice t, tal que t es el vértice con 
        el camino más corto de mayor longitud desde s. */
        for (i in 0 until n) {
            color = Array<Color>(n) { Color.BLANCO }
            excentricidad[i] = bfsExcentricidad(g, i)

            // Mínimo excentricidad
            if (excentricidad[i] < radio) {
                radio = excentricidad[i]
                centro = i
            }

            // Máximo excentricidad
            if (excentricidad[i] > diametro) {
                diametro = excentricidad[i]
            }
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
    

    private fun bfsExcentricidad(g: Grafo, s: Int): Int {
        val dist = IntArray(n) { Integer.MAX_VALUE }
        
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

    // Computa el diámetro de un grafo 
    // El diámetro de un grafo consiste en el mayor valor de 
    // excentricidad que se puede obtener de los vértices de un grafo.
    fun diametro(): Int = diametro

    // Computa el radio de un grafo 
    // El radio de un grafo consiste en el menor valor de excentricidad 
    // que se puede obtener de los vértices de un grafo.
    fun radio(): Int = radio

    // Retorna el vértice centro de un grafo 
    // El centro de un grafo es el vértice v con el que se 
    // obtiene el valor del radio de un grafo.
    fun centro(): Int = centro

    // Computa el índice Wiener de un grafo
    // El índice Wiener de un grafo es la suma de todos los caminos mas
    // cortos entre todos los pares distintos de vértices de un grafo.
    fun indiceWiener(): Int {
        //TODO
        return -1
    }
}


