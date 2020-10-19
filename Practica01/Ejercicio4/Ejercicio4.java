
/**
 * Clase principal donde corre el programa para calcular el 
 * tiempo total de ejecución de un programa mediante la ecuación
 * general de desempeño.
 */
public class Ejercicio4 {
    
    public static void main(String[] args) {
        corre(args);
    }

    private static void corre(String[] args) {
        Lector lector = new Lector(args);

        if(!lector.banderaActiva()) {
            return;
        } else if(!lector.hayArchivo()){
            return;
        } else {
            System.out.println(lector.toString());

            Calcula tiempo = new Calcula(lector);
            
            System.out.println("Resultado: " + tiempo.getTiempo());
        }
    }
}