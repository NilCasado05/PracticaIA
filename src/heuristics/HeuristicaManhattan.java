package heuristics;

import model.*;

public class HeuristicaManhattan implements Heuristica{

    public double calcular(Estat a, Estat b){

        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return (dx + dy) * 0.5;
    }
}