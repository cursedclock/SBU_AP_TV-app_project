package model.users;

import model.Channel;
import model.programs.Advertisement;
import model.util.Schedule;

public class AdvertisementCompany extends UserAccount{
    public Channel watching;
    private int balance;

    public AdvertisementCompany(String username, String password) {
        super(username,password);
        balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public int withdraw(int amount){
        if (amount>balance){
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        return balance;
    }

    public int deposit(int amount){
        balance += amount;
        return balance;
    }

    public void requestAd(String name, Schedule schedule){
        Advertisement newAd = new Advertisement(name, schedule, this);
        if (watching.checkOverlap(newAd)){
            throw new IllegalArgumentException("Ad cannot overlap with other programs.");
        }
        watching.adRequests.add(newAd);
    }
}
