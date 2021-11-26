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
    private val gT = digrafoInverso(g)

    init {
        val ciclo = CicloDigrafo(g)
        if (ciclo.existeUnCiclo()) throw RuntimeException("El grafo no es acíclico.")
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

        var lca = v 

        // Aplicar DFS al grafo inverso desde el vértice v
        dfsVisit(gT, v)

        // Aplicar BFS al grafo inverso desde el vértice u
        color[u] = Color.GRIS
        val Q = LinkedList<Int>()
        Q.add(u)
        
        while (!Q.isEmpty()) {
            val r = Q.poll()

            for (arco in gT.adyacentes(r)) {
                // Se selecciona el adyacente
                val s = arco.elOtroVertice(r)

                if (color[s] == Color.BLANCO) {
                    color[s] = Color.GRIS
                    Q.add(s)
                // Si encuentra ya un coloreado por el DFS anterior, ya es LCA (???)
                } else if (color[s] == Color.NEGRO) {
                    lca = s
                    break
                }
            }
            color[r] = Color.NEGRO
        }
        
        return lca
    }   
}
