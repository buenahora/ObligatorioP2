package Classes;

public class CancionCantidad implements Comparable<CancionCantidad> {
    private String spotifyId;
    private int cantidad;

    @Override
    public int compareTo(CancionCantidad o) {
        return Integer.compare(this.cantidad, o.cantidad);
    }

    public CancionCantidad(String spotifyId, int cantidad) {
        this.spotifyId = spotifyId;
        this.cantidad = cantidad;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
