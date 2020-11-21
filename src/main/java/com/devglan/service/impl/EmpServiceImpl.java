package com.devglan.service.impl;

import com.devglan.dao.EmpDao;
import com.devglan.model.Employee;
import com.devglan.model.EmpDto;
import com.devglan.model.EmpTr;
import com.devglan.service.EmpService;
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
@Service(value = "empService")
public class EmpServiceImpl implements EmpService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	
	
	@Autowired
	private EmpDao empDao;

	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		empDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		empDao.deleteById(id);
	}

	@Override
	public Employee findOne(String username) {
		 logger.info("userid: {}",username);
		//return empDao.findByUserid(username);

		 Employee newEmp = new Employee();
		 Employee emp = new Employee();
		    int flg=-1;
			List<Employee> list = new ArrayList<>();
			empDao.findAll().iterator().forEachRemaining(list::add);
			Iterator<Employee> iterator = list.iterator();
			while (iterator.hasNext()) {
				emp = iterator.next();
				if(username.equals(emp.getUserid())) {
					flg=0;
					break;
				}
			}
			if(flg==-1) {
				 logger.info("NO MATCH FOUND");
				 return newEmp;
			}
			
    	newEmp.setId(emp.getId());
    	newEmp.setAddress(emp.getAddress());
    	newEmp.setCell(emp.getCell());
    	newEmp.setEmail(emp.getEmail());
    	newEmp.setFirstName(emp.getFirstName());
    	newEmp.setLastName(emp.getLastName());
    	newEmp.setUserid(emp.getUserid());
        logger.info("id: {}",newEmp.getId());
        logger.info("address: {}",newEmp.getAddress());
        logger.info("Cell: {}",newEmp.getCell());
        logger.info("Email: {}",newEmp.getEmail());
        logger.info("UserId {}",newEmp.getUserid());
        logger.info("FirstName: {}",newEmp.getFirstName());
        logger.info("LastName: {}",newEmp.getLastName());
        return emp;		
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> optionalUser = empDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public EmpDto update(EmpDto empDto) {
    	Employee emp = findById(empDto.getId());
        if(emp != null) {
            BeanUtils.copyProperties(empDto, emp, "password", "username");
            empDao.save(emp);
            logger.info("Emp Update Called");
            
/*        	Employee newEmp = new Employee();
        	newEmp.setUserid(emp.getUserid());
        	newEmp.setFirstName(emp.getFirstName());
        	newEmp.setLastName(emp.getLastName());
        	newEmp.setEmail(emp.getEmail());
        	newEmp.setCell(emp.getCell());
        	newEmp.setAddress(emp.getAddress());
            empDao.save(newEmp);   */
        }
        else {
            logger.info("Emp Update not Called");
        }
        return empDto;
    }

    @Override
    public Employee save(EmpDto emp) {
    	Employee newEmp = new Employee();
    	newEmp.setUserid(emp.getUserid());
    	newEmp.setFirstName(emp.getFirstName());
    	newEmp.setLastName(emp.getLastName());
    	newEmp.setEmail(emp.getEmail());
    	newEmp.setCell(emp.getCell());
    	newEmp.setAddress(emp.getAddress());
        return empDao.save(newEmp);
    }
}
