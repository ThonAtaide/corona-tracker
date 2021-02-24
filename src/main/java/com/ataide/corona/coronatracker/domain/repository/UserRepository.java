package com.ataide.corona.coronatracker.domain.repository;

import com.ataide.corona.coronatracker.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "WHERE u.username like (':username')")
    public User findByUsername(String username);

    @Query("SELECT u FROM User u " +
            "WHERE u.username LIKE (':username') AND u.password LIKE (':password')")
    public User findByUsernameAndPassword(String username, String password);
}
