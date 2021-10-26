package com.team1.controllers;

import com.team1.models.Users;
import com.team1.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService; // this is something we want to inject

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }


    //CREATE - addUser. This path will help to add a new user
    @PostMapping(path ="/addUser", consumes = "application/json", produces = "application/json")
    public void addUser(@RequestBody Users user){
        userService.addUser(user);
    }

//    // Logic to Login a user
//    @PostMapping(path = "/login")
//    public boolean loginUser(@RequestBody Users users) {
//        // This method will be used to return a boolean value for the login of a user
//        List<Users> allUsers = userService.getAllUsers();
//
//        for (int i = 0; i < allUsers.size(); i++){
//            if (Objects.equals(users.getUsername(), allUsers.get(i).getUsername()) &&
//                    Objects.equals(users.getPassword(), allUsers.get(i).getPassword())  ) {
//
//                return true;
//            }
//        }
//
//        return false;
//    }

    // Logic to Login a user
    @PostMapping(path = "/login")
    public Users loginUser(@RequestBody Users users) {
        // This method will be used to return a boolean value for the login of a user
        List<Users> allUsers = userService.getAllUsers();

        for (int i = 0; i < allUsers.size(); i++){
            if (Objects.equals(users.getUsername(), allUsers.get(i).getUsername()) &&
                    Objects.equals(users.getPassword(), allUsers.get(i).getPassword())  ) {

                return userService.getUser(allUsers.get(i).getId());
            }
        }

        return null;
    }



    //READ - getAllUsers, getUser. This path will help to find all users or a specific user
    @GetMapping(path ="/find")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path ="/find/{user_id}")
    public Users getUser(@PathVariable Integer user_id){
        return userService.getUser(user_id);
    }


    @GetMapping(path = "/findUsername/{username}")
    public Users getByUsername(@PathVariable String username){
        // returns a user if correct username found. Will return null if the username does not exist.
        List<Users> allUsers = userService.getAllUsers();
        Users u = new Users();

        for (int i = 0; i < allUsers.size(); i++){
            if (Objects.equals(username, allUsers.get(i).getUsername())) {

                u = userService.getUser(allUsers.get(i).getId());
                return u;
            }
        }
        return null;

    }




    //UPDATE - updateUser. This path will help update a user's information.
    @PutMapping(path ="/update/{user_id}")//, consumes = "application/json", produces = "application/json")
    public void updateUser (@PathVariable Integer user_id, @RequestBody Users user){
//        Users u = userService.getUser(user_id);

        if (Objects.equals(user_id, user.getId())){ // compare the 2 user IDs
            userService.updateUser(user);
        }

    }



    //DELETE - deleteUser. This path will help to delete a user.
    @DeleteMapping(path ="/delete/{user_id}")
    public void deleteUser (@PathVariable Integer user_id){
        Users u = userService.getUser(user_id);
        userService.deleteUser(u);
    }

}
