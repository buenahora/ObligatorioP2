package Tests;
import Classes.Exceptions.HashExceptions.DuplicateKey;
import TADs.hashcerrado.HashCerrado;
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
    public void testInsertar() throws DuplicateKey {
        hash.insertar(1);
        assertEquals(1,hash.getSize());
        hash.insertar(3);
        assertEquals(2,hash.getSize());
    }
    @Test(expected = DuplicateKey.class)
    public void testInsertarDuplicado() throws DuplicateKey {
        hash.insertar(1);
        hash.insertar(1);
    }
    @Test
    public void testPertenece() throws DuplicateKey {
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);
        assertTrue(hash.pertenece(10));
        assertTrue(hash.pertenece(5));
        assertTrue(hash.pertenece(7));
        assertFalse(hash.pertenece(3));
    }
    @Test
    public void testEliminar() throws DuplicateKey {
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);
        hash.eliminar(7);

        assertEquals(2,hash.getSize());//verifica que el tamaño bajo
        assertFalse(hash.pertenece(7));//verifica que el elemento ya no esta
        assertEquals(2,hash.getTabla()[7].getEstado());//verifica que la celda esta marcada como borrada
    }
    @Test
    public void testExpandirTabla(){
        for (int i = 0; i < 11; i++) {
            try {
                hash.insertar(i);
            } catch (DuplicateKey duplicateKey) {
                System.out.println("Error");
            }
        }
        assertEquals(10,hash.getSize());
        assertEquals(20,hash.getTabla().length);
    }
}
