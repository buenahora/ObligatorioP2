package TADs.hashcerrado;

public class celdahash {
        private Object elemento;
        private int estado;

        public celdahash(Object elemento, int estado){
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
