package ve.usb.grafoLib

/* 
 Solucionador de 2SAT. El constructor recibe como entrada el nombre, o el camino en el directorio, hasta un archivo
 el cual contiene la fórmula booleana en 2CNF. El archivo tiene el formato indicado en el enunciado del Proyecto.
*/
public class Sol2SAT(nombreArchivo: String) {

    // Retorna true si existe una  asignación que haga verdadera la fórmula, en caso contrario retorna false. 
    fun tieneAsignacionVerdadera() : Boolean {

    }

    /* En caso de que exista una asignación que haga verdadera la fórmula booleana, entonces retorna la asignación
     que debe tener cada variable Xi. La posición en el contendor corresponde al indice de la variable.
     Entonces, dada n variables, entonces el objeto iterable corresponde a la secuencia
     < X0, X1, ..., Xn-1>. En caso de que No exista una asignación que haga verdadera la fórmula booena se
     retorna una RuntimeException.
    */ 
    fun asignacion() : Iterable<Boolean> {

    }
}
