package lector;

import java.io.*;
import java.util.*;
import model.*;

public class LectorMapa {

    public static Mapa llegir(String fitxer){

        try{

            BufferedReader br = new BufferedReader(new FileReader(fitxer));

            List<TipusCarretera[]> files = new ArrayList<>();

            String linea;

            while((linea = br.readLine()) != null){

                String[] parts = linea.split(" ");

                TipusCarretera[] fila = new TipusCarretera[parts.length];

                for(int i=0;i<parts.length;i++){

                    switch(parts[i]){

                        case "A":
                            fila[i] = TipusCarretera.AUTOVIA;
                            break;

                        case "N":
                            fila[i] = TipusCarretera.NACIONAL;
                            break;

                        case "C":
                            fila[i] = TipusCarretera.COMARCAL;
                            break;

                        default:
                            fila[i] = TipusCarretera.BLOQUEJAT;
                    }
                }

                files.add(fila);
            }

            TipusCarretera[][] grid = files.toArray(new TipusCarretera[0][]);

            br.close();

            return new Mapa(grid);

        }catch(Exception e){

            e.printStackTrace();
            return null;
        }
    }
}