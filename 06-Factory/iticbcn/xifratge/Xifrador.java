package iticbcn.xifratge;

public interface Xifrador {
    public abstract TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;

    public abstract String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
