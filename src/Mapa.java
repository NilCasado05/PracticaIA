import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Mapa {
    private TipusCarretera[][] matriu;
    private int amplada;
    private int alcada;
    
    public void carregarDeFitxer(String nomFitxer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            String linia = br.readLine();
            if (linia == null) return;
            
            // Determinar dimensions
            String[] dimensions = linia.trim().split("\\s+");
            if (dimensions.length == 2) {
                alcada = Integer.parseInt(dimensions[0]);
                amplada = Integer.parseInt(dimensions[1]);
            }
            
            matriu = new TipusCarretera[alcada][amplada];
            
            // Llegir el mapa
            for (int i = 0; i < alcada; i++) {
                linia = br.readLine();
                if (linia == null) break;
                
                // Eliminar espais en blanc si n'hi ha
                linia = linia.replaceAll("\\s+", "");
                
                for (int j = 0; j < amplada && j < linia.length(); j++) {
                    matriu[i][j] = TipusCarretera.fromChar(linia.charAt(j));
                }
            }
        }
    }
    
    public TipusCarretera getTipus(int x, int y) {
        if (x < 0 || x >= alcada || y < 0 || y >= amplada) {
            return TipusCarretera.BUID;
        }
        return matriu[x][y];
    }
    
    public int getAmplada() {
        return amplada;
    }
    
    public int getAlcada() {
        return alcada;
    }
    
    public void imprimir() {
        for (int i = 0; i < alcada; i++) {
            for (int j = 0; j < amplada; j++) {
                System.out.print(matriu[i][j].getSimbol() + " ");
            }
            System.out.println();
        }
    }
    
    public boolean dinsLimits(int x, int y) {
        return x >= 0 && x < alcada && y >= 0 && y < amplada;
    }
}