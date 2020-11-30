package com.devglan.dao;

import com.devglan.model.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VstDao extends CrudRepository<Visitor, Integer> {

    Visitor findByUserid(String userid);
}
