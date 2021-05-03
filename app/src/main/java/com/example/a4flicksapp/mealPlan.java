package com.example.a4flicksapp;

public class mealPlan {
    private int id1;
    private String day1, breakfirst1, lunch1,dinner1;
    private long started, finished;
    public mealPlan(){}
    public mealPlan(int id1, String day1, String breakfirst1, String lunch1, String dinner1, long started, long finished) {
        this.id1 = id1;
        this.day1 = day1;
        this.breakfirst1 = breakfirst1;
        this.lunch1 = lunch1;
        this.dinner1 = dinner1;
        this.started = started;
        this.finished = finished;
    }

    public mealPlan(String day1, String breakfirst1, String lunch1, String dinner1, long started, long finished) {
        this.day1 = day1;
        this.breakfirst1 = breakfirst1;
        this.lunch1 = lunch1;
        this.dinner1 = dinner1;
        this.started = started;
        this.finished = finished;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getBreakfirst1() {
        return breakfirst1;
    }

    public void setBreakfirst1(String breakfirst1) {
        this.breakfirst1 = breakfirst1;
    }

    public String getLunch1() {
        return lunch1;
    }

    public void setLunch1(String lunch1) {
        this.lunch1 = lunch1;
    }

    public String getDinner1() {
        return dinner1;
    }

    public void setDinner1(String dinner1) {
        this.dinner1 = dinner1;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
