import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/** 
 * Clase para leer los datos del fichero que se le pase al programa
 * para posteriormente hacer los calculos correspondientes para saber
 * el tiempo total de ejecución del un programa. 
 */
public class Lector {

    /** Booleano que nos indica si hay un archivo. */
    private boolean archivo;

    /** 
     * Arreglo de dos dimensiones que nos indica los ciclos que tarda la 
     * instrucción i en ejecutarse y el número de veces que aparece 
     * la instrucción en el programa. 
     */
    private int[][] datos;

    /** Booleano que nos indica si está activada la vandera -f. */
    private boolean bandera_f;

    /** Booleano que nos indica si está activada la vandera -d. */
    private boolean bandera_d;

    /** Flotante que nos indica la frecuencia(en GHz) o duración de ciclo(en ns)
     * dependiendo de la bandera que se pasó. Si la bandera es -f es frecuencia y si 
     * es -d es duración del ciclo*/
    private float ultimaLinea;

    /** Entero que contiene el número de operaciones del procesador de la computadora */
    private int numeroDeOperaciones;

    /** Constructor */
    Lector(String[] args) {
        bandera(args);
        lectura(args);
    }

    /**
     * Lee el archivo, guarda los ciclos que tarda la instrucción i
     * en ejecutarse y el número de veces que aparece la instrucción
     * en el programa en la variable datos y, guarda la frecuencia(en GHz)
     * o duración de ciclo(en ns) dependiendo de la bandera que se pasó en
     * la variable ultimaLina.
     * @param args arreglo que contiene la bandera y la dirección del arichivo
     */
    private void lectura(String[] args) {

        if(!banderaActiva()) {
            System.err.println("Error al leer la bandera");
            return;
        }

        try {
            FileReader fr = new FileReader(args[1]);
            BufferedReader br = new BufferedReader(fr);
            numeroDeOperaciones = Integer.parseInt(br.readLine()); 
            datos = new int[numeroDeOperaciones][2];
            int contador = 0;
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                if(linea.indexOf(" ") == -1) {
                    ultimaLinea = Float.parseFloat(linea);
                } else {
                    String valor_1 = linea.substring(0, linea.indexOf(" "));
                    String valor_2 = linea.substring(valor_1.length() + 1, linea.length());
                    
                    datos[contador][0] = Integer.parseInt(valor_1);
                    datos[contador][1] = Integer.parseInt(valor_2);
                }
                archivo = true;
                contador++;
            }
            br.close();
        } catch(IOException ioe) {
            archivo = false;
            System.err.println("Error al leer el archivo");
            return;
            
        }
    }

    /**
     * Activa la bandera correspondiente para saber cómo se debe calcular el tiempo 
     * total de ejecucion del programa.
     */
    private void bandera(String[] args) {
        bandera_d = bandera_f = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f")) {
                bandera_f = true;
            } else if (args[i].equals("-d")) {
                bandera_d = true;
            }
        }   
    }

    /**
     * Regresa un booleano que nos indica si hay un archivo. verdadero si hay y
     * falso en el caso contrario.
     * @return archivo existencia del archivo
     */
    public boolean hayArchivo() {
        return archivo;
    }

    /**
     * Regresa el arreglo de dos dimensiones en el cual se guardaron los ciclos 
     * que tarda la instrucción i en ejecutarse y el número de veces que aparece 
     * la instrucción en el programa.
     * @return datos datos del programa
     */
    public int[][] getDatos() {
        return datos;
    }

    /**
     * Regresa un float que contiene la frecuencia(en GHz) o duración de ciclo(en ns)
     * dependiendo de la bandera que se pasó. Si la bandera es -f es frecuencia y si 
     * es -d es duración del ciclo.
     * @return ultimaLinea ultima linea del fichero
     */
    public float getUltimaLinea() {
        return ultimaLinea;
    }

    /**
     * Regresa un entero que contiene el número de operaciones del procesador de la 
     * computadora.
     * @return numeroDeOperaciones numero de operaciones
     */
    public int getNumeroDeOperaciones() {
        return numeroDeOperaciones;
    }

    /**
     * Regresa un booleano que nos indica si la bandera -f se paso como argumento.
     * @return bandera_f bandera -f
     */
    public boolean getBanderaF() {
        return bandera_f;
    }

    /**
     * Regresa un booleano que nos indica si la bandera -d se paso como argumento.
     * @return bandera_d bandera -d
     */
    public boolean getBanderaD() {
        return bandera_d;
    }

    /**
     * Nos indica si se paso alguna bandera (-f o -d) como argumento con un booleano.
     * @return la bandera que este activa
     */
    public boolean banderaActiva() {
        return bandera_f == true ? bandera_f : bandera_d;
    }

    /**
     * Regresa una representación en cadena de los datos para calcular el tiempo total
     * de ejecucion del programa.
     * 
     * @return cadena representación en cadena de los datos
     */
    @Override public String toString() {
        String cadena = "Visualizacion de los datos\n";
        cadena += getNumeroDeOperaciones() + " operaciones del procesador de la computadora\n";
        
        for (int i = 0; i < getNumeroDeOperaciones(); i++) {
            cadena += "Ciclos que tarda la instrucion " + i + " en ejecutarse: " + datos[i][0] + ", numero de veces que aparece la instruccion en el programa: " + datos[i][1] + "\n";
        }
        
        if (bandera_d) {
            cadena += "Duracion del ciclo: " + getUltimaLinea() + "\n";
        } else if (bandera_f) {
            cadena += "Frecuencia: " + getUltimaLinea() + "\n";
        }
        
        return cadena;
    }
}