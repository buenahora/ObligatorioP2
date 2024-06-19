import Classes.Cancion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import Classes.ArtistaCantidad;
import uy.edu.um.prog2.adt.closedhash.ClosedHashImpl;
import uy.edu.um.prog2.adt.closedhash.DuplicateKey;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;
import uy.edu.um.prog2.adt.linkedlist.Node;
import uy.edu.um.prog2.adt.maxheap.MiMaxHeap;

public class Main {
    static ClosedHashImpl<String, ClosedHashImpl<String, MyList<Cancion>>> hashFechas = new ClosedHashImpl<>(10000);


    public static MyLinkedListImpl<String> splitArtistas(Cancion cancion) {
        MyLinkedListImpl<String> artistas = new MyLinkedListImpl<>();

        String[] artistasSeparados = cancion.getArtists().split(",");
        for (String artista : artistasSeparados) {
            artistas.add(artista);
        }
        return artistas;
    }

    public static boolean isFechaEntre(Date fechaInicio, Date fechaFinal, Date fechaVerificar) {
        if (fechaVerificar.compareTo(fechaInicio) >= 0) {
            return fechaVerificar.compareTo(fechaFinal) <= 0;
        } else {
            return false;
        }
    }
    public static MyList<Cancion> leerCanciones() {
        String csvFile = "src/universal_top_spotify_songs.csv";
        String line = "";
        long startTime = System.nanoTime();

        MyList<Cancion> canciones = new MyLinkedListImpl<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {

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

                ClosedHashImpl<String, MyList<Cancion>> paisHash;
                MyList<Cancion> listaFechaPais;

                paisHash = hashFechas.getValue(cancion.getSnapshot_date());
                if (paisHash == null) {
                    paisHash = new ClosedHashImpl<>(10000);
                    hashFechas.insertar(cancion.getSnapshot_date(), paisHash);
                }

                listaFechaPais = paisHash.getValue(cancion.getCountry());
                if (listaFechaPais == null) {
                    listaFechaPais = new MyLinkedListImpl<>();
                    paisHash.insertar(cancion.getCountry(), listaFechaPais);
                }

                listaFechaPais.add(cancion);
            }
        } catch (IOException | DuplicateKey e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        float duration = (float) (endTime - startTime) / 1000000000;  // Time in seconds
        System.out.println("La lectura se demoró: " + duration + " segundos");

        return canciones;
    }

    public static MyList<Cancion> top10CancionesPaisFecha(String pais, String fecha) {
        MyList<Cancion> cancionesPaisFecha = new MyLinkedListImpl<>();

        //2023-07-28
        MyList<Cancion> listaTop50 = hashFechas.getValue(fecha).getValue(pais);

        for (int i = 0; i < 10; i++) {
            cancionesPaisFecha.add(listaTop50.get(i));
        }

        return cancionesPaisFecha;
    }

    public static MyList<Cancion> top5CancionesMasTop50Fecha(String fecha) throws ParseException, DuplicateKey {
        MyList<Cancion> cancionesMasTop50 = new MyLinkedListImpl<>();

        ClosedHashImpl<String, MyList<Cancion>> hashFechadada = hashFechas.getValue(fecha);
        MyList<String> keys = hashFechadada.getKeys();

        ClosedHashImpl<String,Cancion> hashContador = new ClosedHashImpl<>(1000000);

        // CONTAR LAS OCURRENCIAS DE UNA CANCION EN TODOS LOS PAISES DE UN DIA
        int sizeKeys = keys.size();
        for (int i =0; i<sizeKeys;i++){
            MyList<Cancion> listacanciones = hashFechadada.getValue(keys.get(i));
            int sizeListaCanciones = listacanciones.size();
            for(int j = 0; j<sizeListaCanciones;j++){
                Cancion cancion = listacanciones.get(j);
                if(!hashContador.contains(cancion.getSpotify_id())){
                    hashContador.insertar(cancion.getSpotify_id(),cancion);
                }else {
                    hashContador.getValue(cancion.getSpotify_id()).aumentarcontador();
                }
            }
        }

        // METEMOS TODAS LAS CANCIONES EN UN MAXHASH
        int sizeHashContador = hashContador.getSize();
        MyList<String> keyscontador = hashContador.getKeys();
        MiMaxHeap<Cancion> heapCanciones = new MiMaxHeap<>(sizeHashContador);
        for (int i = 0; i < sizeHashContador;i++){
            heapCanciones.insert(hashContador.getValue(keyscontador.get(i)));
        }
        for(int i = 0; i < 5;i++){
            cancionesMasTop50.add(heapCanciones.delete());
        }

        return cancionesMasTop50;
    }

    public static MyLinkedListImpl<ArtistaCantidad> top7ArtistasMasTop50Fecha(String inicio, String fin) throws ParseException, DuplicateKey {
        MyLinkedListImpl<ArtistaCantidad> artistasMasTop7 = new MyLinkedListImpl<>();

        for (int i = 0; i < 7; i++) {
            ArtistaCantidad test = new ArtistaCantidad("artista"+i, 0);
            artistasMasTop7.add(test);
        }

        ClosedHashImpl<String, Integer> contadorArtistas = new ClosedHashImpl<>(10000);

        MyList<String> fechasKeys = hashFechas.getKeys();
        MyList<String> paisesKeys = hashFechas.getValue(fechasKeys.get(0)).getKeys();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        Date fechaInicio = sdf.parse(inicio);
        Date fechaFin = sdf.parse(fin);


        for (int i = 0; i < fechasKeys.size(); i++) {

//            System.out.println("I es "+i);
            for (int j = 0; j < paisesKeys.size(); j++) {
//                System.out.println("J es "+j);
                String fecha = fechasKeys.get(i);
                String pais = paisesKeys.get(j);


                ClosedHashImpl hashPaises = hashFechas.getValue(fecha);

                MyList<Cancion> cancionesPais = hashFechas.getValue(fecha).getValue(pais);

                if(cancionesPais == null) {
                    continue;
                }



                for (int k = 0; k < cancionesPais.size(); k++) {
                    Cancion cancion = cancionesPais.get(k);
                    Date fechaCancion = sdf.parse(cancion.getSnapshot_date());

                    if(isFechaEntre(fechaInicio, fechaFin, fechaCancion)) {

                        MyLinkedListImpl<String> artistas = splitArtistas(cancion);

                        for (int l = 0; l < artistas.size(); l++) {
                            String artista = artistas.get(l).trim();
                            if(contadorArtistas.contains(artista)) {

                                int valorNuevo = contadorArtistas.getValue(artista) + 1;

                                contadorArtistas.changeValue(artista, valorNuevo);

                            } else {
                                contadorArtistas.insertar(artista, 1);
                            }
                        }

                    }

                }

            }
        }



        MyList<String> keys = contadorArtistas.getKeys();

        for (int i = 0; i < keys.size(); i++) {
            String artista = keys.get(i);
            int cantidad = contadorArtistas.getValue(artista);
            ArtistaCantidad artistaCantidad = new ArtistaCantidad(artista, cantidad);

            for (int j = 0; j < artistasMasTop7.size(); j++) {
                if (artistasMasTop7.isEmpty()) {
                    artistasMasTop7.add(artistaCantidad);
                    break;
                } else if (cantidad > artistasMasTop7.get(j).cantidad) {
                    artistasMasTop7.remove(artistasMasTop7.get(j));
                    artistasMasTop7.add(artistaCantidad);
                    break;
                }
            }
        }

        artistasMasTop7.sort();
        return artistasMasTop7;
    }

    public static int cantidadVecesArtistaTop50Fecha(String artista, String fecha) {
        return 1;
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

    public static void main(String[] args) throws DuplicateKey, ParseException {
        leerCanciones();
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

                    MyList<Cancion> cancionesPaisFecha = top10CancionesPaisFecha(pais, fecha);

                    if(cancionesPaisFecha.isEmpty()) {
                        System.out.println("No se encontraron canciones para el país y fecha ingresados.");
                        break;
                    }

                    System.out.println("El top 10 de canciones en el pais dado en la fecha dada es: ");
                    for (int i = 0; i < cancionesPaisFecha.size(); i++) {
                        System.out.println((i + 1) + ". " + cancionesPaisFecha.get(i).getName() + " (" + cancionesPaisFecha.get(i).getArtists() + ")");
                    }

                    System.out.println();
                    break;

                case 2:
                    System.out.println("Ingrese una fecha (YYYY-MM-DD): ");
                    String fecha2 = sc.nextLine();
                    MyList<Cancion> cancionesTop5 = top5CancionesMasTop50Fecha(fecha2);


                    System.out.println("El top 5 de canciones que aparecen en mas top 50 es: ");
                    for (int i = 0; i < cancionesTop5.size(); i++) {
                        Cancion cancionActual = cancionesTop5.get(i);

                        System.out.println((i + 1) + ". " + cancionActual.getName() + " aparece " + cancionActual.getContador() +" veces");
                    }

                    break;
                case 3:
//                    System.out.println("Ingrese una fecha inicial (YYYY-MM-DD): ");
//                    String fechaInicial = sc.nextLine();
//
//                    System.out.println("Ingrese una fecha final (YYYY-MM-DD): ");
//                    String fechaFinal = sc.nextLine();

                    MyLinkedListImpl<ArtistaCantidad> cancionesTop7Artistas = top7ArtistasMasTop50Fecha("2023-08-11", "2024-05-13");

                    System.out.println("El top 7 artistas que mas aparece en el top 50 en el rango de fechas dado es: ");
                    for (int i = 0; i < cancionesTop7Artistas.size(); i++) {
                        System.out.println((i+1) +" - "+cancionesTop7Artistas.get(i).artista+" aparece "+cancionesTop7Artistas.get(i).cantidad+" veces");
                    }
                    System.out.println();

                    break;
                case 4:
                    System.out.println("Ingrese el nombre del artista: ");
                    String artista = sc.nextLine();
                    artista = artista.trim();

                    System.out.println("Ingrese una fecha (YYYY-MM-DD): ");
                    String fechaArtista = sc.nextLine();
                    fechaArtista = fechaArtista.trim();

                    int cantidad = cantidadVecesArtistaTop50Fecha(artista, fechaArtista);

                    System.out.println("El artista " + artista + " aparece " + cantidad + " veces en el top 50 en la fecha " + fechaArtista + ".");

//                    System.out.print("El artista ");
//                    System.out.print(artista);
//                    System.out.print(" aparece ");
//                    System.out.print(cantidad);
//                    System.out.print(" veces en el top 50 en la fecha ");
//                    System.out.println(fechaArtista);
                    System.out.println();
                    break;
                case 5:
                    // Aquí va la lógica para la opción 5
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    System.out.println();
                    break;
            }
        } while (option != 0);
    }

}