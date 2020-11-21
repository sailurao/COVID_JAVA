package com.devglan.service;

import com.devglan.model.Employee;
import com.devglan.model.EmpDto;

import java.util.List;

public interface EmpService {

    Employee save(EmpDto emp);
    List<Employee> findAll();
    void delete(int id);

    Employee findOne(String userid);

    Employee findById(int emp_id);

    EmpDto update(EmpDto empDto);
}
