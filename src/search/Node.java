package search;

import model.Estat;

public class Node {

    public Estat estat;
    public Node pare;

    public double g;
    public double h;
    public double f;

    public Node(Estat estat, Node pare, double g, double h){
        this.estat = estat;
        this.pare = pare;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }
}