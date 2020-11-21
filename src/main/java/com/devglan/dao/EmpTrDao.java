package com.devglan.dao;

import com.devglan.model.EmpTr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpTrDao extends CrudRepository<EmpTr, Integer> {

    EmpTr findByDate(String username); //
}
