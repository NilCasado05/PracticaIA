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

    public Resultat buscar(Estat inici, Estat fi){

        PriorityQueue<Node> open =
                new PriorityQueue<>(Comparator.comparingDouble(n -> n.h));

        Set<Estat> closed = new HashSet<>();

        int estatsTractats = 0;

        Node nodeInicial =
                new Node(inici,null,0,heuristica.calcular(inici,fi));

        open.add(nodeInicial);

        while(!open.isEmpty()){

            Node actual = open.poll();

            if(closed.contains(actual.estat))
                continue;

            closed.add(actual.estat);
            estatsTractats++;

            if(actual.estat.equals(fi)){

                List<Node> cami = reconstruirCami(actual);

                return new Resultat(
                        cami,
                        actual.g,
                        estatsTractats,
                        false
                );
            }

            for(Estat successor : mapa.getVeins(actual.estat)){

                if(successor.tipus.equals("X"))
                    continue;

                double cost = calcularCost(actual.estat,successor);

                double g = actual.g + cost;

                double h = heuristica.calcular(successor,fi);

                Node fill = new Node(successor,actual,g,h);

                open.add(fill);
            }
        }

        return null;
    }

    private double calcularCost(Estat a, Estat b){

        double cost = 0;

        if(b.tipus.equals('A')) cost = 0.5;
        if(b.tipus.equals('N')) cost = 1;
        if(b.tipus.equals('C')) cost = 2;

        if(a.tipus != b.tipus)
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