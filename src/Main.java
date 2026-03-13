import java.util.Scanner;

import model.*;
import search.*;
import heuristics.*;
import lector.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Mapa mapa = LectorMapa.llegir("mapa.txt");

        int amplada = mapa.getAmplada();
        int altura = mapa.getAltura();

        Estat inici = new Estat(0,0,mapa.getTipus(0,0));
        Estat finalEstat = new Estat(amplada-1,altura-1,mapa.getTipus(amplada-1,altura-1));

        // ----------- TRIAR ALGORITME -----------

        System.out.println("[ -- Selecciona algoritme -- ]");
        System.out.println("[ 1 ] - A*");
        System.out.println("[ 2 ] - Best First");

        int alg = sc.nextInt();

        // ----------- TRIAR HEURISTICA -----------

        System.out.println("[ -- Selecciona heuristica -- ]");
        System.out.println("[ 1 ] Manhattan");
        System.out.println("[ 2 ] Mitjana");
        System.out.println("[ 3 ] Canvi carretera");

        int heur = sc.nextInt();

        Heuristica h = null;
        String heuristicaNom = "";

        switch(heur){

            case 1:
                h = new HeuristicaManhattan();
                heuristicaNom = "Manhattan";
                break;

            case 2:
                h = new HeuristicaMitjana();
                heuristicaNom = "Mitjana";
                break;

            case 3:
                h = new HeuristicaCanviCarretera();
                heuristicaNom = "CanviCarretera";
                break;

            default:
                System.out.println("Heuristica no valida");
                sc.close();
                return;
        }

        Resultat r = null;
        String metodeNom = "";

        if(alg == 1){

            metodeNom = "A*";
            A astar = new A(mapa,h);
            r = astar.buscar(inici,finalEstat);

        }
        else if(alg == 2){

            metodeNom = "BestFirst";
            BestFirst bf = new BestFirst(mapa,h);
            r = bf.buscar(inici,finalEstat);

        }
        else{

            System.out.println("Algoritme no valid");
            sc.close();
            return;
        }

        if(r != null){
            r.mostrar(metodeNom,heuristicaNom);
        }
        else{
            System.out.println("No s'ha trobat cami");
        }

        sc.close();
    }
}