package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.Employee;
import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.services.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@RestController
public class EmployeeController {

    private final Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> allEmployee(){
        log.info("Get all Employees");
        List<Employee> employees = employeeService.findAll();
        if (employees == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        log.info("Get employee #" + id);
        Employee employee = employeeService.findOne(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

//    TODO: Check Unique
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
        log.info("Create new employee...");
        if (employee != null){
            Employee newEmployee = employeeService.save(employee);
            return new ResponseEntity<>(newEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        log.info("Update employee #" + id);
        Employee oldEmployee = employeeService.findOne(id);
        if(oldEmployee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteGuest(@PathVariable("id") Long id){
        log.info("Delete guest #" + id);
        Employee employee = employeeService.findOne(id);
        if (employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}