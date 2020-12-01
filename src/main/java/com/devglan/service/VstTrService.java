package com.devglan.service;

import com.devglan.model.VstTr;
import com.devglan.model.VstTrDto;
import com.devglan.model.Visitor;

import java.util.List;

public interface VstTrService {

    VstTr save(VstTrDto emptr);
    List<VstTr> findAll();
    void delete(int id);

    VstTr findOne(String id);
    
    VstTr findById(int id);

    VstTrDto update(VstTrDto empDto);
}
