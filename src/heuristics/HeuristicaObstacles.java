package heuristics;

import model.*;

public class HeuristicaObstacles implements Heuristica {

    @Override
    public double calcular(Estat a, Estat b, Mapa mapa) {

        // Distància Manhattan
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        // Caixa rectangular entre els dos punts
        int minX = Math.min(a.x, b.x);
        int maxX = Math.max(a.x, b.x);
        int minY = Math.min(a.y, b.y);
        int maxY = Math.max(a.y, b.y);

        int obstacles = 0;

        // Recorrem la caixa i comptem quants BLOQUEJAT hi ha
        for(int x = minX; x <= maxX; x++){
            for(int y = minY; y <= maxY; y++){

                // Comprovem límits
                if(x >= 0 && y >= 0 && x < mapa.getAmplada() && y < mapa.getAltura()){

                    TipusCarretera t = mapa.getTipus(x, y);

                    if(t == TipusCarretera.BLOQUEJAT){
                        obstacles++;
                    }
                }
            }
        }

        // Penalització forta per obstacles reals
        return (dx + dy) + obstacles * 5;
    }
}
