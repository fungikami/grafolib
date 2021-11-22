import ve.usb.grafoLib.*

/**
 * Universidad Simón Bolívar.
 * Algoritmos y Estructuras III. Prof. Guillermo Palma.
 *
 * Descripción: 
 * 
 * 
 */
fun main() {

    // Prueba de grafo bipartito
    val g = GrafoNoDirigido(9)
    g.agregarArista(Arista(0, 1))
    g.agregarArista(Arista(1, 2))
    g.agregarArista(Arista(1, 7))
    g.agregarArista(Arista(2, 3))
    g.agregarArista(Arista(3, 5))
    g.agregarArista(Arista(4, 6))
    g.agregarArista(Arista(4, 8))
    g.agregarArista(Arista(7, 8))
    g.agregarArista(Arista(1, 3)) // Si se agrega, el grafo no es bipartito

    val bipartito = DosColoreable(g)
    println("El grafo es bipartito: ${bipartito.esDosColoreable()}")

    val g2 = GrafoNoDirigido(4)
    g2.agregarArista(Arista(0, 1))
    g2.agregarArista(Arista(0, 3))
    g2.agregarArista(Arista(1, 2))
    g2.agregarArista(Arista(2, 3))

    val bipartito2 = DosColoreable(g2)
    println("El grafo 2 es bipartito: ${bipartito2.esDosColoreable()}")
}