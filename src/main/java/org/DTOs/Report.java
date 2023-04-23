package org.DTOs;

public class Report {
    private String playerID;
    private String scoutID;

    private int season;
    private String positives;
    private String negatives;

    public Report(String playerID, String scoutID, int season, String positives, String negatives) {
        this.playerID = playerID;
        this.scoutID = scoutID;
        this.season = season;
        this.positives = positives;
        this.negatives = negatives;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getScoutID() {
        return scoutID;
    }

    public void setScoutID(String scoutID) {
        this.scoutID = scoutID;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getPositives() {
        return positives;
    }

    public void setPositives(String positives) {
        this.positives = positives;
    }

    public String getNegatives() {
        return negatives;
    }

    public void setNegatives(String negatives) {
        this.negatives = negatives;
    }
}
