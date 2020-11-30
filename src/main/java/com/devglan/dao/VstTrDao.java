package com.devglan.dao;

import com.devglan.model.VstTr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VstTrDao extends CrudRepository<VstTr, Integer> {

    VstTr findByDate(String username); //
}
