package ve.usb.grafoLib

import java.util.LinkedList

/*
 Esta clase al inicializarse determina si un digrafo fuertemente conectado, 
 tiene o no un ciclo euleriano. Si el digrafo de entrada no es fuertemente conectado,
 entonces se lanza un RuntineException.
 */
public class CicloEuleriano(val g: GrafoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }

    private var euleriano = true
    private var arcoVisitado = HashMap<Arco, Boolean>()
    private var cicloEuler = LinkedList<Arco>()

    init {
        if (!esFC(g)) throw RuntimeException("El grafo no es fuertemente conectado.")

        // Verifica si tiene un grafo euleriano
        for (v in 0 until n) {
            euleriano = g.gradoExterior(v) == g.gradoInterior(v)
            if (!euleriano) break
        }

        // val arcos = g.arcos()

        // if (euleriano) {
        //     val temp = LinkedList<Arco>()
            
        //     temp.add(arcos.first())

        //     while (!temp.isEmpty()) {
        //         var u = temp.peekLast()

        //         if (g.adyacentes(u).firstOrNull == null) {
                    
        //         }
        //     }
        // }

        // Obtiene los arcos del ciclo euleriano
        var arcos = g.arcos()
        arcos.forEach { arcoVisitado[it] = false }
        eulerTourRecur(g, arcos.first())
        // eulerTourIter(g, arcos.first())
    }

    private fun eulerTourRecur(g: GrafoDirigido, lado: Arco) {
        arcoVisitado[lado] = true
        g.adyacentes(lado.sumidero()).forEach {
            if (!arcoVisitado[it]!!) {
                arcoVisitado[it] = true
                eulerTourRecur(g, it)
            }
        }
        cicloEuler.addFirst(lado)
    }

    private fun eulerTourIter(g: GrafoDirigido, lado: Arco){
        val S = LinkedList<Arco>()
        S.addFirst(lado)
        arcoVisitado[lado] = true
        
        while (!S.isEmpty()) {
            val arco = S.poll()

            g.adyacentes(arco.sumidero()).forEach {
                if (!arcoVisitado[it]!!) {
                    arcoVisitado[it] = true
                    S.addFirst(it)
                }
            }
            // println(arco)
            cicloEuler.add(arco)
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

    private fun esFC(g: GrafoDirigido): Boolean {
        // Si desde el vertice 0 no se recorre todo el grafo, retorna false
        dfsVisit(g, 0)
        if (color.any { it == Color.BLANCO }) return false

        // Calcula inversa de g
        for (v in 0 until n) color[v] = Color.BLANCO
        val gT = digrafoInverso(g)

        // Si desde el vertice 0 no se recorre todo el grafo inverso, retorna false
        dfsVisit(gT, 0)
        return color.all{ it == Color.NEGRO }
    }

    // Retorna un objeto iterable que contiene los lados del ciclo euleriano.
    // Si el digrafo no tiene ciclo euleriano, entonces se lanza un RuntimeException. 
    fun obtenerCicloEuleriano(): Iterable<Arco> {
        if (!euleriano) throw RuntimeException("El grafo no tiene ciclo euleriano.")
        return cicloEuler
    }
    
    // Retorna true si el digrafo tiene un ciclo euleriano, y false en caso contrario.
    fun tieneCicloEuleriano(): Boolean = euleriano
}