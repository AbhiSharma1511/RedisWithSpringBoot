package com.company.rediswithspringboot.controller;


import com.company.rediswithspringboot.dao.UserDao;
import com.company.rediswithspringboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping
    public User saveUser(@RequestBody User user){

        user.setUserId(UUID.randomUUID().toString());

        return userDao.saveUser(user);

    }

    @GetMapping
    public List<User> getAllUser(){
        Map<Object, Object> all = userDao.findAll();

        Collection<Object> values = all.values();

        return values.stream().map(value -> (User) value).collect(Collectors.toList());

    }

    @DeleteMapping
    public void deleteUser(String userId){
        userDao.delete(userId);
    }

}
