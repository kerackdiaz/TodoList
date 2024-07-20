package com.kerackdiaz.User_Service.dtos;

import com.kerackdiaz.User_Service.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private final long id;
    private final String FirstName;
    private final String LastName;
    private final String Email;
    private final List<Long> tasks;

    public UserDTO(User user) {
        this.id = user.getId();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.Email = user.getEmail();
        this.tasks = user.getTasksId();

    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public List<Long> getTasks() {
        return tasks;
    }
}

