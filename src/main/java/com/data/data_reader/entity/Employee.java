package com.data.data_reader.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "builder", setterPrefix = "with")
public class Employee {

    @Id
    private double id;
    private String name;
    private double salary;

}
