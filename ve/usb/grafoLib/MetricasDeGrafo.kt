package ve.usb.grafoLib

import java.util.LinkedList

/*
 Computación de varias métricas sobre un grafo no dirigido conexo.
 Se recibe como entrada un grafo no dirigido conexo, en caso de que
 el grafo no sea conexo, entonces se lanza un RuntimeException.
*/
public class MetricasDeGrafo(val g: GrafoNoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private var color = Array<Color>(n) { Color.BLANCO }
    
    private val excentricidades = IntArray(n)
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
            excentricidades[i] = bfs(g, i)

            if (excentricidades[i] < radio) {
                radio = excentricidades[i]
                centro = i
            }

            if (excentricidades[i] > diametro) {
                diametro = excentricidades[i]
            }
        }
    }

    private fun esConexo() : Boolean {
        bfs(g, 0)
        color.forEach { if (it != Color.NEGRO) return false }
        return true
    }

    private fun bfs(g: Grafo, s: Int) : Int {
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
    fun diametro() : Int = diametro

    // Computa el radio de un grafo 
    // El radio de un grafo consiste en el menor valor de excentricidad 
    // que se puede obtener de los vértices de un grafo.
    fun radio() : Int = radio

    // Retorna el vértice centro de un grafo 
    // El centro de un grafo es el vértice v con el que se 
    // obtiene el valor del radio de un grafo.
    fun centro() : Int = centro

    // Computa el índice Wiener de un grafo
    // El índice Wiener de un grafo es la suma de todos los caminos mas
    // cortos entre todos los pares distintos de vértices de un grafo.
    fun indeceWiener() : Int {
        //TODO
        return -1
    }
}


