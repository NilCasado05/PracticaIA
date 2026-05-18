package heuristics;

import model.*;

public class HeuristicaCanviCarretera implements Heuristica {

    @Override
    public double calcular(Estat a, Estat b, Mapa mapa) {

        // Si el tipus de carretera és diferent, penalitzem
        if(a.tipus != b.tipus){
            return 3;   // Penalització per canvi de carretera
        }

        // Si és el mateix tipus, cap penalització
        return 0;
    }
}
