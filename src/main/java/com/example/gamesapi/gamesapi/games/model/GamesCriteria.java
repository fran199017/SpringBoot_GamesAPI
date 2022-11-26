package com.example.gamesapi.gamesapi.games.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GamesCriteria {

    String key;
    String value;
    String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

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
                ", operator='" + operator + '\'' +
                '}';
    }
}
