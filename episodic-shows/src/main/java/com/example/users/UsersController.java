package com.example.users;

import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer20 on 5/17/17.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }
}
