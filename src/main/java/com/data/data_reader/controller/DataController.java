package com.data.data_reader.controller;

import com.data.data_reader.entity.Employee;
import com.data.data_reader.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public List<Employee> getData(){
        return dataService.getAllEmployee();
    }

}
