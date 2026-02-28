package com.BasicsOfSpring.SpringBasics.controller;

import com.BasicsOfSpring.SpringBasics.Employee;
//import jakarta.websocket.server.PathParam;
import com.BasicsOfSpring.SpringBasics.EmployeeJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {
    @Autowired
    EmployeeJPARepository jpa;

    @GetMapping("/a")
    public String sendData(@RequestParam String name, @RequestParam String id) {
        System.out.println(name);
        System.out.println(id);
        return "data fetched";
    }

    @GetMapping("/a/{name}/{id}")
    public String sendData(@PathVariable String name, @PathVariable int id) {
        System.out.println(name);
        System.out.println(id);
        return "data fetched";
    }


    @PostMapping("/emp")
    public String createEmployee(@RequestBody Employee e) {
//        System.out.println(e.getId());
//        System.out.println(e.getName());
//        System.out.println(e.getSalary());
        jpa.save(e);
        return "data saved";
    }

    @GetMapping("/get-id/{id}")
    public Employee getById(@PathVariable int id) {
        Optional<Employee> emp = jpa.findById(id);
        if (emp.isPresent()) {
            return emp.get();
        } else {
            return null;
        }
    }

    @GetMapping("/get-all")
    public List<Employee> getAllEmployee() {
        List<Employee> res = jpa.findAll();
        return res;
    }

    //for full updating use putmatting
    @PutMapping("/update-id/{id}")

    public String updateEmployee(@PathVariable int id, @RequestBody Employee e) {
        Employee employee = getById(id);
        employee.setId(e.getId());
        employee.setId(e.getId());
        employee.setName(e.getName());
        employee.setSalary(e.getSalary());
        jpa.save(employee);
        return "data updated";
    }

    //for partial mapping use patchMapping
    @PatchMapping("/update-name/{id}")
    public String upadteEmployeeDetails(@PathVariable int id, @RequestBody Employee e) {
        Employee emp = getById(id);
        if (emp != null) {

            if (e.getName() != null) {
                emp.setName(e.getName());
            } else if (e.getSalary()!= 0.0) {
                emp.setSalary(e.getSalary());
            } else if (e.getId() != 0) {
                emp.setId(e.getId());
            }
        jpa.save(emp);
        return "data updated";
        } else {
            throw new EmployeeNotFoundException("Employee not found  by id: "+id);
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id, @RequestBody Employee e){
        Employee emp= getById(id);
        if(emp!=null){
            jpa.delete(emp);
            return "Deleted";
        }else{
            return "data not exist";
        }
    }
    @GetMapping("/get-name/{name}")
    public Employee findByName(@PathVariable String name){
        return  jpa.getByName(name);
    }

    @GetMapping("/get-sal/{salary}")
    public Employee findBySalary(@PathVariable double salary){
        return  jpa.getBySalary(salary);
    }

    @GetMapping("/get-name-sal/{name}/{salary}")
    public Employee findByNameAndSalary(@PathVariable String name, @PathVariable double salary){
        return  jpa.getByNameAndSalary(name, salary);
    }

    @PatchMapping("/update-sal/{oldSalary}/{newSalary}")
    public String updateBySalary(@PathVariable double oldSalary, @PathVariable double newSalary){
       int count=  jpa.updateBySalary(oldSalary,newSalary);
       if(count>0){
           return "updated";
       }else{
           return  "data not exist";
       }
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException ex){
        return ex.getMessage();
    }
}

