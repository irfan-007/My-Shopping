package com.myshopping.MyShopping.controller;

import com.myshopping.MyShopping.models.AppUsers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")  // all request start with api will come here
public class CommonController {
    @PostMapping("/user/register")
    public String registerUser(@RequestBody AppUsers appUsers){
        // save user - logic built in service layer
        return "";
    }
}
