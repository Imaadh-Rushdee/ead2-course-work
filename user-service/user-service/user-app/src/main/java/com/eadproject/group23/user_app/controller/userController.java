package com.eadproject.group23.user_app.controller;


import com.eadproject.group23.user_app.data.userdata;
import com.eadproject.group23.user_app.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private userService obj;

    @GetMapping
    public List<userdata> getAllUsers() {
        return obj.getAllUsers();
    }

    @GetMapping("/names")
    public List<userdata> getUserByName(@RequestParam String name){
        return obj.getUserByName(name);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody userdata user) {
        obj.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }


    @DeleteMapping(path = "/{id}")
    public  String deleteBYID(@PathVariable int id) {
        boolean deleted = obj.deleteUser(id);
        return deleted ? "User deleted successfully." : "User not found.";
    }

    @GetMapping("/students/grades")
    public List<Integer> getStudentsByGrade(@RequestParam String grade){
        return obj.getStudentByGrade(grade).stream().map(userdata::getId).toList();
    }

    @PostMapping("/login")
    public Object validateLogin(@RequestBody Map<String, String> loginData) {
        String name = loginData.get("name").trim();
        String password = loginData.get("password").trim();

        if (name == null || password == null) {
            return "Username and password required.";
        }

        userdata user = obj.validateUser(name, password); // returns userdata or null

        if (user == null) {
            return "Invalid username or password!";
        }

        user.setPassword(null); // hide password before returning
        return user; // return user info as JSON
    }

    @GetMapping("/grades")
    public List<String> getAllGrades() {
        return obj.getAllGrades();
    }

}


