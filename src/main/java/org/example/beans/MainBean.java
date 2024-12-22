package org.example.beans;

import jakarta.persistence.*;
import org.example.model.Point;
import org.example.model.Result;
import org.example.util.AreaChecker;
import org.example.util.Manager.ResultManager;

import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "bean")
@ApplicationScoped
public class MainBean {

    private double x=0.0;

    private double y = 0.0;

    private double r = 1.0;

    private boolean hit;

    private String timenow;

    public String getTimenow() {
        return timenow;
    }

    public void setTimenow(String timenow) {
        this.timenow = timenow;
    }

    private double graphX;
    private double graphY;

    private String error = "";
    private List<Result> results = new ArrayList<>();

    public MainBean() {
    }
    ResultManager resultManager = new ResultManager();
    AreaChecker areaChecker = new AreaChecker();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void init() {
        results = resultManager.getResultList();
    }

    public String process() {
            error = "";
            hit = areaChecker.isInside(x,y,r);
            Result result = new Result(x, y, r, hit, LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            addResult(result);
            resultManager.addResult(result);
            return "200";

    }
    public String submitFromGraph(){
            error = "";
            hit = areaChecker.isInside(graphX,graphY,r);
            Result result = new Result(graphX, graphY, r, hit, LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            addResult(result);
            return "200";
    }


    public void addResult(Result result) {
        results.add(result);
    }
    public List<Point> getPointsForGraph() {
        List<Point> points = new ArrayList<>();
        for (Result item : results) {
            points.add(new Point(item.getX(), item.getY(), item.getR(), item.isHit()));
        }
        return points;
    }

    private boolean checkHit() {
        return areaChecker.isInside(x,y,r);
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public double getGraphX() {
        return graphX;
    }

    public double getGraphY() {
        return graphY;
    }

    public void setGraphX(double graphX) {
        this.graphX = graphX;
    }

    public void setGraphY(double graphY) {
        this.graphY = graphY;
    }
}
