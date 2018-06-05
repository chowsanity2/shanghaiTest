package com.chow.model;

import java.util.Date;

public class Achievements {
    private Integer id;

    private String year;

    private String score;

    private String achievementsTarget;

    private String current;

    private Double weight;

    private String futureScore;

    private String finishedRate;

    private Date createdTime;

    private Date updateTime;

    public Achievements(Integer id, String year, String score, String achievementsTarget, String current, Double weight, String futureScore, String finishedRate, Date createdTime, Date updateTime) {
        this.id = id;
        this.year = year;
        this.score = score;
        this.achievementsTarget = achievementsTarget;
        this.current = current;
        this.weight = weight;
        this.futureScore = futureScore;
        this.finishedRate = finishedRate;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    public Achievements() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getAchievementsTarget() {
        return achievementsTarget;
    }

    public void setAchievementsTarget(String achievementsTarget) {
        this.achievementsTarget = achievementsTarget == null ? null : achievementsTarget.trim();
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current == null ? null : current.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFutureScore() {
        return futureScore;
    }

    public void setFutureScore(String futureScore) {
        this.futureScore = futureScore == null ? null : futureScore.trim();
    }

    public String getFinishedRate() {
        return finishedRate;
    }

    public void setFinishedRate(String finishedRate) {
        this.finishedRate = finishedRate == null ? null : finishedRate.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}