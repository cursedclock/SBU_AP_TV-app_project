package model.programs;

import model.util.RepeatingSchedule;

public class News extends Rateable{
    private String host;

    public News(String name, RepeatingSchedule schedule, String host){
        super(name, schedule);
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
