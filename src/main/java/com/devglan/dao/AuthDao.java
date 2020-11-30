package com.devglan.dao;

import com.devglan.model.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDao extends CrudRepository<Auth, Integer> {

    Auth findByUsername(String username);
}
