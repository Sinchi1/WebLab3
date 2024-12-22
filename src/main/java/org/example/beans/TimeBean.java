package org.example.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean(name = "bean")
@ApplicationScoped
public class TimeBean {

    private String currentTime;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void init() {
        updateTime();
    }

    public void updateCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        this.currentTime = sdf.format(new Date());
    }

    public String updateTime() {
        updateCurrentTime();
        return currentTime;
    }
}
