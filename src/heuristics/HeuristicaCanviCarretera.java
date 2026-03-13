package heuristics;

import model.*;
import search.A;

public class HeuristicaCanviCarretera implements Heuristica{

    // A més de la distància, considera que canviar de 
    // tipus de carretera té cost extra

    public double calcular(Estat a, Estat b){

        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        double dist = dx + dy;

        return dist * a.tipus.cost + 3;
    }
}