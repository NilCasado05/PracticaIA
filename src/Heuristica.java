public interface Heuristica {
    double calcular(int xActual, int yActual, int xDesti, int yDesti, TipusCarretera tipusActual, TipusCarretera tipusDesti);
    boolean esAdmissible();
    String getNom();
}