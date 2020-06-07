package com.shoppingapp.demo.auth.db;

import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class H2UserDetailsService implements UserDetailsService {

    private UserRepository repository;

    H2UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword());
    }

    public UserDetails loadUserById(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User of id: " + userId + " not found."));

        return new UserPrincipal(userId, user.getEmail(), user.getPassword());
    }
}
