
/** 
 * Clase para calcular el tiempo total de ejecución de un programa. 
 */
public class Calcula {

    /** Flotante que nos indica el tiempo que tarda la ejecucuón de un programa. */
    private float tiempo;

    /** Constructor */
    Calcula (Lector lector) {
        if (lector.getBanderaF())
            tiempo = banderaF(lector.getDatos(), lector.getNumeroDeOperaciones(), lector.getUltimaLinea());
        else if (lector.getBanderaD())
            tiempo = banderaD(lector.getDatos(), lector.getNumeroDeOperaciones(), lector.getUltimaLinea());
    }

    /**
     * Se hace el cálculo correspondiente para la bandera -f que indica 
     * la frecuencia. 
     */
    private float banderaF(int[][] datos, int numeroDeOperaciones, float ultimaLinea) {
        float aux = 0;
        for (int i = 0; i < numeroDeOperaciones; i++) {
            aux += datos[i][0] * datos[i][1];
        }
        aux /= ultimaLinea;
        return aux;
    }

    /**
     * Se hace el cálculo correspondiente para la bandera -d que indica 
     * duración de ciclo. 
     */
    private float banderaD(int[][] datos, int numeroDeOperaciones, float ultimaLinea) {
        float aux = 0;
        for (int i = 0; i < numeroDeOperaciones; i++) {
            aux += datos[i][0] * datos[i][1] * ultimaLinea;
        }
        return aux;
    }

    /**
     * Regresa un flotante que nos indica el tiempo total que tardo en ejecutarse
     * el progrma.
     * @return tiempo tiempo total
     */
    public float getTiempo() {
        return tiempo;
    }
}