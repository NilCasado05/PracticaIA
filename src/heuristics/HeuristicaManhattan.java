package heuristics;

import model.*;

public class HeuristicaManhattan implements Heuristica {

    @Override
    public double calcular(Estat a, Estat b, Mapa mapa) {

        // Distància Manhattan entre els dos punts
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return dx + dy;
    }
}
