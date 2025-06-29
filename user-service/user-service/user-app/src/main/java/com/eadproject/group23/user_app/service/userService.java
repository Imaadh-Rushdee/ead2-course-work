package com.eadproject.group23.user_app.Service;

import com.eadproject.group23.user_app.Data.UserRepository;
import com.eadproject.group23.user_app.Data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<UserData> getAllUsers() {
        return userRepo.findAll();
    }

    public UserData getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public UserData createUser(UserData user) {
        return userRepo.save(user);
    }

    public UserData updateUser(UserData user) {
        return userRepo.save(user);
    }

    public List<UserData> getUserByName(String name) {
        return userRepo.findByName(name);
    }

    public boolean deleteUser(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public UserData validateUser(String name, String password) {
        return userRepo.findByNameAndPassword(name, password).orElseThrow(() -> new RuntimeException("Invalid User data"));
    }

    public List<UserData> getStudentByGrade(String grade){
        return userRepo.findByGrade(grade);
    }

    public List<String> getAllGrades() {
        return userRepo.findDistinctGrades();
    }
}
