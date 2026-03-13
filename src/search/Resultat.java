package search;

import java.util.*;

public class Resultat {

    public List<Node> cami;
    public double tempsTotal;
    public int estatsTractats;
    public boolean esOptim;

    public Resultat(List<Node> cami,double tempsTotal,int estatsTractats,boolean esOptim){
        this.cami = cami;
        this.tempsTotal = tempsTotal;
        this.estatsTractats = estatsTractats;
        this.esOptim = esOptim;
    }

    public void mostrar(String metode,String heuristica){

        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("RESULTAT DE LA CERCA");
        System.out.println("---------------------------------");

        System.out.println("Cami trobat amb metode " + metode + " amb la heuristica " + heuristica);

        System.out.print("Cami: ");

        for(int i = 0; i < cami.size(); i++){

            Node n = cami.get(i);

            System.out.print("(" + n.estat.x + "," + n.estat.y + ")");

            if(i < cami.size() - 1){
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("Longitud del cami: " + cami.size());
        System.out.println("Temps total: " + tempsTotal);
        System.out.println("Estats tractats: " + estatsTractats);
        System.out.println("Es optim: " + esOptim);

        System.out.println("---------------------------------");
    }
}