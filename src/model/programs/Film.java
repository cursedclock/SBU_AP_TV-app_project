package model.programs;

import model.util.Schedule;

public class Film extends Rerunable {
    public Film(String name, Schedule schedule, Schedule rerunSchedule){
        super(name, schedule, rerunSchedule);
    }
}
