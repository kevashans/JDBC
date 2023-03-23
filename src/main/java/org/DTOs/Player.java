package org.DTOs;

import java.sql.Date;

public class Player {
    private String id;
    private String player_name;
    private Date DOB;

    private String position;

    private int player_draft_year;

    public Player(String id, String player_name, Date DOB, String position, int player_draft_year) {
        this.id = id;
        this.player_name = player_name;
        this.DOB = DOB;
        this.position = position;
        this.player_draft_year = player_draft_year;
    }
    public Player(){};

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
