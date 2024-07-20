package com.kerackdiaz.User_Service.dtos;

import com.kerackdiaz.User_Service.models.User;

import java.util.List;

public class RegisteredDTO {
        private final String id;
        private final String FirstName;
        private final String LastName;
        private final String Email;



        public RegisteredDTO(User user) {
            this.id = String.valueOf(user.getId());
            this.FirstName = user.getFirstName();
            this.LastName = user.getLastName();
            this.Email = user.getEmail();

        }

        public String getId() {
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

    }