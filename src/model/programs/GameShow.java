package model.programs;

import model.util.Schedule;

public class GameShow extends Rerunable{
    private String host;

    public GameShow(String name, Schedule schedule, Schedule rerunSchedule, String host){
        super(name, schedule, rerunSchedule);
        this.host = host;
    }
}
