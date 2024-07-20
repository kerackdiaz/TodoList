package com.kerackdiaz.AuthServer.dtos;

import java.util.List;

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Long> tasks;

    // Constructors, Getters, and Setters
    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, String email, List<Long> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getTasks() {
        return tasks;
    }

    public void setTasks(List<Long> tasks) {
        this.tasks = tasks;
    }
}