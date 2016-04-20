package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
