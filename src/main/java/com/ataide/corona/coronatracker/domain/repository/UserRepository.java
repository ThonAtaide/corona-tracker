package com.ataide.corona.coronatracker.domain.repository;

import com.ataide.corona.coronatracker.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "WHERE u.username like (:username)")
    Optional<User> findByUsernameLike(String username);

    @Query("SELECT u FROM User u " +
            "WHERE u.username LIKE (':username') AND u.password LIKE (':password')")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
