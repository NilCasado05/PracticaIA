package model;

public class Estat {

    public int x;
    public int y;
    public TipusCarretera tipus;

    public Estat(int x,int y,TipusCarretera tipus){
        this.x = x;
        this.y = y;
        this.tipus = tipus;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Estat)) return false;
        Estat e = (Estat)o;
        return this.x == e.x && this.y == e.y;
    }

    @Override
    public int hashCode(){
        return x*31 + y;
    }
}