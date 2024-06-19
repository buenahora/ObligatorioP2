package Classes;

import java.util.Objects;

public class ArtistaCantidad implements Comparable<ArtistaCantidad> {
    public String artista;
    public int cantidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistaCantidad that = (ArtistaCantidad) o;
        return Objects.equals(artista, that.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artista);
    }

    public ArtistaCantidad(String artista, int cantidad) {
        this.artista = artista;
        this.cantidad = cantidad;
    }


    @Override
    public int compareTo(ArtistaCantidad o) {
        return Integer.compare(o.cantidad, this.cantidad);
    }
}
