package model.util;

import model.programs.Advertisement;
import model.programs.Program;
import java.util.Set;

public interface AdCalculator {
    public int calcPrice(Advertisement ad, Set<Program> programs);
}
