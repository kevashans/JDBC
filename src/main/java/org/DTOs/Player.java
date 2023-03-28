package org.DTOs;

import java.sql.Date;

public class Player {
    private String id;
    private String player_name;
    private Date DOB;

    private String position;

    private int player_draft_year;

    private static int idCount;
    public Player(String id, String player_name, Date DOB, String position, int player_draft_year) {
        this.id = id;
        this.player_name = player_name;
        this.DOB = DOB;
        this.position = position;
        this.player_draft_year = player_draft_year;
    }
    public Player(String player_name, Date DOB, String position, int player_draft_year) {
        idCount++;
        this.id = "PL"+idCount;
        System.out.println(idCount);
        this.player_name = player_name;
        this.DOB = DOB;
        this.position = position;
        this.player_draft_year = player_draft_year;
    }
    public Player(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getDOB() {
        return DOB.toString();
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPlayer_draft_year() {
        return player_draft_year;
    }

    public void setPlayer_draft_year(int player_draft_year) {
        this.player_draft_year = player_draft_year;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", player_name='" + player_name + '\'' +
                ", DOB=" + DOB +
                ", position='" + position + '\'' +
                ", player_draft_year=" + player_draft_year +
                '}';
    }
}
