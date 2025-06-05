package com.producer.KafkaProducer.repository;

import com.producer.KafkaProducer.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    @Query(value = "SELECT * from employees LIMIT 10", nativeQuery = true)
    List<Employees> findTop10ById();
}

