package com.example.gamesapi.gamesapi.users.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonSerialize()
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", required = true)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "EMAIL", nullable = false,  unique = true, length = 45)
    @ApiModelProperty(value = "EMAIL", required = true)
    private String email;

    @Column(name = "PASSWORD",nullable = false, length = 64)
    @ApiModelProperty(value = "PASSWORD", required = true)
    private String password;

    @Column(name = "ROLE", nullable = false)
    @ApiModelProperty(value = "ROLE", required = true)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
