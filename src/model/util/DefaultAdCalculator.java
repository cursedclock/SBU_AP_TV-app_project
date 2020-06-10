package model.util;

import model.programs.Advertisement;
import model.programs.Program;

import java.util.Set;

public class DefaultAdCalculator implements AdCalculator{
    private static final int neighbourMultiplier = 2;
    private static final int durationMultiplier = 5;
    public static final int base = 100;

    String name;

    public DefaultAdCalculator(String name){
        this.name = name;
    }

    public DefaultAdCalculator(){
        this("Default");
    }
    @Override
    public int calcPrice(Advertisement ad, Set<Program> programs) {
        int neighbouring = 0;
        for (Program p: programs){
            int comp = p.checkCollision(ad);
            switch(comp){
                case 0: return 0;
                case 1: if(!(p instanceof Advertisement)) neighbouring++;
            }
        }
        return base+neighbouring*neighbourMultiplier*ad.getDuration()*durationMultiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}
