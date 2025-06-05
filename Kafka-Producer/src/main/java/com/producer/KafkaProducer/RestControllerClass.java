package com.producer.KafkaProducer;

import com.producer.KafkaProducer.service.EmployeeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerClass {

    @Autowired
    private EmployeeProducer employeeProducer;

    @GetMapping
    public void test(){
        employeeProducer.publishBatch();
    }
}
