package model;

import model.programs.*;
import model.util.AdCalculator;
import model.users.StationManager;

import java.util.*;

public class Channel {
    private String name;
    public StationManager manager;
    public AdCalculator adCalculator;
    public Set<Program> programs;
    public Set<Advertisement> adRequests;

    public Channel(String name, AdCalculator adCalculator){
        this.name = name;
        this.adCalculator = adCalculator;
        programs = new HashSet<Program>();
        adRequests = new HashSet<Advertisement>();
    }

    public Channel(String name, AdCalculator adCalculator, StationManager manager){
        this(name, adCalculator);
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Set<Rateable> getRateables(){
        Set<Rateable> outp = new HashSet<>();
        for (Program p: programs){
            if (p instanceof Rateable){
                outp.add((Rateable)p);
            }
        }
        return outp;
    }

    public boolean checkOverlap(Program program){
        for (Program p : programs){
            if (p.checkCollision(program)==0){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return name;
    }
}
