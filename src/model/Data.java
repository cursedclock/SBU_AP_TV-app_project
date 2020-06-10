package model;

import model.programs.*;
import model.users.AdvertisementCompany;
import model.users.GeneralManager;
import model.users.StationManager;
import model.users.UserAccount;
import model.util.AdCalculator;
import model.util.DefaultAdCalculator;
import model.util.RepeatingSchedule;
import model.util.Schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Data {
    private static Data data = new Data();
    public Set<Channel> allChannels;
    public Set<UserAccount> allUsers;
    public Set<AdCalculator> adCalculators;
    public UserAccount currentUser;
    public Program programBuffer;

    public ArrayList<Rateable> testPrograms;

    private Data(){
        allChannels = new HashSet<>();
        allUsers = new HashSet<>();
        adCalculators = new HashSet<>();
        adCalculators.add(new DefaultAdCalculator());
        allUsers.add(GeneralManager.getInstance());

        testPrograms = new ArrayList<>();
        testPrograms.add(new Film("Plaid Runner",new Schedule("1-1-1","1:1","1:2"), new Schedule("1-2-1","1:1","1:2")));
        testPrograms.add(new News("Aperture",new RepeatingSchedule("12:0","12:15"),"Cave Johnson"));
        testPrograms.add(new Series("JoJo",new RepeatingSchedule("11:0","12:00"),new RepeatingSchedule("0:0","1:0")));

        Channel c = new Channel("ch1",new DefaultAdCalculator());
        for (Program p : testPrograms){
            if (!c.checkOverlap(p)){
                c.programs.add(p);
            }
        }
        allChannels.add(c);
    }

    public static Data getInstance(){
        return data;
    }

    public UserAccount login(String username, String password){
        for (UserAccount u : allUsers){
            if (username.equals(u.getUsername())){
                if (u.verify(password)){
                    return u;
                } else{
                    throw new RuntimeException("Wrong password");
                    }
                }
            }
        throw new RuntimeException("User does not exist.");
    }

    public boolean checkUsernameValidity(String username){
        for (UserAccount user : allUsers){
            if (username.equals(user.getUsername())){
                return false;
            }
        }
        return true;
    }
}

