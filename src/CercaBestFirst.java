package practica1;

import java.util.*;

public class CercaBestFirst extends Cerca {
    
    public CercaBestFirst(Mapa mapa, Heuristica heuristica) {
        super(mapa, heuristica);
    }
    
    @Override
    public List<int[]> cercar(int xi, int yi, int xf, int yf) {
        iteracions = 0;
        esOptima = false;
        estatsExpandits.clear();
        
        // PriorityQueue ordenada per cost heurístic (no el real)
        PriorityQueue<Estat> oberts = new PriorityQueue<>(
            (e1, e2) -> Double.compare(e1.getCostHeuristic(), e2.getCostHeuristic())
        );
        
        Map<String, Double> millorCostArribar = new HashMap<>();
        Set<String> tancats = new HashSet<>();
        
        TipusCarretera tipusInicial = mapa.getTipus(xi, yi);
        if (!tipusInicial.esTransitable()) {
            System.out.println("Posició inicial no transitable");
            return null;
        }
        
        Estat inicial = new Estat(xi, yi, tipusInicial, 0);
        inicial.setCostHeuristic(heuristica.calcular(xi, yi, xf, yf, tipusInicial, mapa.getTipus(xf, yf)));
        oberts.add(inicial);
        millorCostArribar.put(inicial.getCoordenadesKey(), 0.0);
        
        while (!oberts.isEmpty()) {
            iteracions++;
            Estat actual = oberts.poll();
            String clauActual = actual.getCoordenadesKey();
            
            // Si ja està a tancats, saltar
            if (tancats.contains(clauActual)) {
                continue;
            }
            
            // Marcar com a expandit/seleccionat
            actual.setExpandit(true);
            estatsExpandits.add(actual);
            
            // Comprovar si és objectiu
            if (esObjectiu(actual, xf, yf)) {
                solucio = actual.getCami();
                tempsSolucio = actual.getCostReal();
                
                // Best-first no garanteix optimalitat
                esOptima = false;
                
                imprimirResultat();
                return solucio;
            }
            
            tancats.add(clauActual);
            
            // Generar successors
            for (int[] mov : MOVIMENTS) {
                int nx = actual.getX() + mov[0];
                int ny = actual.getY() + mov[1];
                
                if (!mapa.dinsLimits(nx, ny)) continue;
                
                TipusCarretera tipusDesti = mapa.getTipus(nx, ny);
                if (!tipusDesti.esTransitable()) continue;
                
                double costMoviment = calcularCost(actual.getTipus(), tipusDesti);
                double nouCostReal = actual.getCostReal() + costMoviment;
                
                String clauDesti = nx + "," + ny;
                
                // Si ja hem visitat aquesta posició amb un cost menor, saltar
                if (millorCostArribar.containsKey(clauDesti) && 
                    millorCostArribar.get(clauDesti) <= nouCostReal) {
                    continue;
                }
                
                Estat successor = new Estat(nx, ny, tipusDesti, nouCostReal, actual);
                
                // Calcular heurística per al successor
                double h = heuristica.calcular(nx, ny, xf, yf, tipusDesti, mapa.getTipus(xf, yf));
                successor.setCostHeuristic(h);
                
                millorCostArribar.put(clauDesti, nouCostReal);
                oberts.add(successor);
            }
        }
        
        System.out.println("No s'ha trobat camí");
        return null;
    }
}