package practica1;

import java.util.*;

public abstract class Cerca {
    protected Mapa mapa;
    protected Heuristica heuristica;
    protected int iteracions;
    protected boolean esOptima;
    protected double tempsSolucio;
    protected List<int[]> solucio;
    protected List<Estat> estatsExpandits; // Llista d'estats seleccionats/expandits
    
    // Moviments possibles: dreta, esquerra, avall, amunt
    protected static final int[][] MOVIMENTS = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    
    public Cerca(Mapa mapa, Heuristica heuristica) {
        this.mapa = mapa;
        this.heuristica = heuristica;
        this.iteracions = 0;
        this.esOptima = false;
        this.tempsSolucio = 0;
        this.solucio = null;
        this.estatsExpandits = new ArrayList<>();
    }
    
    protected double calcularCost(TipusCarretera origen, TipusCarretera desti) {
        double cost = desti.getTempsBase();
        
        // Si canvia de tipus, afegir penalització de +3
        if (origen != desti) {
            cost += 3;
        }
        
        return cost;
    }
    
    protected boolean esObjectiu(Estat estat, int xf, int yf) {
        return estat.getX() == xf && estat.getY() == yf;
    }
    
    public abstract List<int[]> cercar(int xi, int yi, int xf, int yf);
    
    protected void imprimirResultat() {
        System.out.println("=================================");
        System.out.println("Algorisme: " + this.getClass().getSimpleName());
        System.out.println("Heurística: " + heuristica.getNom());
        System.out.println("=================================");
        
        if (solucio == null || solucio.isEmpty()) {
            System.out.println("No s'ha trobat solució");
            return;
        }
        
        System.out.println("Solució trobada (camí):");
        for (int[] pos : solucio) {
            System.out.print("(" + pos[0] + "," + pos[1] + ") ");
        }
        System.out.println();
        System.out.println("Temps total: " + tempsSolucio);
        System.out.println("Iteracions: " + iteracions);
        System.out.println("Estats expandits: " + estatsExpandits.size());
        System.out.println("Solució òptima: " + (esOptima ? "SÍ" : "NO"));
        System.out.println("Heurística admissible: " + heuristica.esAdmissible());
        
        // Mostrar els estats expandits (primer 20 si n'hi ha molts)
        System.out.println("\nEstats expandits (seleccionats) en ordre:");
        int comptador = 0;
        for (Estat e : estatsExpandits) {
            System.out.print("(" + e.getX() + "," + e.getY() + ") ");
            comptador++;
            if (comptador % 10 == 0) System.out.println();
            if (comptador >= 30) {
                System.out.println("... i " + (estatsExpandits.size() - 30) + " més");
                break;
            }
        }
        System.out.println();
        System.out.println();
    }
    
    public int getIteracions() {
        return iteracions;
    }
    
    public boolean isEsOptima() {
        return esOptima;
    }
    
    public double getTempsSolucio() {
        return tempsSolucio;
    }
    
    public List<int[]> getSolucio() {
        return solucio;
    }
    
    public List<Estat> getEstatsExpandits() {
        return estatsExpandits;
    }
}