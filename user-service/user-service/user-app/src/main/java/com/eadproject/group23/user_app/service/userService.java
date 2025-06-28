package com.eadproject.group23.user_app.service;


import com.eadproject.group23.user_app.data.UserRepository;
import com.eadproject.group23.user_app.data.userdata;
import com.eadproject.group23.user_app.dto.FeeDto;
import com.eadproject.group23.user_app.dto.StudentFeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FeeServiceClient feeServiceClient;

    public List<userdata> getAllUsers() {
        return userRepo.findAll();
    }

    public List<userdata> getAlluser() {
        return userRepo.findAll();

    }

    public List<userdata> getStudentByGrade(String grade){
        return userRepo.findByGrade(grade);
    }
    public userdata getUserBYID(int id) {
        Optional<userdata> user = userRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public userdata createUser(userdata user) {
        // 1. Save user first to get ID
        userdata savedUser = userRepo.save(user);

        if ("student".equalsIgnoreCase(savedUser.getRole())) {
            List<FeeDto> dto = feeServiceClient.getFeeByGrade(user.getGrade());
            for(FeeDto feeDto : dto){
                StudentFeeDto studentFeeDto = new StudentFeeDto(savedUser.getId(), feeDto.getFeeName(), feeDto.getFeeAmount(), "DUE", LocalDate.now());
                feeServiceClient.enterStudentFee(studentFeeDto);
            }
        }

        return savedUser;
    }

    public userdata updateUser(userdata user) {

        return userRepo.save(user);
    }

    public List<userdata> getUserByName(String name) {
        return userRepo.findByName(name);
    }

    public boolean deleteUser(int id) {
        Optional<userdata> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            return true;

        }
        return false;
    }
    public userdata validateUser(String name, String password) {
        return userRepo.findByNameAndPassword(name, password).orElseThrow(() -> new RuntimeException("Invalid User data"));
    }

    public List<String> getAllGrades() {
        return userRepo.findDistinctGrades();
    }

}




