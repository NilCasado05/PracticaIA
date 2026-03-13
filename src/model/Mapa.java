package model;

import java.util.*;

public class Mapa {

    private TipusCarretera[][] grid;

    public Mapa(TipusCarretera[][] grid){
        this.grid = grid;
    }

    public TipusCarretera getTipus(int x,int y){
        return grid[x][y];
    }

    public int getAmplada(){
        return grid.length;
    }

    public int getAltura(){
        return grid[0].length;
    }

    public boolean esAccessible(int x,int y){

        if(x < 0 || y < 0 || x >= getAmplada() || y >= getAltura()){
            return false;
        }

        return grid[x][y] != TipusCarretera.BLOQUEJAT;
    }

    public List<Estat> getVeins(Estat e){

        List<Estat> veins = new ArrayList<>();

        int[][] moviments = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        for(int[] m : moviments){

            int nx = e.x + m[0];
            int ny = e.y + m[1];

            if(esAccessible(nx,ny)){
                veins.add(new Estat(nx,ny,getTipus(nx,ny)));
            }
        }

        return veins;
    }
}