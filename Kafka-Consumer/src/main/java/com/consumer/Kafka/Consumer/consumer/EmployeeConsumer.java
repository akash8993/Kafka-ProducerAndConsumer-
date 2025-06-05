package com.consumer.Kafka.Consumer.consumer;

import com.consumer.Kafka.Consumer.entity.Employee;
import com.consumer.Kafka.Consumer.repository.EmployeeRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeConsumer {
    private final EmployeeRepository employeeRepository;

    public EmployeeConsumer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "employee-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void consumeBatch(List<Employee> employees) {
        System.out.println("Consumed batch:");
        employees.forEach(emp -> System.out.println(emp.getId() + " - " + emp.getName()));

        employeeRepository.saveAll(employees); // Save again to update processed status
    }
}

