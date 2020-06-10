package model.users;

import model.Channel;
import model.Data;
import model.programs.Advertisement;
import model.programs.Program;
import model.util.AdCalculator;

import java.util.HashSet;
import java.util.Set;

public class GeneralManager extends UserAccount{
    private static GeneralManager instance = new GeneralManager("admin","admin");
    private Set<Channel> channels;
    private Set<StationManager> managers;

    public static GeneralManager getInstance(){
        return instance;
    }

    private GeneralManager(String username, String password){
        super(username, password);
        channels = new HashSet<>();
        managers = new HashSet<>();
    }

    public Set<StationManager> getManagers() {
        return managers;
    }

    public void addManager(String username, String password){
        if(Data.getInstance().checkUsernameValidity(username)) {
            StationManager newManager = new StationManager(username,password);
            managers.add(newManager);
            Data.getInstance().allUsers.add(newManager);
        } else{
            throw new IllegalArgumentException("Username already exists.");
        }
    }

    public void addChannel(String name, AdCalculator adCalculator){
        Channel newChannel = new Channel(name, adCalculator);
        channels.add(newChannel);
        Data.getInstance().allChannels.add(newChannel);
    }

    public void setManagement(Channel channel, StationManager manager){
        if (channel==null || manager == null){
            throw new NullPointerException("PLease select a channel and program.");
        }
        manager.station = channel;
        channel.manager = manager;
    }

    public void addProgram(Channel channel, Program program){
        if (channel.checkOverlap(program)){
            throw new IllegalArgumentException("Programs cannot overlap.");
        }
        channel.programs.add(program);
    }

    public void removeProgram(Channel channel, Program program){
        if (!channel.programs.remove(program)){
            throw new RuntimeException("Please select a program.");
        }
    }

    public boolean replaceProgram(Channel channel, Program oldProgram, Program newProgram){
        if (!channel.programs.remove(oldProgram)){
            throw new RuntimeException("Program not found.");
        }
        if (channel.checkOverlap(newProgram)){
            channel.programs.add(oldProgram);
            return false;
        }
        channel.programs.add(newProgram);
        return true;
    }

    public Set<Advertisement> getAdRequests(Channel channel){
        return channel.adRequests;
    }

    public void approveAd(Channel channel, Advertisement ad){
        if (!channel.adRequests.contains(ad)){
            throw new RuntimeException("Please select a request.");
        }
        int price = channel.adCalculator.calcPrice(ad,channel.programs);
        if (price>ad.getAdCompany().getBalance()){
            throw new RuntimeException("Insufficient balance.");
        }
        addProgram(channel, ad);
        channel.adRequests.remove(ad);
        ad.getAdCompany().withdraw(price);
    }

    public boolean declineAd(Channel channel, Advertisement ad){
        return channel.adRequests.remove(ad);
    }
}
