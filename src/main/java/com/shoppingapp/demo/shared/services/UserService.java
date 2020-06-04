package com.shoppingapp.demo.shared.services;

import com.shoppingapp.demo.home.Credentials;
import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.exceptions.EmailAlreadyTakenException;
import com.shoppingapp.demo.shared.repos.UserRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void addUser(Credentials credentials) {
        if(userRepository.findOneByEmail(credentials.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException();
        }

        User userToAdd = modelMapper.map(credentials, User.class);
        userRepository.save(userToAdd);
    }


    public boolean validateUser(Credentials credentials) {
        return Optional.ofNullable(credentials.getEmail())
                .map(userRepository::findByEmail)
                .map(user -> user.getPassword().equals(credentials.getPassword()))
                .orElse(false);
    }
}
