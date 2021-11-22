package ve.usb.grafoLib

/*
 Determina si un grafo es 2-coloreable o bipartito. El algoritmo se ejecutan 
 al invocar el constructor de la clase. 
 */
public class DosColoreable(val g: GrafoNoDirigido) {

    // Propiedades de los vértices del grafo
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }
    private val bipartitoColor = BooleanArray(n)
    private var bipartito = true

    init {
        for (v in 0 until n) {
            if (color[v] == Color.BLANCO){
                bipartito = bipartito && dfsVisit(g, v)
            }
        }
    }

    /**
     * Explora recursivamente todos los vértices alcanzables desde [u]
     * en el grafo [g].
     * 
     * Tiempo de ejecución: O(|E|).
     * Precondición: [g] es un grafo.
     *               [u] es un vértice perteneciente al grafo.
     * Postcondición: true
     */
    private fun dfsVisit(g: Grafo, u: Int) : Boolean {
        // Se empieza a explorar u
        color[u] = Color.GRIS

        g.adyacentes(u).forEach {
            // Se selecciona el adyacente
            val v = it.elOtroVertice(u)

            if (color[v] == Color.BLANCO) {
                bipartitoColor[v] = !bipartitoColor[u]
                if (dfsVisit(g, v) == false) return false

            } else if (bipartitoColor[u] == bipartitoColor[v]) {
                return false
            }
        }

        // Se termina de explorar u
        color[u] = Color.NEGRO

        return true
    }

    // Retorna true si el grafo de entrada es un grafo bipartito, false en caso contrario.
    fun esDosColoreable() : Boolean = bipartito
}
