package model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RepeatingSchedule extends Schedule{

    public RepeatingSchedule(int start, int duration){
        super(new Date(0), start, duration);
        date = null;
    }

    public RepeatingSchedule(String start, String end){
        this(parseTime(start), parseTime(end));
    }

    @Override
    public int compareTo(Schedule schedule){
        if (end<schedule.start) return -2;
        if (end==schedule.start) return -1;
        if (start==schedule.end) return 1;
        if (start>schedule.end) return 2;
        return 0;
    }
}
