import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estat implements Comparable<Estat> {
    private int x;
    private int y;
    private TipusCarretera tipus;
    private double costReal;
    private double costHeuristic;
    private List<int[]> cami;
    private Estat pare;
    private boolean expandit; // Indica si aquest estat ha estat seleccionat/expandit
    
    public Estat(int x, int y, TipusCarretera tipus, double costReal) {
        this.x = x;
        this.y = y;
        this.tipus = tipus;
        this.costReal = costReal;
        this.cami = new ArrayList<>();
        this.cami.add(new int[]{x, y});
        this.pare = null;
        this.expandit = false;
    }
    
    public Estat(int x, int y, TipusCarretera tipus, double costReal, Estat pare) {
        this.x = x;
        this.y = y;
        this.tipus = tipus;
        this.costReal = costReal;
        this.pare = pare;
        this.cami = new ArrayList<>();
        if (pare != null) {
            this.cami.addAll(pare.getCami());
        }
        this.cami.add(new int[]{x, y});
        this.expandit = false;
    }
    
    public double getCostTotal() {
        return costReal + costHeuristic;
    }
    
    public double getCostReal() {
        return costReal;
    }
    
    public void setCostReal(double costReal) {
        this.costReal = costReal;
    }
    
    public double getCostHeuristic() {
        return costHeuristic;
    }
    
    public void setCostHeuristic(double costHeuristic) {
        this.costHeuristic = costHeuristic;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public TipusCarretera getTipus() {
        return tipus;
    }
    
    public List<int[]> getCami() {
        return cami;
    }
    
    public Estat getPare() {
        return pare;
    }
    
    public boolean isExpandit() {
        return expandit;
    }
    
    public void setExpandit(boolean expandit) {
        this.expandit = expandit;
    }
    
    public String getCoordenadesKey() {
        return x + "," + y;
    }
    
    @Override
    public int compareTo(Estat altre) {
        // Ordenar per cost total (real + heuristic) per A*
        // Per Best-first només usarem costHeuristic
        return Double.compare(this.getCostTotal(), altre.getCostTotal());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estat estat = (Estat) o;
        return x == estat.x && y == estat.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ") " + tipus + " cost:" + costReal;
    }
}