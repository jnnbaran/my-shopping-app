package com.shoppingapp.demo.profile;

import com.shoppingapp.demo.auth.db.UserPrincipal;
import com.shoppingapp.demo.auth.jwt.Credentials;
import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.exceptions.EmailAlreadyTakenException;
import com.shoppingapp.demo.shared.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            throw new InsufficientAuthenticationException("Couldn't not retrieve currently logged in user");
        }
        return getUser(((UserPrincipal) principal).getId());
    }

    public void addUser(Credentials credentials) {
        if (userRepository.findOneByEmail(credentials.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException();
        }
        User userToAdd = modelMapper.map(credentials, User.class);
        userRepository.save(userToAdd);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User of id: %s not found", id)));
    }
}
