package com.example.gamesapi.gamesapi.games.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GamesCriteria {

    public GamesCriteria() {
    }

    public GamesCriteria(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String key;
    String value;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GamesCriteria{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
