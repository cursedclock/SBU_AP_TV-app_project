package model.users;

import model.Channel;
import model.programs.*;
import java.util.Set;

public class StationManager extends UserAccount{
    public Channel station;

    public StationManager(String username, String password, Channel station){
        super(username, password);
        this.station = station;
    }

    public StationManager(String username, String password){
        super(username, password);
    }

    public void addProgram(Program program){
        if (station.checkOverlap(program)){
            throw new IllegalArgumentException("Programs cannot overlap.");
        }
        station.programs.add(program);
    }

    public void removeProgram(Program program){
        if (!station.programs.remove(program)){
            throw new RuntimeException("Program not found.");
        }
    }

    public void replaceProgram(Program oldProgram, Program newProgram){
        if (!station.programs.remove(oldProgram)){
            throw new RuntimeException("Program not found.");
        }
        if (station.checkOverlap(newProgram)){
            station.programs.add(oldProgram);
            throw new RuntimeException("Programs cannot overlap.");
        }
        station.programs.add(newProgram);
    }

    public void approveAd(Advertisement ad){
        if (!station.adRequests.contains(ad)){
            throw new RuntimeException("Please select a request.");
        }
        int price = station.adCalculator.calcPrice(ad,station.programs);
        if (price>ad.getAdCompany().getBalance()){
            throw new RuntimeException("Insufficient balance.");
        }
        addProgram(ad);
        station.adRequests.remove(ad);
        ad.getAdCompany().withdraw(price);
    }

    public boolean declineAd(Advertisement ad){
        return station.adRequests.remove(ad);
    }

    public Set<Advertisement> getAdRequests(){
        return station.adRequests;
    }

    public Set<Rateable> getPrograms(){
        return station.getRateables();
    }
}
