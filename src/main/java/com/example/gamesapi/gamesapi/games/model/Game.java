package com.example.gamesapi.gamesapi.games.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game")
@JsonSerialize()
public class Game {

    @Id
    @ApiModelProperty(value = "ID", required = true)
    @Column(name = "ID", nullable = false)
    int id;

    @ApiModelProperty(value = "NAME", required = true)
    @Column(name = "NAME", nullable = false)
    String name;

    @ApiModelProperty(value = "RELEASED", required = true)
    @Column(name = "RELEASED", nullable = false)
    String released;

    @ApiModelProperty(value = "RATING", required = true)
    @Column(name = "RATING", nullable = false)
    String rating;

    @ApiModelProperty(value = "BACKGROUND_IMAGE", required = true)
    @Column(name = "BACKGROUND_IMAGE", nullable = false)
    String backGroundImage;

    @ApiModelProperty(value = "GENERES", required = true)
    @Column(name = "GENERES", nullable = false)
    String generes;

    public String getGeneres() {
        return generes;
    }

    public void setGeneres(String generes) {
        this.generes = generes;
    }


    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", released='" + released + '\'' +
                ", rating='" + rating + '\'' +
                ", backGroundImage='" + backGroundImage + '\'' +
                ", generes=" + generes +
                '}';
    }
}
