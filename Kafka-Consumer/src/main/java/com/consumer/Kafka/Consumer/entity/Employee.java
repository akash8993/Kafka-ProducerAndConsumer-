package com.consumer.Kafka.Consumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees_consumers")
@Data
public class Employee {
    @Id
    private Long id;
    private String name;
    private String department;
    private Double salary;
}
