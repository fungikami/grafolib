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
            if (g.gradoExterior(v) != g.gradoInterior(v)) {
                euleriano = false
                break
            }
        }

        // Obtiene los arcos del ciclo euleriano

        // Inicializa todos los lados de Color.BLANCO
        var arcosColor = HashMap<Arco, Color>()
        var arcos = g.arcos()
        arcos.forEach { arcosColor.put(it, Color.BLANCO) }

        var arcoActual = arcos.first()
        arcosEuler.add(arcoActual)
        while (arcosColor[arcoActual] == Color.BLANCO) {
            arcosColor[arcoActual] = Color.NEGRO

            for (arc in g.adyacentes(arcoActual.sumidero())) {
                if (arcosColor[arc] == Color.BLANCO) {
                    arcoActual = arc
                    break
                }
            }
            arcosEuler.add(arcoActual)
        }
        
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

    private fun esFC(g: GrafoDirigido) : Boolean {
        // Si desde el vertice 0 no se recorre todo el grafo, retorna false
        dfsVisit(g, 0)
        for (v in 0 until n) if (color[v] == Color.BLANCO) return false

        // Calcula inversa de g
        for (v in 0 until n) color[v] = Color.BLANCO
        val gT = digrafoInverso(g)

        // Si desde el vertice 0 no se recorre todo el grafo inverso, retorna false
        dfsVisit(gT, 0)
        for (v in 0 until n) if (color[v] == Color.BLANCO) return false

        return true
    }

    // Retorna un objeto iterable que contiene los lados del ciclo euleriano.
    // Si el digrafo no tiene ciclo euleriano, entonces se lanza un RuntineException. 
    fun obtenerCicloEuleriano() : Iterable<Arco> = arcosEuler
    
    // Retorna true si el digrafo tiene un ciclo euleriano, y false en caso contrario.
    fun tieneCicloEuleriano() : Boolean = euleriano
}
