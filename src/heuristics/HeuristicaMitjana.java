package heuristics;

import model.*;

public class HeuristicaMitjana implements Heuristica{

    // És una estimació més realista perquè té en 
    // compte el cost mitjà de les carreteres

    public double calcular(Estat a, Estat b){

        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return (dx + dy) * 1;
    }
}