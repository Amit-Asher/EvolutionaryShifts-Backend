package com.evo.springboot.db.Repositories;

import com.evo.springboot.db.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserEmailAndAndUserPassword(String userEmail, String password);
    User findUserByUserEmail(String userEmail);
    void deleteUserByUserEmail(String userEmail);
    boolean existsUserByUserEmail(String userEmail);
}
