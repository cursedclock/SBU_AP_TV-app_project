package model.util;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule implements Comparable<Schedule>{
    protected Date date;
    protected int start;
    protected int end;

    public Schedule(Date date, int start, int end){
        if (date==null){
            throw new NullPointerException();
        }
        if (start>=end){
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Schedule(Date date, String start, String end){
        this(date, parseTime(start), parseTime(end));
    }
    public Schedule(String date, String start, String end){
        this(parseDate(date), parseTime(start), parseTime(end));
    }

    public boolean hasCollision(Schedule schedule){
        if (schedule.date!=null && date.compareTo(schedule.date) != 0){
            return false;
        }
        return (start>=schedule.start && start<=schedule.end)||
                (start<=schedule.start && end>=schedule.end);
    }

    @Override
    public int compareTo(Schedule schedule){
        int dateComparison = 0;
        if (schedule.date!=null) {
            dateComparison = date.compareTo(schedule.date);
            if (dateComparison!=0) return  dateComparison*2;
        }
        if (end<schedule.start) return -2;
        if (end==schedule.start) return -1;
        if (start==schedule.end) return 1;
        if (start>schedule.end) return 2;
        return 0;
    }

    protected static int parseTime(String time){
        if (!(time.matches("\\d{1,2}:\\d{1,2}"))){
            throw new IllegalArgumentException("Invalid time input.");
        }
        String[] units = time.split(":");
        int h = Integer.parseInt(units[0]);
        int m = Integer.parseInt(units[1]);
        if (h>24||(h==24&&m>0)||h<0||m>60||m<0){
            throw new IllegalArgumentException("Invalid time input.");
        }
        return h*60 + m;
    }

    protected static Date parseDate(String date){
        try {
            return (new SimpleDateFormat(date)).parse(date);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid date input.");
        }
    }

    public int duration(){
        return  end-start;
    }
}

