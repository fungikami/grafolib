package ve.usb.grafoLib

/*
 Esta clase al inicializarse determina si un digrafo fuertemente conectado, 
 tiene o no un ciclo euleriano. Si el digrafo de entrada no es fuertemente conectado,
 entonces se lanza un RuntineException.
 */
public class CicloEuleriano(val g: GrafoDirigido) {
    private val n = g.obtenerNumeroDeVertices()
    private val color = Array<Color>(n) { Color.BLANCO }

    private var euleriano = true
    private var arcosEuler = ArrayList<Arco>(0)

    init {
        if (!esFC(g)) throw RuntimeException("El grafo no es fuertemente conectado.")

        // Verifica si tiene un grafo euleriano
        for (v in 0 until n) {
            euleriano = g.gradoExterior(v) == g.gradoInterior(v)
            if (!euleriano) break
        }

        // // Obtiene los arcos del ciclo euleriano
        // var arcos = g.arcos()
        // var arcosColor = HashMap<Arco, Color>()
        // arcos.forEach { arcosColor.put(it, false) }

        // // Escoge un arco del grafo
        // var arcoActual = arcos.first()

        // // Agrega arco a la lista de arcos del ciclo euleriano
        // arcosEuler.add(arcoActual)

        // // Mientras existe un lado de color blanco desde el arcoActual
        // while (!arcosColor[arcoActual]) {
        //     arcosColor[arcoActual] = true

        //     // Buscamos el siguiente arco 
        //     for (arc in g.adyacentes(arcoActual.sumidero())) {
        //         if (!arcosColor[arc]) {
        //             arcoActual = arc
        //             break
        //         }
        //     }

        //     // Añadimos el siguiente arco en la lista de arcos del ciclo
        //     arcosEuler.add(arcoActual)
        // }
        
        // EULER-TOUR(G)
        // color all edges WHITE
        // let (v, u) be any edge
        // let L be a list containing v
        // while there is some WHITE edge (v, w) coming out of v
        //     color (v, w) BLACK
        //     v = w
        //     append v to L
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
        return color.all { it == Color.NEGRO }
    }

    // Retorna un objeto iterable que contiene los lados del ciclo euleriano.
    // Si el digrafo no tiene ciclo euleriano, entonces se lanza un RuntineException. 
    fun obtenerCicloEuleriano(): Iterable<Arco> = {
        if (!euleriano) throw RuntimeException("El grafo no tiene ciclo euleriano.")
        
        return g.arcos()
    }
    
    // Retorna true si el digrafo tiene un ciclo euleriano, y false en caso contrario.
    fun tieneCicloEuleriano(): Boolean = euleriano
}
