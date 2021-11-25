package ve.usb.grafoLib

/*
 Clase para determinar el ancestro común más bajo de un par de vértices.
 El grafo de entrada tiene que ser un digrafo acíclico, en caso contrario
 se lanza una RuntimeException.
*/
public class LCA(val g: GrafoDirigido) {

    init {
        val ciclo = CicloDigrafo(g)
        if (ciclo.existeUnCiclo()) throw RuntimeException("El grafo no es acíclico.")
    }

    /*
     Obtiene el LCA de dos vértices. Si alguno de los vértices no pertenece al 
     grafo de lanza una RuntimeException.
     */
    fun obtenerLCA(v: Int, u: Int) : Int {
        //TODO
        return -1
    }
}
