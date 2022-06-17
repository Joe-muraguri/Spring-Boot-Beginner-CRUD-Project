package com.joe.CRUD.Controller;

import com.joe.CRUD.Model.User;
import com.joe.CRUD.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return  userRepo.findAll();
    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "Saved...";
    }

    public String updateUser(@PathVariable long id,@RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        userRepo.save(updatedUser);
        return "Updated...";
    }

    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with id: "+id;
    }
}
