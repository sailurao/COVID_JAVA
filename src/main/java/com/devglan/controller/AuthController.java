package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Auth;
import com.devglan.model.AuthDto;
import com.devglan.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devglan.service.impl.AuthSerImpl;
import com.devglan.service.impl.TmrTask;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthSerImpl.class);
	
    @Autowired
    private AuthService empTrService;

    @PostMapping
    public ApiResponse<Auth> saveAuth(@RequestBody AuthDto emp){
    	
    	if(empTrService.CheckPass(emp)!=0) {
    		TmrTask.count=100; //keep the token longer
    		return new ApiResponse<>(HttpStatus.OK.value(), TmrTask.Token,emp);
    	}
    	else {
    		return new ApiResponse<>(HttpStatus.OK.value(), "",emp);
    	}
    }

    /*@GetMapping
    public ApiResponse<List<Auth>> listUser(){
        //return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction list fetched successfully.",empTrService.findAll());
    }
*/
    @GetMapping("/{token}")
    public ApiResponse<Auth> getOne(@PathVariable String token){
    	Auth ath=new Auth();
    	if(token.equals(TmrTask.Token)) {
    		TmrTask.count=100; //keep the token longer
    		 logger.info("GET- MATCH FOUND-{},{}",TmrTask.Token,token);
    		return new ApiResponse<>(HttpStatus.OK.value(), "match",ath);
    	}
    	else {
    		logger.info("GET- NO MATCH FOUND- {},{}",TmrTask.Token,token);
    		return new ApiResponse<>(HttpStatus.OK.value(), "noMatch",ath);
    	}
    }

  /*  @PutMapping("/{id}")
    public ApiResponse<AuthDto> update(@RequestBody AuthDto empDto) {
       // return new ApiResponse<>(HttpStatus.OK.value(), "Employee Transaction updated successfully.",empTrService.update(empDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	//AuthService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Not supported.", null);
    }*/
}
