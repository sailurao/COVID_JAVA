package com.devglan.service;

import com.devglan.model.Auth;
import com.devglan.model.AuthDto;

import java.util.List;

public interface AuthService {

    Auth save(AuthDto emp);
    List<Auth> findAll();
    void delete(int id);

    int CheckPass(AuthDto emp);
    
    Auth findOne(String userid);

    Auth findById(int emp_id);

    AuthDto update(AuthDto empDto);
}
