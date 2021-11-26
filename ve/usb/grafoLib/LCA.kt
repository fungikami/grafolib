package ve.usb.grafoLib

import java.util.LinkedList
/*
 Clase para determinar el ancestro común más bajo de un par de vértices.
 El grafo de entrada tiene que ser un digrafo acíclico, en caso contrario
 se lanza una RuntimeException.
*/
public class LCA(val g: GrafoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }
    private val pred = Array<Int?>(n) { null }
    private var vFuente = 0

    init {
        val ciclo = CicloDigrafo(g)
        if (ciclo.existeUnCiclo()) throw RuntimeException("El grafo no es acíclico.")

        // Buscar el vértice fuente
        for (v in 0 until n) {
            if (g.gradoInterior(v) == 0) {
                vFuente = v 
                break
            }
        }

        // Aplicar BFS modificado desde el vertice fuente para hallar 
        // los predecesores más proximos a cada vértice.
        color[vFuente] = Color.GRIS
        val Q = LinkedList<Int>()
        Q.add(vFuente)
        
        while (!Q.isEmpty()) {
            val u = Q.poll()

            g.adyacentes(u).forEach {
                // Se selecciona el adyacente
                val s = it.elOtroVertice(u)

                // Actualiza predecesor aunque ya se haya visitado
                pred[s] = u

                if (color[s] == Color.BLANCO) {
                    color[s] = Color.GRIS
                    Q.add(s)
                }
            }
            color[u] = Color.NEGRO
        }
    }
    
    private fun dfsVisit(g: Grafo, u: Int) {
        // Se empieza a explorar u
        color[u] = Color.GRIS

        g.adyacentes(u).forEach {
            // Se selecciona el adyacente
            val v = it.elOtroVertice(u)
            if (color[v] == Color.BLANCO) dfsVisit(g, v)
        }

        // Se termina de explorar u
        color[u] = Color.NEGRO
    }

    /*
     Obtiene el LCA de dos vértices. Si alguno de los vértices no pertenece al 
     grafo de lanza una RuntimeException.
     */
    fun obtenerLCA(v: Int, u: Int) : Int {
        if (v < 0 || v >= n) throw RuntimeException("El vértice $v no pertenece al grafo.")
        if (u < 0 || u >= n) throw RuntimeException("El vértice $u no pertenece al grafo.")

        // Si uno de los vertices es el fuente, es el LCA
        if (vFuente == u) return u
        if (vFuente == v) return v 

        // En cambio, se debe buscar el ancestro en común con mayor nivel
        
        return -1
    }   
}
