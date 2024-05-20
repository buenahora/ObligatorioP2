package Tests;
import TADs.hashcerrado.HashCerrado;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;;
public class hashcerradotest {
    HashCerrado hash;
    @Before
    public void setup(){
        hash = new HashCerrado();
    }
    @Test
    public void testInsertar(){
        hash.insertar(1);
        assertEquals(1,hash.getSize());
        hash.insertar(3);
        assertEquals(2,hash.getSize());

    }
    @Test
    public void testPertenece(){
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);
        assertTrue(hash.pertenece(10));
        assertTrue(hash.pertenece(5));
        assertTrue(hash.pertenece(7));
        assertFalse(hash.pertenece(3));
    }
    @Test
    public void testEliminar(){
        hash.insertar(10);
        hash.insertar(5);
        hash.insertar(7);

        hash.eliminar(7);

        assertEquals(2,hash.getSize());
        assertFalse(hash.pertenece(7));
    }
    @Test
    public void testExpandirTabla(){

    }
}
