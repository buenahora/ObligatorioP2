import classes.Cancion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ArrayList<Cancion> leerCanciones() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        File csvFile = new File("src/universal_top_spotify_songs.csv");

        try (Scanner scanner = new Scanner(csvFile)) {
            //Saltea la primera linea, si no se rompe
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll(";", "");
                line = line.substring(1, line.length() - 1);
                line = line.replaceAll("\"\"", "\"");

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("[;\"]", "");
                }

                if (values[0].equals("7hDoxkN20lLb06zifzYnD2")) {
                    line = "7hDoxkN20lLb06zifzYnD2,\"Ishq - From \"\"Lost	Found\"\"\",\"Faheem Abdullah, Rauhan Malik\",\"2\",\"0\",\"0\",\"PK\",\"2024-05-13\",\"75\",\"False\",\"228639\",\"Ishq (From \"\"Lost	Found\"\")\",\"2024-03-09\",\"0.383\",\"0.547\",\"4\",\"-8.862\",\"0\",\"0.0275\",\"0.423\",\"0.0466\",\"0.147\",\"0.236\",\"95.068\",\"4\"";
                    line = line.replaceAll(";", "");
                    line = line.replaceAll("\"\"", "\"");
                    values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                    for (int i = 0; i < values.length; i++) {
                        String str = values[i];
                        str = str.replaceAll("[;\"]", "");
                        line = line.trim();
                        values[i] = str;
                    }

                    Cancion cancion = new Cancion(values[0], values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6], values[7], Integer.parseInt(values[8]), Boolean.parseBoolean(values[9]), Integer.parseInt(values[10]), values[11], values[12], Double.parseDouble(values[13]), Double.parseDouble(values[14]), Integer.parseInt(values[15]), Double.parseDouble(values[16]), Integer.parseInt(values[17]), Double.parseDouble(values[18]), Double.parseDouble(values[19]), Double.parseDouble(values[20]), Double.parseDouble(values[21]), Double.parseDouble(values[22]), Double.parseDouble(values[23]), Integer.parseInt(values[24]));
                    canciones.add(cancion);

                } else {
                    Cancion cancion = new Cancion(values[0], values[1], values[2], Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6], values[7], Integer.parseInt(values[8]), Boolean.parseBoolean(values[9]), Integer.parseInt(values[10]), values[11], values[12], Double.parseDouble(values[13]), Double.parseDouble(values[14]), Integer.parseInt(values[15]), Double.parseDouble(values[16]), Integer.parseInt(values[17]), Double.parseDouble(values[18]), Double.parseDouble(values[19]), Double.parseDouble(values[20]), Double.parseDouble(values[21]), Double.parseDouble(values[22]), Double.parseDouble(values[23]), Integer.parseInt(values[24]));
                    canciones.add(cancion);
//
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public static void main(String[] args) {
        ArrayList<Cancion> canciones = leerCanciones();
        System.out.println(canciones.size());

    }
}