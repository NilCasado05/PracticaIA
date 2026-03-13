package search;

import java.util.*;

import model.*;
import heuristics.*;

public class BestFirst {

    private Mapa mapa;
    private Heuristica heuristica;

    public BestFirst(Mapa mapa, Heuristica h){
        this.mapa = mapa;
        this.heuristica = h;
    }

    // Best-First només segueix el node que sembla 
    // més proper al destí segons l’heurística

    public Resultat buscar(Estat inici, Estat objectiu){

        PriorityQueue<Node> open =
                new PriorityQueue<>(Comparator.comparingDouble(n -> n.h));

        Set<Estat> closed = new HashSet<>();

        Node start = new Node(inici,null,0,heuristica.calcular(inici,objectiu));
        open.add(start);

        int estatsTractats = 0;

        while(!open.isEmpty()){

            Node actual = open.poll();
            estatsTractats++;

            if(actual.estat.equals(objectiu)){

                List<Node> cami = reconstruirCami(actual);

                return new Resultat(
                        cami,
                        actual.g,
                        estatsTractats,
                        false
                );
            }

            closed.add(actual.estat);

            for(Estat vei : mapa.getVeins(actual.estat)){

                if(closed.contains(vei))
                    continue;

                double cost = costCarretera(actual.estat.tipus,vei.tipus);

                double g = actual.g + cost;
                double h = heuristica.calcular(vei,objectiu);

                Node fill = new Node(vei,actual,g,h);

                open.add(fill);
            }
        }

        return null;
    }

    private double costCarretera(TipusCarretera a, TipusCarretera b){

        double cost = 0;

        if(b == TipusCarretera.AUTOVIA) cost = 0.5;
        if(b == TipusCarretera.NACIONAL) cost = 1;
        if(b == TipusCarretera.COMARCAL) cost = 2;

        if(a != b)
            cost += 3;

        return cost;
    }

    private List<Node> reconstruirCami(Node node){

        List<Node> cami = new ArrayList<>();

        while(node != null){
            cami.add(node);
            node = node.pare;
        }

        Collections.reverse(cami);

        return cami;
    }
}