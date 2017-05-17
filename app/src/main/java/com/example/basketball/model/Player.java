package com.example.basketball.model;

import java.util.Date;

/**
 * Created by Alfredo on 28/02/2016.
 */
public class Player {
    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private Integer numBaskets;
    private Integer numAssists;
    private Integer numRebounds;
    private Position position;
    private Team team;

    public Player() {
    }

    public Player(Long id, String name, String surname, Date birthdate, Integer numBaskets, Integer numAssists, Integer numRebounds, Position position, Team team) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.numBaskets = numBaskets;
        this.numAssists = numAssists;
        this.numRebounds = numRebounds;
        this.position = position;
        this.team = team;
    }

    public Player(String name, String surname,Integer numBaskets, Integer numAssists, Integer numRebounds, Position position, Team team) {
        this.name = name;
        this.surname = surname;
        this.numBaskets = numBaskets;
        this.numAssists = numAssists;
        this.numRebounds = numRebounds;
        this.position = position;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getNumBaskets() {
        return numBaskets;
    }

    public void setNumBaskets(Integer numBaskets) {
        this.numBaskets = numBaskets;
    }

    public Integer getNumAssists() {
        return numAssists;
    }

    public void setNumAssists(Integer numAssists) {
        this.numAssists = numAssists;
    }

    public Integer getNumRebounds() {
        return numRebounds;
    }

    public void setNumRebounds(Integer numRebounds) {
        this.numRebounds = numRebounds;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", numBaskets=" + numBaskets +
                ", numAssists=" + numAssists +
                ", numRebounds=" + numRebounds +
                ", position=" + position +
                ", team=" + team +
                '}';
    }
}
