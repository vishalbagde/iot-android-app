package com.example.watermonitoringiot;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class QualityMstr {
    int id;
    int user_id;
    int tds;
    Date date;
    Time time;
    int setting_fetch_status;
    Timestamp entry_date;
    String status;

    public QualityMstr() {
    }

    public QualityMstr(int id, int user_id, int tds, Date date, Time time, int setting_fetch_status, Timestamp entry_date, String status) {
        this.id = id;
        this.user_id = user_id;
        this.tds = tds;
        this.date = date;
        this.time = time;
        this.setting_fetch_status = setting_fetch_status;
        this.entry_date = entry_date;
        this.status = status;
    }

    public QualityMstr(int id, int user_id, int tds, Date date, Time time) {
        this.id = id;
        this.user_id = user_id;
        this.tds = tds;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }


    public int getUser_id() {
        return user_id;
    }

    public int getTds() {
        return tds;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getSetting_fetch_status() {
        return setting_fetch_status;
    }

    public Timestamp getEntry_date() {
        return entry_date;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTds(int tds) {
        this.tds = tds;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setSetting_fetch_status(int setting_fetch_status) {
        this.setting_fetch_status = setting_fetch_status;
    }

    public void setEntry_date(Timestamp entry_date) {
        this.entry_date = entry_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
