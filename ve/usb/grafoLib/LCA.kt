package ve.usb.grafoLib

import java.util.LinkedList
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.Double.Companion.NEGATIVE_INFINITY

/*
 Clase para determinar el ancestro común más bajo de un par de vértices.
 El grafo de entrada tiene que ser un digrafo acíclico, en caso contrario
 se lanza una RuntimeException.
*/
public class LCA(val g: GrafoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }
    private val dist = IntArray(n) { POSITIVE_INFINITY.toInt() }
    private val pred = Array<ArrayList<Int>>(n) { arrayListOf() }
    private var vFuente = 0

    init {
        if (CicloDigrafo(g).existeUnCiclo()) throw RuntimeException("El grafo no es acíclico.")

        // Buscar el vértice fuente
        for (v in 0 until n) {
            if (g.gradoInterior(v) == 0) {
                vFuente = v 
                break
            }
        }
        println(vFuente)
        /* Aplicar BFS modificado desde el vertice fuente
        para hallar los ancestros de cada vértice.*/
        dist[vFuente] = 0
        color[vFuente] = Color.GRIS
        val Q = LinkedList<Int>()
        Q.add(vFuente)
        
        while (!Q.isEmpty()) {
            val u = Q.poll()

            g.adyacentes(u).forEach {
                // Se selecciona el adyacente
                val s = it.elOtroVertice(u)

                // Guardar predecesor del vértice.
                pred[s].add(u)
                dist[s] = dist[u] + 1

                if (color[s] == Color.BLANCO) {
                    color[s] = Color.GRIS
                    Q.add(s)
                }
            }
            color[u] = Color.NEGRO
        }
    }
    
    /*
     Obtiene el LCA de dos vértices. Si alguno de los vértices no pertenece al 
     grafo de lanza una RuntimeException.
     */
    fun obtenerLCA(v: Int, u: Int) : Int {
        g.chequearVertice(v)
        g.chequearVertice(u)

        // Si uno de los vertices es el fuente, es el LCA
        if (vFuente == u) return u
        if (vFuente == v) return v 

        // En cambio, se debe buscar el ancestro en común con mayor nivel
        println(pred[u])
        println(pred[v])
        val ancestrosComun = pred[u].intersect(pred[v])
        println(ancestrosComun)
        var maxNivel = NEGATIVE_INFINITY.toInt()
        var maxNivelVert = -1

        for (anc in ancestrosComun) {
            if (dist[anc] > maxNivel) {
                maxNivel = dist[anc]
                maxNivelVert = anc
            }
        }

        return maxNivelVert
    }
}