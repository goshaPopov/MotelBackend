package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT * FROM employee WHERE phone = ?1",nativeQuery = true)
    Employee checkPhone(String passport);

}
