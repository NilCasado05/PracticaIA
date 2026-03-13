package model;

public enum TipusCarretera {

    AUTOVIA(0.5),
    NACIONAL(1),
    COMARCAL(2),
    BLOQUEJAT(-1);

    public double cost;

    TipusCarretera(double cost){
        this.cost = cost;
    }
}