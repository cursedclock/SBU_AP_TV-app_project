package model.programs;

import model.util.Schedule;

public abstract class Rerunable extends Rateable{
    private Schedule rerunSchedule;

    public Rerunable(String name, Schedule schedule, Schedule rerunSchedule){
        super(name, schedule);
        this.rerunSchedule = rerunSchedule;
    }

    public int checkCollision(Program program){
        int comparison1 = schedule.compareTo(program.schedule);
        int comparison2 = rerunSchedule.compareTo(program.schedule);
        int combination = combineComparisons(comparison1,comparison2);

        if (program instanceof Rerunable){
            Rerunable rerunableProgram = (Rerunable) program;
            int comparison3 = schedule.compareTo(rerunableProgram.rerunSchedule);
            int comparison4 = rerunSchedule.compareTo(rerunableProgram.rerunSchedule);
            combination = combineComparisons(combination,combineComparisons(comparison3,comparison4));
        }
        return combination;

    }

    private int combineComparisons(int c1, int c2){
        if (c1*c2==0) return 0;
        if (Math.abs(c1)==1 || Math.abs(c2)==1) return 1;
        return 2;
    }
}
