package com.restlearning;

import com.restlearning.model.Employee;
import com.restlearning.model.Student;
import com.restlearning.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public ResponseEntity<?> root(){
        return ResponseEntity.status(HttpStatus.OK).body("Application is up");
    }

    @GetMapping("/api/students/{id}")
    public Student consumingRestAPI(@PathVariable int id){
        String param = String.valueOf(id);
        String url = "http://localhost:8989/students/" + id;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.GET, entity, Student.class);
        System.out.println(response);
        return response.getBody();
    }

    @GetMapping("/api/students")
    public List<Student> getAllStudents(){
        String url = "http://localhost:8989/students";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Student>> originalResponse = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {});
//        Test test = new Test();
//        test.setStudents(originalResponse.getBody());
        List<Student> fetchallstudents = new ArrayList<>();
        fetchallstudents = originalResponse.getBody();
        return fetchallstudents;
    }
}
