package ve.usb.grafoLib

import java.io.File
import java.io.FileInputStream
import java.util.Scanner
import java.util.LinkedList
import kotlin.math.abs

/**
 * Implementación del algoritmo que soluciona el problema computacional 
 * 2-satisfiability (2-SAT), para asignar valores a variables, cada uno 
 * de los cuales tiene dos valores posibles, para satisfacer un sistema 
 * de restricciones en pares de variables. 
 * 
 * @param [nombreArchivo]: camino en el directorio que contiene la  
 *                         fórmula booleana 2CNF.
 */
public class Sol2SAT(nombreArchivo: String) {
    var esSatisfacible = true
    var asignacion = BooleanArray(0)

    init {
        // Abrir archivo
        if (!File(nombreArchivo).exists()) {
            throw RuntimeException("El archivo indicado en $nombreArchivo no existe o no se puede leer.")
        }
        
        // Leer archivo
        val sc = Scanner(FileInputStream(nombreArchivo))
        val literales = LinkedList<Pair<Int, Int>>()
        var maxVert = -1
        
        try {
            while (true) {
                val (lit0, lit1) = sc.nextLine()!!.split(' ')
                
                val id0 = id(lit0)
                val id1 = id(lit1)

                if (id0 > maxVert) maxVert = id0
                if (id1 > maxVert) maxVert = id1

                literales.add(Pair(id0, id1))
            }
        } catch (e: NoSuchElementException) { }
        
        // Digrafo de implicacion
        val n = maxVert + 2 - (maxVert % 2)
        val digrafoImp = GrafoDirigido(n)
        asignacion = BooleanArray(n / 2)

        // Agregar lados al digrafo
        literales.forEach { (id0, id1) ->
            digrafoImp.agregarArco(Arco(negadoId(id0), id1))
            digrafoImp.agregarArco(Arco(negadoId(id1), id0))
        }

        // Obtener CFC del digrafo
        val cfc = CFC(digrafoImp)

        // Verificar si es satisfacible
        esSatisfacible = !(0 until n step 2).any {
            cfc.estanFuertementeConectados(it, it + 1)
        }

        if (esSatisfacible) {
            // Se crea el grafo componente
            val componente = cfc.obtenerGrafoComponente()

            // Se obtiene el ordenamiento topológico al grafo componente
            val topSort = OrdenTopologico(componente).obtenerOrdenTopologico()

            // Si existe un camino
            for (i in 0 until n step 2) {
                for (v in topSort) {
                    if (v == cfc.obtenerIdentificadorCFC(i + 1)) {
                        // C(¬xi) < C(xi) -> xi = true
                        asignacion[i / 2] = true
                        break
                    } else if (v == cfc.obtenerIdentificadorCFC(i)) {
                        // C(xi) < C(¬xi) -> xi = false
                        break
                    }
                }
            } 
        }
    }    

    /**
     * Retorna .
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición: [id] es: .
     */
    private fun id(str: String): Int {
        if (str == "-0") return 1
        
        val int = str.toInt()
        return if (int >= 0) 2 * int else -2 * int + 1
    }

    /**
     * Retorna .
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición: [negadoId] es: .
     */
    private fun negadoId(id: Int) = if (id % 2 == 0) id + 1 else id - 1 

    /**
     * Retorna .
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición: [literal] es: .
     */
    private fun literal(id: Int): String {
        if (id == 1) return "-0"
        
        return if (id % 2 == 0) (id / 2).toString() else (-id / 2).toString()
    }

    /**
     * Retorna un booleano indicando si el 2CNF tiene asignación que haga verdadera la fórmula.
     * 
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición: [tieneAsignacionVerdadera] es: -True si el 2CF del archivo,
     *                                  tiene asignación que haga verdadera la fórmula.
     *                                              -False de otra forma.
     */
    fun tieneAsignacionVerdadera(): Boolean = esSatisfacible

    /**
     * Retorna un objeto Iterable que contiene la asignación de cada varible de la fórmula.
     * 
     * @throws [RuntimeException] El 2CNF del archivo no tiene una asignación 
     *                            que haga verdadera la fórmula booleana.
     * Tiempo de ejecución: O(1).
     * Precondición: true.
     * Postcondición: [asignacion] Es un objeto iterable con la asignación que debe 
     *                tener cada variable Xi, tal que la posición en el contendor 
     *                corresponde al indice de la variable.
     *                Ejemplo: dado n variables, entonces el objeto iterable corresponde
     *                a la secuencia  <X0, X1, ..., Xn-1> de booleanos.
     */ 
    fun asignacion(): Iterable<Boolean> {
        if (!this.tieneAsignacionVerdadera()) {
            throw RuntimeException("No existe una asignación que haga verdadera la fórmula booleana.")
        }
        return asignacion.asIterable()
    } 
}