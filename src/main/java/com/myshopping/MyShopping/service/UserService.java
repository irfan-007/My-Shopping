package com.myshopping.MyShopping.service;

import com.myshopping.MyShopping.models.AppUsers;
import com.myshopping.MyShopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // to save user we need user repository layer
    @Autowired
    UserRepository userRepository;

    public void registerUser(AppUsers appUsers){
        userRepository.save(appUsers); // jpa provides save() to save in database
    }
}
