/**
 * Autor: Christopher Gómez.
 * Fecha: 8/nov/2021
 */

package ve.usb.grafoLib

/**
 * Retorna el grafo inverso de [g].
 * 
 * Tiempo de ejecución: O(|V| + |E|) en el peor caso.
 * Precondición: [g] es un grafo dirigido.
 * Postcondición: El grafo resultante es un grafo inverso de g.
 */
fun digrafoInverso(g: GrafoDirigido): GrafoDirigido {
    val gInverso = GrafoDirigido(g.obtenerNumeroDeVertices())

    g.arcos().forEach {
        gInverso.agregarArco(Arco(it.sumidero(), it.fuente()))
    }

    return gInverso
}