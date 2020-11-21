package com.devglan.service.impl;

import com.devglan.dao.EmpTrDao;
import com.devglan.model.EmpTr;
import com.devglan.model.EmpTrDto;
import com.devglan.model.Employee;
import com.devglan.service.EmpTrService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Transactional
@Service(value = "empTrService")
public class EmpTrSerImpl implements EmpTrService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	
	
	@Autowired
	private EmpTrDao empTrDao;

	public List<EmpTr> findAll() {
		List<EmpTr> list = new ArrayList<>();
		empTrDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		empTrDao.deleteById(id);
	}

	@Override
	public EmpTr findOne(String empid) {
		return empTrDao.findByDate(empid);
	}
	
	
	@Override
	public EmpTr findById(int id) {
		Optional<EmpTr> optionalUser = empTrDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public EmpTrDto update(EmpTrDto empDto) {
    	EmpTr emp = findById(empDto.getId());
        if(emp != null) {
            BeanUtils.copyProperties(empDto, emp, "password", "username");
            empTrDao.save(emp);
            logger.info("EmpTr Update Called");
            
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
            logger.info("EmpTr Update not Called");
        }
        return empDto;
    }

    @Override
    public EmpTr save(EmpTrDto emp) {
    	EmpTr newEmp = new EmpTr();
    	newEmp.setEmpid(emp.getEmpid());
    	newEmp.setDate(emp.getDate());
    	newEmp.setTime(emp.getTime());
    	newEmp.setQ1(emp.getQ1());
    	newEmp.setQ2(emp.getQ2());
    	newEmp.setQ3(emp.getQ3());
    	newEmp.setQ4(emp.getQ4());
    	newEmp.setQ5(emp.getQ5());
    	newEmp.setTemp(emp.getTemp());
    	newEmp.setId(0);
        logger.info("id: {}",newEmp.getId());
        logger.info("empid: {}",newEmp.getEmpid());
        logger.info("Date: {}",newEmp.getDate());
        logger.info("Time: {}",newEmp.getTime());
        logger.info("Q1: {}",newEmp.getQ1());
        logger.info("Q2: {}",newEmp.getQ2());
        logger.info("Q3: {}",newEmp.getQ3());
        logger.info("Q4: {}",newEmp.getQ4());
        logger.info("Q5: {}",newEmp.getQ5());
        logger.info("Temp: {}",newEmp.getTemp());
        //logger.info("Val: {}",newEmp);
        logger.info("EmpTr addition Called");
        return empTrDao.save(newEmp);
    }
}
