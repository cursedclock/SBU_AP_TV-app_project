package model.users;

import model.Channel;
import model.programs.Rateable;

public class Viewer {
    public Channel watching;

    public Viewer(Channel watching){
        this.watching = watching;
    }

    public void rate(Rateable program, int rating){
        program.rate(rating);
    }
}
