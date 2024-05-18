package TADs.hashcerrado;

public class celdahash {
        private Object elem;
        private int estado;

        public celdahash(Object elem, int estado){
            this.elem = elem;
            this.estado = estado;
        }

        public void setElem(Object elem){
            this.elem = elem;
        }

        public void setEstado(int estado){
            this.estado = estado;
        }

        public Object getElem(){
            return this.elem;
        }

        public int getEstado(){
            return this.estado;
        }
}
