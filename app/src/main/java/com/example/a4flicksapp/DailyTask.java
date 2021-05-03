package com.example.a4flicksapp;

public class DailyTask {
    //this class is used for both keeping data when that it is to be inserted to DB and to store the retrieved data temporarily as well
    // (simply a model class which keeps data in an intermidiate class)
    private int id;
    private String time, description;
    private long started, finished;

    //set values to the above variables using constructor or getters, setters
    public DailyTask() {}

    public DailyTask(int id, String time, String description, long started, long finished) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }

    public DailyTask(String time, String description, long started, long finished) {
        this.time = time;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
