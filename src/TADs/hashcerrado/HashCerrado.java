package TADs.hashcerrado;

import Classes.Exceptions.HashExceptions.DuplicateKeyHash;

public class HashCerrado {
    private static final int TAMANIO_INICIAL = 10; // Tamaño inicial de la tabla
    private CeldaHash[] tabla; // Array que representa la tabla de hash
    private  int size_borrados;
    private int size; // Cantidad de elementos en la tabla

    //Getter del size
    public int getSize() {
        return size;
    }

    public CeldaHash[] getTabla() {
        return tabla;
    }

    // Constructor de la clase
    public HashCerrado() {
        // Inicializa la tabla con un tamaño inicial y todas las celdas vacías
        this.tabla = new CeldaHash[TAMANIO_INICIAL];
        for (int i = 0; i < TAMANIO_INICIAL; i++) {
            tabla[i] = new CeldaHash(null, CeldaHash.Vacio);
        }
        this.size = 0; // Inicialmente, la tabla no tiene elementos
        this.size_borrados = 0;
    }

    // Función hash: transforma la clave en un índice de la tabla
    public int hash(Object key) {
        return Math.abs(key.hashCode()) % tabla.length;
    }



    // Inserta una nueva clave en la tabla
    public void insertar(Object key) throws DuplicateKeyHash {

        // Si la tabla está llena, se duplica su tamaño
        if (size_borrados >= Math.round((tabla.length * 0.75))) {
            expandirTabla();
        }

        // Calcula el índice de la clave
        int i = hash(key);
        // Si la celda está ocupada, busca la siguiente celda vacía
        while (tabla[i].getEstado() == CeldaHash.Ocupado || tabla[i].getEstado() == CeldaHash.Borrado) {
            if (tabla[i].getElemento().equals(key)) {
                throw new DuplicateKeyHash();
            }
            i = (i + 1) % tabla.length;
        }

        // Inserta la clave en la celda encontrada y actualiza el estado de la celda
        tabla[i].setElemento(key);
        tabla[i].setEstado(CeldaHash.Ocupado);
        size++; // Incrementa el tamaño de la tabla
        size_borrados++;
    }

    // Verifica si una clave pertenece a la tabla
    public boolean pertenece(Object key) {
        // Calcula el índice de la clave
        int i = hash(key);
        // Busca la clave en la tabla
        while (tabla[i].getEstado() != CeldaHash.Vacio) {
            // Si la celda está ocupada y la clave de la celda es igual a la clave buscada, retorna true
            if (tabla[i].getEstado() == CeldaHash.Ocupado && tabla[i].getElemento().equals(key)) {
                return true;
            }
            i = (i + 1) % tabla.length;
        }
        // Si no se encontró la clave, retorna false
        return false;
    }

    // Elimina una clave de la tabla
    public void eliminar(Object key) {
        // Calcula el índice de la clave
        int i = hash(key);
        // Busca la clave en la tabla
        while (tabla[i].getEstado() != CeldaHash.Vacio) {
            // Si la celda está ocupada y la clave de la celda es igual a la clave a eliminar, actualiza el estado de la celda a Borrado
            if (tabla[i].getEstado() == CeldaHash.Ocupado && tabla[i].getElemento().equals(key)) {
                tabla[i].setEstado(CeldaHash.Borrado);
                size--; // Decrementa el tamaño de la tabla
                return;
            }
            i = (i + 1) % tabla.length;
        }
    }

    // Expande la tabla de hash al duplicar su tamaño
    private void expandirTabla() throws DuplicateKeyHash {
        // Guarda la tabla antigua
        CeldaHash[] tablaAntigua = tabla;
        // Crea una nueva tabla con el doble de tamaño
        tabla = new CeldaHash[2 * tablaAntigua.length];
        size = 0; // Reinicia el tamaño de la tabla
        size_borrados = 0;
        // Inicializa la nueva tabla con todas las celdas vacías
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = new CeldaHash(null, CeldaHash.Vacio);
        }
        // Inserta en la nueva tabla todas las claves de la tabla antigua que estaban en celdas ocupadas
        for (CeldaHash celda : tablaAntigua) {
            if (celda.getEstado() == CeldaHash.Ocupado) {
                insertar(celda.getElemento());
            }
        }
    }

}