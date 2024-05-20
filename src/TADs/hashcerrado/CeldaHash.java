package TADs.hashcerrado;

public class CeldaHash {
        public static final int Vacio = 0;
        public static final int Ocupado = 1;
        public static final int Borrado = 2;
        private Object elemento;
        private int estado;

        public CeldaHash(Object elemento, int estado){
            this.elemento = elemento;
            this.estado = estado;
        }

        public void setElemento(Object elemento){
            this.elemento = elemento;
        }

        public void setEstado(int estado){
            this.estado = estado;
        }

        public Object getElemento(){
            return this.elemento;
        }

        public int getEstado(){
            return this.estado;
        }
}
