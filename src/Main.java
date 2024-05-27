import classes.Cancion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractList;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ArrayList<Cancion> leerCanciones() {
       return null;
    }

    public static AbstractList<Cancion> top10CancionesPaisFecha(ArrayList<Cancion> canciones, String pais, String fecha) {
        ArrayList<Cancion> cancionesPaisFecha = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getCountry().equals(pais) && cancion.getSnapshot_date().equals(fecha)) {
                cancionesPaisFecha.add(cancion);
            }
        }
        return cancionesPaisFecha;
    }

    public static AbstractList<Cancion> top5CancionesMasTop50Fecha(ArrayList<Cancion> canciones, String fecha) {
        ArrayList<Cancion> cancionesMasTop50 = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getSnapshot_date().equals(fecha)) {
                cancionesMasTop50.add(cancion);
            }
        }
        return cancionesMasTop50;
    }

    public static AbstractList<Cancion> top7ArtistasMasTop50Fecha(ArrayList<Cancion> canciones, String fechaInicio, String fechaFin) {
        ArrayList<Cancion> cancionesMasTop50 = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getSnapshot_date().equals(fechaInicio) || cancion.getSnapshot_date().equals(fechaFin)) {
                cancionesMasTop50.add(cancion);
            }
        }
        return cancionesMasTop50;
    }

    public static int cantidadVecesArtistaTop50Fecha(ArrayList<Cancion> canciones, String artista, String fecha) {
        int cantidad = 0;
        for (Cancion cancion : canciones) {
            if (cancion.getArtists().contains(artista) && cancion.getSnapshot_date().equals(fecha)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static int cantidadCancionesTempoRangoFecha(ArrayList<Cancion> canciones, double tempoMin, double tempoMax, String fechaInicio, String fechaFin) {
        int cantidad = 0;
        for (Cancion cancion : canciones) {
            if (cancion.getTempo() >= tempoMin && cancion.getTempo() <= tempoMax && (cancion.getSnapshot_date().equals(fechaInicio) || cancion.getSnapshot_date().equals(fechaFin))) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static void main(String[] args) {
//        ArrayList<Cancion> canciones = leerCanciones();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Top 10 canciones en un país en un día dado");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado");
            System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
            System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
            System.out.println("0. Salir");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    // top10CancionesPaisFecha();
                    break;
                case 2:
                    // Aquí va la lógica para la opción 2
                    break;
                case 3:
                    // Aquí va la lógica para la opción 3
                    break;
                case 4:
                    // Aquí va la lógica para la opción 4
                    break;
                case 5:
                    // Aquí va la lógica para la opción 5
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        } while (option != 0);
    }

}