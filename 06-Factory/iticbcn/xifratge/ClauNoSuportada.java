package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {
    public ClauNoSuportada() {
        super("La clau indicada no es valid");
    }

    public ClauNoSuportada(String mensaje) {
        super(mensaje);
    }
}
