package com.example.vuongphusanhhoangthien.babyvaccine;

public class Alarm {
    private int alarm_id;
    private String alarm_name;
    private String alarm_time;


    public Alarm(){

    }
    public Alarm(int alarm_id, String alarm_name, String alarm_time) {
        this.alarm_id = alarm_id;
        this.alarm_name = alarm_name;
        this.alarm_time = alarm_time;
    }

    public Alarm(String alarm_name, String alarm_time) {
        this.alarm_name = alarm_name;
        this.alarm_time = alarm_time;
    }

    public int getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(int alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlarm_name() {
        return alarm_name;
    }

    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }
}
