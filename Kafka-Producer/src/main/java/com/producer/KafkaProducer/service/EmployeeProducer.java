package com.producer.KafkaProducer.service;

import com.producer.KafkaProducer.entity.Employees;
import com.producer.KafkaProducer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class EmployeeProducer {
    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, List<Employees>> kafkaTemplate;
    private final EmployeeRepository employeeRepository;

    private final AtomicBoolean isPublishing = new AtomicBoolean(false);

    public EmployeeProducer(KafkaTemplate<String, List<Employees>> kafkaTemplate, EmployeeRepository repo) {
        this.kafkaTemplate = kafkaTemplate;
        this.employeeRepository = repo;
    }

    public void publishBatch() {
        if (isPublishing.get()) return;

        List<Employees> employees = employeeRepository.findTop10ById();
        if (!employees.isEmpty()) {
            isPublishing.set(true);
            CompletableFuture<SendResult<String, List<Employees>>> future = kafkaTemplate.send(topic, employees);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
//                    employees.forEach(e -> e.setProcessed(true));
//                    employeeRepository.saveAll(employees);
                    isPublishing.set(false);
                } else {
                    ex.printStackTrace();
                    isPublishing.set(false);
                }
            });
        }
    }
}

