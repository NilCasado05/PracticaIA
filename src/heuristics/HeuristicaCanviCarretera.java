package heuristics;

import model.*;

public class HeuristicaCanviCarretera implements Heuristica{

    public double calcular(Estat a, Estat b){

        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        double dist = dx + dy;

        return dist * a.tipus.cost + 3;
    }
}