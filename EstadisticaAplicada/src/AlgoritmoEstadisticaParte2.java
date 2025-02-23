/*
*   Algoritmo para Reporte Práctico de Estadística Aplicada.
*   @author [Pon tu nombre aquí]
 */

 /*
    Descripción: Se realizan dos o más procesos en paralelo, de acuerdo con
    lo que decida el alumno.

    La clase AlgoritmoEstadística se encarga de leer un archivo de texto con
    50000 números aleatorios y, a raíz de estos, generará un arreglo de 50000 
    posiciones. Los números leídos irán del 1 al 100 000. Así mismo, aquí se 
    ejecutan los procesos en paralelo deseados.

    La clase ProcesoPrincipal se encarga ordenar el arreglo mediante el método
    de selección, generando un hilo.

    La clase ProcesoSecundario realiza cualquier cosa que el usuario desee
    realizar.
 */
import java.io.*;
import java.util.Scanner;

public class AlgoritmoEstadisticaParte2 {

    private static File f = new File("Numeros.txt");
    public static int numeros[] = new int[obtenNumeroRegistros(f)];

    public static int obtenNumeroRegistros(File f) {
        String aux;
        int i = 0;
        try {
            FileReader r = new FileReader(f);
            BufferedReader br = new BufferedReader(r);
            while (true) {
                aux = br.readLine();
                if (aux == null) {
                    break;
                }
                i++;
            }

        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
        return i;
    }

    public static void cargaArreglo() {
        String aux;
        int i = 0;

        try {
            if (!f.exists()) {
                System.out.println("Error. No existe el achivo");
                return;
            }
            FileReader r = new FileReader(f);
            BufferedReader br = new BufferedReader(r);
            System.out.println("Datos en el archivo: " + f.getName() + "\n");
            while (true) {
                aux = br.readLine();
                if (aux == null) {
                    break;
                }
                numeros[i] = Integer.parseInt(aux);
                System.out.println("Posición: " + (i + 1) + "/" + numeros.length + ". Dato: " + numeros[i]);
                i++;
            }
            System.out.println("Carga terminada. Procesos iniciando en 5 segundos...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println("Procesos iniciados.");

        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }

    public static void main(String[] args) {
        AlgoritmoEstadisticaParte2.cargaArreglo();
        ProcesoPrincipal hilo1 = new ProcesoPrincipal();
        ProcesoSecundario hilo2 = new ProcesoSecundario();
    }
}

class ProcesoPrincipal implements Runnable {

    private Thread hilo;
    public String mensajeFinal;

    public ProcesoPrincipal() {
        hilo = new Thread(this, "Proceso principal");
        System.out.println("Proceso " + hilo.getName() + " iniciado.");
        hilo.start();
    }

    @Override
    public void run() {
        long tiempoInicial = System.currentTimeMillis();
        this.ordenaSeleccion(AlgoritmoEstadisticaParte2.numeros);
        long tiempoFinal = System.currentTimeMillis();
        mensajeFinal = "Tiempo del proceso: " + (tiempoFinal - tiempoInicial) + " ms. Proceso: " + hilo.getName();
        System.out.println(mensajeFinal);
    }

    public void ordenaSeleccion(int array[]) {
        int e, i, posMenor, aux, iteraciones = 0;

        for (e = 0; e < array.length - 1; e++) {
            posMenor = e;
            for (i = e + 1; i < array.length; i++) {
                if (array[i] < array[posMenor]) {
                    posMenor = i;
                }
                iteraciones++;
            }
            aux = array[e];
            array[e] = array[posMenor];
            array[posMenor] = aux;
        }

        for (int k = 0; k < array.length; k++) {
            //System.out.println("Dato ordenado " + k + ": " + array[k] + ". Proceso: " + hilo.getName());
        }
        //System.out.println("Método de Selección. Iteraciones: " + iteraciones);
    }
}

class ProcesoSecundario implements Runnable {

    private Thread hilo;
    public String mensajeFinal;

    public ProcesoSecundario() {
        hilo = new Thread(this, "Proceso secundario");
        System.out.println("Proceso " + hilo.getName() + " iniciado.");
        hilo.start();
    }

    @Override
    public void run() {
        long tiempoInicial = System.currentTimeMillis();
        this.ponElNombreQueQuieras(AlgoritmoEstadisticaParte2.numeros);
        long tiempoFinal = System.currentTimeMillis();
        mensajeFinal = "Tiempo del proceso: " + (tiempoFinal - tiempoInicial) + " ms. Proceso: " + hilo.getName();
        System.out.println(mensajeFinal);
    }

    public void ponElNombreQueQuieras(int[] array) {
        //Aquí colocas lo que tú decidas. No lo dejes en blanco.
    }
}
