package Classes;

import java.util.Objects;

public class Cancion implements Comparable<Cancion> {
    private String spotify_id;
    private String name;
    private String artists;
    private int daily_rank;
    private int daily_movement;
    private int weekly_movement;
    private String country;
    private String snapshot_date;
    private int popularity;
    private boolean is_explicit;
    private int duration_ms;
    private String album_name;
    private String album_release_date;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private int time_signature;
    private int contador = 1;

    public int getContador() {
        return contador;
    }

    public String getSpotify_id() {
        return spotify_id;
    }

//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancion cancion = (Cancion) o;
        return Objects.equals(spotify_id, cancion.spotify_id);
    }

    public String getName() {
        return name;
    }

    public String getArtists() {
        return artists;
    }

    public int getDaily_rank() {
        return daily_rank;
    }

    public int getDaily_movement() {
        return daily_movement;
    }

    public int getWeekly_movement() {
        return weekly_movement;
    }

    public String getCountry() {
        return country;
    }

    public String getSnapshot_date() {
        return snapshot_date;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isIs_explicit() {
        return is_explicit;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getAlbum_release_date() {
        return album_release_date;
    }

    public double getDanceability() {
        return danceability;
    }

    public double getEnergy() {
        return energy;
    }

    public int getKey() {
        return key;
    }

    public double getLoudness() {
        return loudness;
    }

    public int getMode() {
        return mode;
    }

    public double getSpeechiness() {
        return speechiness;
    }

    public double getAcousticness() {
        return acousticness;
    }

    public double getInstrumentalness() {
        return instrumentalness;
    }

    public double getLiveness() {
        return liveness;
    }

    public double getValence() {
        return valence;
    }

    public double getTempo() {
        return tempo;
    }

    public int getTime_signature() {
        return time_signature;
    }

    public Cancion(String spotify_id, String name, String artists, int daily_rank, int daily_movement, int weekly_movement, String country, String snapshot_date, int popularity, boolean is_explicit, int duration_ms, String album_name, String album_release_date, double danceability, double energy, int key, double loudness, int mode, double speechiness, double acousticness, double instrumentalness, double liveness, double valence, double tempo, int time_signature) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
        this.daily_rank = daily_rank;
        this.daily_movement = daily_movement;
        this.weekly_movement = weekly_movement;
        this.country = country;
        this.snapshot_date = snapshot_date;
        this.popularity = popularity;
        this.is_explicit = is_explicit;
        this.duration_ms = duration_ms;
        this.album_name = album_name;
        this.album_release_date = album_release_date;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.time_signature = time_signature;
    }

    public void aumentarcontador() {
        contador++;
    }

    @Override
    public int compareTo(Cancion o) {
        return Integer.compare(this.contador, o.getContador());
    }

    @Override
    public String toString() {
        return "spotify_id='" + spotify_id + '\'' +
                ", name='" + name + '\'' +
                ", contador=" + contador;
    }
}
