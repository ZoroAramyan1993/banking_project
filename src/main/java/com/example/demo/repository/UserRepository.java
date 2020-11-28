package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user u where u.email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "select * from user where email = :email")
    Boolean existsByEmail(String email);

}
