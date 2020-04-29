package com.shoppingapp.demo.shared.repos;

import com.shoppingapp.demo.shared.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
