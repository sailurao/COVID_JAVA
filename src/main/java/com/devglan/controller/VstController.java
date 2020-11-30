package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Visitor;
import com.devglan.model.VstDto;
import com.devglan.service.VstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/visitors")
public class VstController {

    @Autowired
    private VstService vstService;

    @PostMapping
    public ApiResponse<Visitor> saveVisitor(@RequestBody VstDto vst){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor saved successfully.",vstService.save(vst));
    }

    @GetMapping
    public ApiResponse<List<Visitor>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor list fetched successfully.",vstService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Visitor> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor fetched successfully.",vstService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<VstDto> update(@RequestBody VstDto vstDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor updated successfully.",vstService.update(vstDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	vstService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor deleted successfully.", null);
    }



}
