package com.devglan.service;

import com.devglan.model.Visitor;
import com.devglan.model.VstDto;

import java.util.List;

public interface VstService {

	Visitor save(VstDto emp);
    List<Visitor> findAll();
    void delete(int id);

    Visitor findOne(String userid);

    Visitor findById(int emp_id);

    VstDto update(VstDto empDto);
}
