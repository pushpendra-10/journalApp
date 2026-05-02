package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServices;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUser(){
        return userServices.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user, Authentication authentication){
        String userName = authentication.getName();
        User userInDb = userServices.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServices.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(Authentication authentication){
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
