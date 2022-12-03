package com.example.gamesapi.gamesapi.developers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "developers")
public class Developers {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;


    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "GAMES_COUNT", nullable = false)
    private int gamesCount;

    @Column(name = "BACKGROUND_IMAGE", nullable = false)
    String backGroundImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(int gamesCount) {
        this.gamesCount = gamesCount;
    }

    public String getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(String backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Developers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gamesCount=" + gamesCount +
                ", backGroundImage='" + backGroundImage + '\'' +
                '}';
    }
}
