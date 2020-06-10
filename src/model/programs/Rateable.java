package model.programs;

import model.util.Schedule;

public abstract class Rateable extends Program {
    double rating;
    int ratingCount;

    public Rateable(String name, Schedule schedule){
        super(name, schedule);
        rating = 0.0d;
        ratingCount = 0;
    }

    public double getRating() {
        return rating;
    }

    public void rate(int rating){
        verify(rating);
        this.rating = (this.rating*ratingCount  + rating) / (double)(++ratingCount);
    }

    private void verify(int rating){
        if (rating>100 || rating<0){
            throw new IllegalArgumentException("Rating must be between 0 and 100.");
        }
    }
}
