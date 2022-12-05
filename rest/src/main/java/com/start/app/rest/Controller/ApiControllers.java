package com.start.app.rest.Controller;

import com.start.app.rest.Models.User;
import com.start.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getpage() {
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();   // return all user form database.
    }

    @PostMapping(value = "/save")     // create the user in postman and hit the db(UserRepo)
    public String save(@RequestBody User user) {
        userRepo.save(user);
        return "saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {

        User updatedUser = userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());
        userRepo.save(updatedUser);
        return "updateUser....";
    }
    @DeleteMapping(value = "/delete/{id}")
     public String deleteUser(@PathVariable int id){
       User deleteUser= userRepo.findById(id).get();
       userRepo.delete(deleteUser);
       return "Delete user with the id:"+id;
     }

}

