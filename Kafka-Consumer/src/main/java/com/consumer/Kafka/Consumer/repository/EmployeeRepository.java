package com.consumer.Kafka.Consumer.repository;

import com.consumer.Kafka.Consumer.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

