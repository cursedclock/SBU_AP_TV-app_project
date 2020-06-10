package model.programs;

import model.util.Schedule;

public abstract class Program {
    private String name;
    protected Schedule schedule;

    public Program(String name, Schedule schedule){
        this.name = name;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    // 0:overlapping    1:next to each other   2:separate
    public int checkCollision(Program program){
        return Math.abs(schedule.compareTo(program.schedule));
    }

    public int checkCollision(Rerunable program){
        return program.checkCollision(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
