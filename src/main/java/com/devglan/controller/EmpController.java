package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Employee;
import com.devglan.model.EmpDto;
import com.devglan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping
    public ApiResponse<Employee> saveEmployee(@RequestBody EmpDto emp){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee saved successfully.",empService.save(emp));
    }

    @GetMapping
    public ApiResponse<List<Employee>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee list fetched successfully.",empService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee fetched successfully.",empService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<EmpDto> update(@RequestBody EmpDto empDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee updated successfully.",empService.update(empDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	empService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee deleted successfully.", null);
    }



}
