package com.devglan.service.impl;

import com.devglan.dao.VstDao;
import com.devglan.model.Visitor;
import com.devglan.model.VstDto;
import com.devglan.model.VstTr;
import com.devglan.service.VstService;
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
@Service(value = "vstService")
public class VstServiceImpl implements VstService {
	
	private static final Logger logger = LoggerFactory.getLogger(VstServiceImpl.class);
	
	
	@Autowired
	private VstDao empDao;

	public List<Visitor> findAll() {
		List<Visitor> list = new ArrayList<>();
		empDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		empDao.deleteById(id);
	}

	@Override
	public Visitor findOne(String username) {
		 logger.info("userid: {}",username);
		//return empDao.findByUserid(username);

		 Visitor newVst = new Visitor();
		 Visitor emp = new Visitor();
		    int flg=-1;
			List<Visitor> list = new ArrayList<>();
			empDao.findAll().iterator().forEachRemaining(list::add);
			Iterator<Visitor> iterator = list.iterator();
			while (iterator.hasNext()) {
				emp = iterator.next();
				if(username.equals(emp.getUserid())) {
					flg=0;
					break;
				}
			}
			if(flg==-1) {
				 logger.info("NO MATCH FOUND");
				 return newVst;
			}
			
    	newVst.setId(emp.getId());
    	newVst.setAddress(emp.getAddress());
    	newVst.setCompany(emp.getCompany());
    	newVst.setCell(emp.getCell());
    	newVst.setEmail(emp.getEmail());
    	newVst.setFirstName(emp.getFirstName());
    	newVst.setLastName(emp.getLastName());
    	newVst.setUserid(emp.getUserid());
        logger.info("id: {}",newVst.getId());
        logger.info("address: {}",newVst.getAddress());
        logger.info("Cell: {}",newVst.getCell());
        logger.info("Email: {}",newVst.getEmail());
        logger.info("UserId {}",newVst.getUserid());
        logger.info("FirstName: {}",newVst.getFirstName());
        logger.info("LastName: {}",newVst.getLastName());
        return emp;		
	}

	@Override
	public Visitor findById(int id) {
		Optional<Visitor> optionalUser = empDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public VstDto update(VstDto empDto) {
    	Visitor emp = findById(empDto.getId());
        if(emp != null) {
            BeanUtils.copyProperties(empDto, emp, "password", "username");
            empDao.save(emp);
            logger.info("Vst Update Called");
            
/*        	Visitor newVst = new Visitor();
        	newVst.setUserid(emp.getUserid());
        	newVst.setFirstName(emp.getFirstName());
        	newVst.setLastName(emp.getLastName());
        	newVst.setEmail(emp.getEmail());
        	newVst.setCell(emp.getCell());
        	newVst.setAddress(emp.getAddress());
            empDao.save(newVst);   */
        }
        else {
            logger.info("Vst Update not Called");
        }
        return empDto;
    }

    @Override
    public Visitor save(VstDto emp) {
    	Visitor newVst = new Visitor();
    	newVst.setUserid(emp.getUserid());
    	newVst.setFirstName(emp.getFirstName());
    	newVst.setLastName(emp.getLastName());
    	newVst.setEmail(emp.getEmail());
    	newVst.setCompany(emp.getCompany());
    	newVst.setCell(emp.getCell());
    	newVst.setAddress(emp.getAddress());
        return empDao.save(newVst);
    }
}
