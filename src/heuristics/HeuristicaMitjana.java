package heuristics;

import model.*;

public class HeuristicaMitjana implements Heuristica{

    public double calcular(Estat a, Estat b){

        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return (dx + dy) * 1;
    }
}