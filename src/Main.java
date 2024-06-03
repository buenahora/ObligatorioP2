import classes.Cancion;

import java.util.AbstractList;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ArrayList<Cancion> leerCanciones() {
        String csvFile = "src/universal_top_spotify_songs.csv";
        String line = "";
        String csvSplitBy = ",";
        ArrayList<Cancion> canciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] data = line.split("(?<=\"),(?=\")", -1);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].substring(1, data[i].length() - 1);
                }

                Cancion cancion = new Cancion(
                        data[0], // spotify_id
                        data[1], // name
                        data[2], // artists
                        Integer.parseInt(data[3]), // daily_rank
                        Integer.parseInt(data[4]), // daily_movement
                        Integer.parseInt(data[5]), // weekly_movement
                        data[6], // country
                        data[7], // snapshot_date
                        Integer.parseInt(data[8]), // popularity
                        Boolean.parseBoolean(data[9]), // is_explicit
                        Integer.parseInt(data[10]), // duration_ms
                        data[11], // album_name
                        data[12], // album_release_date
                        Double.parseDouble(data[13]), // danceability
                        Double.parseDouble(data[14]), // energy
                        Integer.parseInt(data[15]), // key
                        Double.parseDouble(data[16]), // loudness
                        Integer.parseInt(data[17]), // mode
                        Double.parseDouble(data[18]), // speechiness
                        Double.parseDouble(data[19]), // acousticness
                        Double.parseDouble(data[20]), // instrumentalness
                        Double.parseDouble(data[21]), // liveness
                        Double.parseDouble(data[22]), // valence
                        Double.parseDouble(data[23]), // tempo
                        Integer.parseInt(data[24]) // time_signature
                );
                canciones.add(cancion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public static ArrayList<Cancion> top10CancionesPaisFecha(ArrayList<Cancion> canciones, String pais, String fecha) {
        ArrayList<Cancion> cancionesPaisFecha = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if(cancionesPaisFecha.size() == 10) break;

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
        ArrayList<Cancion> canciones = leerCanciones();
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
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Ingrese el pais: ");
                    String pais = sc.nextLine();
                    pais = pais.trim();

                    System.out.println("Ingrese una fecha (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();
                    fecha = fecha.trim();

                    ArrayList<Cancion> cancionesPaisFecha = top10CancionesPaisFecha(canciones, pais, fecha);

                    if(cancionesPaisFecha.isEmpty()) {
                        System.out.println("No se encontraron canciones para el país y fecha ingresados.");
                        break;
                    }

                    for (int i = 0; i < cancionesPaisFecha.size(); i++) {
                        System.out.println((i + 1) + ". " + cancionesPaisFecha.get(i).getName() + " (" + cancionesPaisFecha.get(i).getArtists() + ")");
                    }
                    System.out.println();
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