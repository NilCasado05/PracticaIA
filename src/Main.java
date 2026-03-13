import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {

            Mapa mapa = new Mapa("src/mapa.txt");

            // Coordenades automàtiques (mai més error d'índex)
            int ultimaFila = mapa.getFiles() - 1;
            int ultimaColumna = mapa.getColumnes() - 1;

            Estat inici = new Estat(0, 0, mapa.getTipus(0, 0));
            Estat fi = new Estat(ultimaFila, ultimaColumna,
                                 mapa.getTipus(ultimaFila, ultimaColumna));

            System.out.println("===== A* amb H1 =====");
            executarAStar(mapa, inici, fi);

        } catch (IOException e) {
            System.out.println("Error carregant el mapa: " + e.getMessage());
        }
    }

    // =============================
    // A* SIMPLE I FUNCIONAL
    // =============================
    public static void executarAStar(Mapa mapa, Estat inici, Estat fi) {

        PriorityQueue<Estat> oberts =
                new PriorityQueue<>(Comparator.comparingDouble(Estat::f));

        Set<String> tancats = new HashSet<>();
        int estatsTractats = 0;

        inici.g = 0;
        inici.h = Heuristiques.h1(inici, fi);

        oberts.add(inici);

        while (!oberts.isEmpty()) {

            Estat actual = oberts.poll();
            estatsTractats++;

            if (actual.x == fi.x && actual.y == fi.y) {
                imprimirResultat(actual, estatsTractats);
                return;
            }

            tancats.add(actual.x + "," + actual.y);

            for (Estat s : mapa.successors(actual)) {

                String clau = s.x + "," + s.y;

                if (!tancats.contains(clau)) {

                    double cost = actual.g + s.tipus.getCost();

                    if (actual.tipus != s.tipus)
                        cost += 3;

                    s.g = cost;
                    s.h = Heuristiques.h1(s, fi);
                    s.pare = actual;

                    oberts.add(s);
                }
            }
        }

        System.out.println("No hi ha solució.");
    }

    // =============================
    // IMPRIMIR RESULTAT
    // =============================
    public static void imprimirResultat(Estat fi, int estatsTractats) {

        List<Estat> cami = new ArrayList<>();

        while (fi != null) {
            cami.add(fi);
            fi = fi.pare;
        }

        Collections.reverse(cami);

        System.out.println("Camí trobat:");
        for (Estat e : cami)
            System.out.print(e + " ");

        System.out.println("\nCost total: " + cami.get(cami.size() - 1).g);
        System.out.println("Estats tractats: " + estatsTractats);
    }
}