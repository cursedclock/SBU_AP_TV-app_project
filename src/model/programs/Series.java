package model.programs;

import model.util.RepeatingSchedule;
import model.util.Schedule;

public class Series extends Rerunable{
    public Series(String name, RepeatingSchedule schedule, RepeatingSchedule rerunSchedule){
        super(name, schedule, rerunSchedule);
    }
}
