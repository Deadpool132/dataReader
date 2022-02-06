package com.data.data_reader.repository;

import com.data.data_reader.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Employee, Integer> {
}
