package com.eadproject.group23.user_app.service;

import com.eadproject.group23.user_app.dto.FeeDto;
import com.eadproject.group23.user_app.dto.StudentFeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FeeServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<FeeDto> getFeeByGrade(String grade){
        String url = "http://localhost:8081/school-system/fees/grades?grade=" +grade;
        FeeDto[] fees = restTemplate.getForObject(url, FeeDto[].class);
        return fees == null ? List.of() : Arrays.asList(fees);
    }

    public StudentFeeDto enterStudentFee(StudentFeeDto studentFeeDto){
        String url = "http://localhost:8081/school-system/student-fees";
        StudentFeeDto response = restTemplate.postForObject(url, studentFeeDto, StudentFeeDto.class);
        return response;
    }


}
