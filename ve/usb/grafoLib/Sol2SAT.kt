package ve.usb.grafoLib

import java.io.File
import java.io.FileInputStream
import java.util.Scanner
import java.util.LinkedList

/* 
 Solucionador de 2SAT. El constructor recibe como entrada el nombre, o el camino en el directorio, hasta un archivo
 el cual contiene la fórmula booleana en 2CNF. El archivo tiene el formato indicado en el enunciado del Proyecto.
*/
public class Sol2SAT(nombreArchivo: String) {

    esSatisfaciible = false

    init {
        // https://www.math.ucsd.edu/~sbuss/CourseWeb/Math268_2007WS/2SAT.pdf
        // https://en.wikipedia.org/wiki/2-satisfiability

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

        // Agregar lados
        literales.forEach { (id0, id1) ->
            digrafoImp.agregarArco(Arco(negadoId(id0), id1))
            digrafoImp.agregarArco(Arco(negadoId(id1), id0))
        }

        // Obtener CFC
        val cfc = CFC(digrafoImp)

        for (i in 0 until n step 2) {
            if (cfc.estanFuertementeConectados(i, i + 1))
        }
        // Si existe asignacion que haga verdadera, se crea grafo componente
        // Ordenamiento topologico <

        // 
    }    

    private fun id(str: String): Int {
        if (str == "-0") return 1
        
        val int = str.toInt()
        return if (int > 0) 2 * int else -2 * int + 1
    }

    private fun negadoId(id: Int) = if (id % 2 == 0) id + 1 else id - 1 

    private fun literal(id: Int): String {
        if (id == 1) return "-0"
        
        return if (id % 2 == 0) (id / 2).toString() else (-id / 2).toString()
    }

    // Retorna true si existe una  asignación que haga verdadera la fórmula, en caso contrario retorna false. 
    fun tieneAsignacionVerdadera(): Boolean {
        //TODO
        return false
    }

    /* En caso de que exista una asignación que haga verdadera la fórmula booleana, entonces retorna la asignación
     que debe tener cada variable Xi. La posición en el contendor corresponde al indice de la variable.
     Entonces, dada n variables, entonces el objeto iterable corresponde a la secuencia
     <X0, X1, ..., Xn-1>. En caso de que No exista una asignación que haga verdadera la fórmula booena se
     retorna una RuntimeException.
    */ 
    fun asignacion(): Iterable<Boolean> {
        //TODO
        return arrayListOf()
    }
}
