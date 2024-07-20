package com.kerackdiaz.User_Service.repositories;

import com.kerackdiaz.User_Service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
