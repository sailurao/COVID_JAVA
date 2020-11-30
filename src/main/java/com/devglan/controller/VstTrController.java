package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.VstTr;
import com.devglan.model.VstTrDto;
import com.devglan.service.VstTrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/visitor-trs")
public class VstTrController {

    @Autowired
    private VstTrService vstTrService;

    @PostMapping
    public ApiResponse<VstTr> saveVstTr(@RequestBody VstTrDto vst){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor Transaction saved successfully.",vstTrService.save(vst));
    }

    @GetMapping
    public ApiResponse<List<VstTr>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor Transaction list fetched successfully.",vstTrService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<VstTr> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor fetched successfully.",vstTrService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<VstTrDto> update(@RequestBody VstTrDto vstDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor Transaction updated successfully.",vstTrService.update(vstDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	vstTrService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor Transaction deleted successfully.", null);
    }
}
