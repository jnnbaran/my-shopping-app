package com.shoppingapp.demo.shared.services;

import com.shoppingapp.demo.home.Credentials;
import com.shoppingapp.demo.shared.entities.User;
import com.shoppingapp.demo.shared.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void addUser(Credentials credentials) {
        User userToAdd = modelMapper.map(credentials, User.class);
        userRepository.save(userToAdd);
    }
}
