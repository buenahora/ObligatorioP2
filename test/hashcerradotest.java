package Tests;
import Classes.Cancion;
import Classes.Exceptions.HashExceptions.DuplicateKeyHash;
import uy.edu.um.prog2.adt.hashcerrado.HashCerrado;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class hashcerradotest {
    HashCerrado hash;
    @Before
    public void setup(){
        hash = new HashCerrado();
    }
    @Test
    public void testInsertar() throws DuplicateKeyHash {
        hash.insertar(1);
        assertEquals(1,hash.getSize());
        hash.insertar(3);
        assertEquals(2,hash.getSize());
    }
    @Test(expected = DuplicateKeyHash.class)
    public void testInsertarDuplicado() throws DuplicateKeyHash {
        Cancion cancion = new Classes.Cancion("Cancion1","Artista1","Pepe",1,1,1,"1","a",1,false,1,"a","a",1,1,1,1,1,1,1,1,1,1,1,1);

        hash.insertar(cancion);
        hash.insertar(cancion);

    }
    @Test
    public void testPertenece() throws DuplicateKeyHash {
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);
        assertTrue(hash.pertenece(10));
        assertTrue(hash.pertenece(5));
        assertTrue(hash.pertenece(7));
        assertFalse(hash.pertenece(3));
    }
    @Test
    public void testEliminar() throws DuplicateKeyHash {
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);
        hash.eliminar(7);

        assertEquals(2,hash.getSize());//verifica que el tamaño bajo
        assertFalse(hash.pertenece(7));//verifica que el elemento ya no esta
        assertEquals(2,hash.getTabla()[7].getEstado());//verifica que la celda esta marcada como borrada
    }
    @Test
    public void testExpandirTabla() throws DuplicateKeyHash {
        for (int i = 0; i < 11; i++) {
                hash.insertar(i);
        }
        assertEquals(11,hash.getSize());
        assertEquals(20,hash.getTabla().length);
    }
}
