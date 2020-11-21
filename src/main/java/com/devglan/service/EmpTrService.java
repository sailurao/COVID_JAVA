package com.devglan.service;

import com.devglan.model.EmpTr;
import com.devglan.model.EmpTrDto;
import com.devglan.model.Employee;

import java.util.List;

public interface EmpTrService {

    EmpTr save(EmpTrDto emptr);
    List<EmpTr> findAll();
    void delete(int id);

    EmpTr findOne(String id);
    
    EmpTr findById(int id);

    EmpTrDto update(EmpTrDto empDto);
}
