package TADs.hashcerrado;

public class hashcerrado {
    public int size = 0;
    private celdahash[] hash;
    private int cant;
    public static final int vacio = 0;
    public static final int ocupado = 1;
    public static final int borrado = -1;
    public hashcerrado() {
        this.hash = new celdahash[this.size];
        cant = 0;
    }

    private int rehash(Object buscado) {
        /*
         * Reorganiza la estructura para que pueda almacenar mas elementos.
         */
        return 0;
    }

    public boolean pertenece(Object elem) {
        /*
         * Verifica si el elemento se encuentra en la estructura.
         */
        return false;
    }

    public boolean insertar(Object elem) {
        /*
         * Inserta el elemento en la estructura.
         */
        return false;
    }

    public boolean eliminar(Object buscado) {

        /*
         * Elimina el elemento de la estructura.
         */

        // Calcula posicion inicial e incremento
        int pos = buscado.hashCode() % this.size;
        int incremento = this.rehash(buscado) % this.size;
        boolean encontrado = false;
        int intento = 1;
        
        // Busca el elemento hasta encontrarlo o encontrar una celda vacia
        // o para despues de TAM intentos
        while (!encontrado && intento < this.size && this.hash[pos].getEstado() != vacio) {

            if (this.hash[pos].getEstado() == ocupado) {

                encontrado = this.hash[pos].getElemento() == buscado;
                if (encontrado) {
                    // Si lo encuentra lo marca y para el ciclo
                    this.hash[pos].setEstado(borrado);
                    this.cant--;
                }
            }
            pos = (pos + intento * incremento) & this.size;
            intento++;
        }
        return encontrado;
    }
}
