package com.shoppingapp.demo.shared.repos;

import com.shoppingapp.demo.shared.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findOneByEmail(String email);
}
