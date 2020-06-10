package model.programs;

import model.users.AdvertisementCompany;
import model.util.Schedule;

public class Advertisement extends Program{
    private AdvertisementCompany adCompany;

    public Advertisement(String name, Schedule schedule, AdvertisementCompany adCompany){
        super(name, schedule);
        this.adCompany = adCompany;
    }

    public AdvertisementCompany getAdCompany() {
        return adCompany;
    }

    public int getDuration(){
        return schedule.duration();
    }
}
