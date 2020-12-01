package com.devglan.service.impl;

import com.devglan.dao.VstTrDao;
import com.devglan.model.VstTr;
import com.devglan.model.VstTrDto;
import com.devglan.model.Visitor;
import com.devglan.service.VstTrService;
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
@Service(value = "vstTrService")
public class VstTrSerImpl implements VstTrService {
	
	private static final Logger logger = LoggerFactory.getLogger(VstServiceImpl.class);
	
	
	@Autowired
	private VstTrDao vstTrDao;

	public List<VstTr> findAll() {
		List<VstTr> list = new ArrayList<>();
		vstTrDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		vstTrDao.deleteById(id);
	}

	@Override
	public VstTr findOne(String vstid) {
		return vstTrDao.findByDate(vstid);
	}
	
	
	@Override
	public VstTr findById(int id) {
		Optional<VstTr> optionalUser = vstTrDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public VstTrDto update(VstTrDto vstDto) {
    	VstTr vst = findById(vstDto.getId());
        if(vst != null) {
            BeanUtils.copyProperties(vstDto, vst, "password", "username");
            vstTrDao.save(vst);
            logger.info("VstTr Update Called");
            
/*        	Visitor newVst = new Visitor();
        	newVst.setUserid(vst.getUserid());
        	newVst.setFirstName(vst.getFirstName());
        	newVst.setLastName(vst.getLastName());
        	newVst.setEmail(vst.getEmail());
        	newVst.setCell(vst.getCell());
        	newVst.setAddress(vst.getAddress());
            vstDao.save(newVst);   */
        }
        else {
            logger.info("VstTr Update not Called");
        }
        return vstDto;
    }

    @Override
    public VstTr save(VstTrDto vst) {
    	VstTr newVst = new VstTr();
    	newVst.setVisid(vst.getVisid());
    	newVst.setEmpid(vst.getEmpid());
    	newVst.setDate(vst.getDate());
    	newVst.setTime(vst.getTime());
    	newVst.setQ1(vst.getQ1());
    	newVst.setQ2(vst.getQ2());
    	newVst.setQ3(vst.getQ3());
    	newVst.setQ4(vst.getQ4());
    	newVst.setQ5(vst.getQ5());
    	newVst.setTemp(vst.getTemp());
    	newVst.setId(0);
        logger.info("id: {}",newVst.getId());
        logger.info("visid: {}",newVst.getVisid());
        logger.info("Date: {}",newVst.getDate());
        logger.info("Time: {}",newVst.getTime());
        logger.info("Q1: {}",newVst.getQ1());
        logger.info("Q2: {}",newVst.getQ2());
        logger.info("Q3: {}",newVst.getQ3());
        logger.info("Q4: {}",newVst.getQ4());
        logger.info("Q5: {}",newVst.getQ5());
        logger.info("Temp: {}",newVst.getTemp());
        //logger.info("Val: {}",newVst);
        logger.info("VstTr addition Called");
        return vstTrDao.save(newVst);
    }
}
