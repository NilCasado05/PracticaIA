import java.util.*;

public class CercaAEstrella extends Cerca {
    
    public CercaAEstrella(Mapa mapa, Heuristica heuristica) {
        super(mapa, heuristica);
    }
    
    @Override
    public List<int[]> cercar(int xi, int yi, int xf, int yf) {
        iteracions = 0;
        esOptima = heuristica.esAdmissible(); // Si l'heurística és admissible, A* troba l'òptim
        estatsExpandits.clear();
        
        // PriorityQueue ordenada per cost total (real + heurístic)
        PriorityQueue<Estat> oberts = new PriorityQueue<>();
        
        Map<String, Double> millorCostArribar = new HashMap<>();
        Set<String> tancats = new HashSet<>();
        
        TipusCarretera tipusInicial = mapa.getTipus(xi, yi);
        if (!tipusInicial.esTransitable()) {
            System.out.println("Posició inicial no transitable");
            return null;
        }
        
        Estat inicial = new Estat(xi, yi, tipusInicial, 0);
        double hInicial = heuristica.calcular(xi, yi, xf, yf, tipusInicial, mapa.getTipus(xf, yf));
        inicial.setCostHeuristic(hInicial);
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