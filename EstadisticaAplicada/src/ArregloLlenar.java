
import java.io.*;
import java.util.Scanner;

public class ArregloLlenar {

    private File f = new File("Numeros.txt");
    private int numeros[] = new int[50000];

    //Método constructor que llena el arreglo.
    public ArregloLlenar() {

        if (f.exists()) {
            f.delete();
            System.out.println("Archivo en reescritura.");
        }

        for (int i = 0; i < numeros.length; i++) {
            int aleatorio = (int) (Math.random() * numeros.length * 2 + 1);
            numeros[i] = aleatorio;
            grabaArchivo(Integer.toString(numeros[i]));
            System.out.println("Llenando posición: " + (i + 1) + " de " + numeros.length);
        }
    }

    //Crear archivo.
    public void creaArchivo() {
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("El archivo ya existe.");
        }
    }

    //Grabar archivo.
    public void grabaArchivo(String linea) {
        try {
            if (!f.exists()) {
                creaArchivo();
                System.out.println("Archivo creado.");
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(linea + "\r\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("Error en el archivo.");
        }
    }

    public static void main(String[] args) {
        ArregloLlenar inicia = new ArregloLlenar();
    }
}
