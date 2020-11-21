package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.EmpTr;
import com.devglan.model.EmpTrDto;
import com.devglan.service.EmpTrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee-trs")
public class EmpTrController {

    @Autowired
    private EmpTrService empTrService;

    @PostMapping
    public ApiResponse<EmpTr> saveEmpTr(@RequestBody EmpTrDto emp){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction saved successfully.",empTrService.save(emp));
    }

    @GetMapping
    public ApiResponse<List<EmpTr>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction list fetched successfully.",empTrService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<EmpTr> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee fetched successfully.",empTrService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<EmpTrDto> update(@RequestBody EmpTrDto empDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction updated successfully.",empTrService.update(empDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	empTrService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction deleted successfully.", null);
    }
}
