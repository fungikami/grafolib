package ve.usb.grafoLib

import java.util.LinkedList
import kotlin.Double.Companion.POSITIVE_INFINITY

/*
 Clase para determinar el ancestro común más bajo de un par de vértices.
 El grafo de entrada tiene que ser un digrafo acíclico, en caso contrario
 se lanza una RuntimeException.
*/
public class LCA(val g: GrafoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }
    private val dist = IntArray(n) { POSITIVE_INFINITY.toInt() }
    // Cada vértice es ancestro de sí mismo
    private val ancestro = Array<MutableSet<Int>>(n) { mutableSetOf(it) }
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
                dist[s] = dist[u] + 1
                ancestro[s].addAll(ancestro[u])
                ancestro[s].add(u)

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

        // Si uno de los vertices es el predecesor del otro, es el LCA
        if (ancestro[u].contains(v)) return v
        if (ancestro[v].contains(u)) return u
        
        // En cambio, se debe buscar el ancestro en común con mayor nivel
        val ancestrosComun = ancestro[u].intersect(ancestro[v])
        
        // Si no hay ancestros en común, se retorna -1
        var maxNivel = -1
        var maxNivelVert = -1

        ancestrosComun.forEach {
            if (dist[it] > maxNivel) {
                maxNivel = dist[it]
                maxNivelVert = it
            }
        }

        return maxNivelVert
    }
}