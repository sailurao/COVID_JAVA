package com.devglan.service.impl;

import com.devglan.dao.AuthDao;
import com.devglan.model.Auth;
import com.devglan.model.AuthDto;
import com.devglan.service.AuthService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.*;  
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Transactional
@Service(value = "aUTHService")
public class AuthSerImpl implements AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthSerImpl.class);
	
	
	@Autowired
	private AuthDao empDao;

	public List<Auth> findAll() {
		List<Auth> list = new ArrayList<>();
		empDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		//empDao.deleteById(id);
	}

	@Override
	public Auth findOne(String username) {
		 logger.info("userid: {}",username);
		//return empDao.findByUserid(username);

		 Auth newAuth = new Auth();
		 Auth emp = new Auth();
		    int flg=-1;
			List<Auth> list = new ArrayList<>();
			empDao.findAll().iterator().forEachRemaining(list::add);
			Iterator<Auth> iterator = list.iterator();
			while (iterator.hasNext()) {
				emp = iterator.next();
				if(username.equals(emp.getUsername())) {
					flg=0;
					break;
				}
			}
			if(flg==-1) {
				 logger.info("NO MATCH FOUND");
				 return newAuth;
			}
			
    	newAuth.setId(emp.getId());
    	newAuth.setUsername(emp.getUsername());
    	newAuth.setPassword(emp.getPassword());
        logger.info("id: {}",newAuth.getId());
        logger.info("username: {}",newAuth.getUsername());
        logger.info("Password: {}",newAuth.getPassword());
        return emp;		
	}

	public int CheckPass(AuthDto emp1) {
		 Auth emp = new Auth();
		    int flg=-1;
			List<Auth> list = new ArrayList<>();
			empDao.findAll().iterator().forEachRemaining(list::add);
			Iterator<Auth> iterator = list.iterator();
			while (iterator.hasNext()) {
				emp = iterator.next();
				if((emp1.getUsername().equals(emp.getUsername()))) {
					if((emp1.getPassword().equals(emp.getPassword()))) {
						logger.info(" MATCH FOUND-{}",TmrTask.Token);
						flg=0;
						return 1;
					}
				}
			}
			if(flg==-1) {
				 logger.info("NO MATCH FOUND");
				 return 0;
			}
			return 0;
	}

	
	
	@Override
	public Auth findById(int id) {
		Optional<Auth> optionalUser = empDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public AuthDto update(AuthDto empDto) {
    	Auth emp = findById(empDto.getId());
        if(emp != null) {
            BeanUtils.copyProperties(empDto, emp, "password", "username");
            empDao.save(emp);
            logger.info("Auth Update Called");
            
/*        	Auth newAuth = new Auth();
        	newAuth.setUserid(emp.getUserid());
        	newAuth.setFirstName(emp.getFirstName());
        	newAuth.setLastName(emp.getLastName());
        	newAuth.setEmail(emp.getEmail());
        	newAuth.setCell(emp.getCell());
        	newAuth.setAddress(emp.getAddress());
            empDao.save(newAuth);   */
        }
        else {
            logger.info("Auth Update not Called");
        }
        return empDto;
    }

    @Override
    public Auth save(AuthDto emp) {
    	Auth newAuth = new Auth();
    	newAuth.setUsername(emp.getPassword());
    	newAuth.setPassword(emp.getPassword());
        return empDao.save(newAuth);
    }
}
