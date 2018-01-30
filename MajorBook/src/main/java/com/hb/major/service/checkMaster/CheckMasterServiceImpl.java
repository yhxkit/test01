package com.hb.major.service.checkMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.major.model.checkMaster.CheckMasterDao;

@Service
public class CheckMasterServiceImpl implements CheckMasterService {
	
	@Autowired
	CheckMasterDao checkMasterDao;
	
	
	@Override
	public boolean checkMaster(String id) throws Exception {
	
		boolean checkresult = false;
		
		int checknum =checkMasterDao.masterCountId(id);
		
		if(checknum>0) {
			//마스터임 checkresult = true;
			checkresult = true;
			
		}else {
			//마스터가 아님checkresult = false;
			checkresult = false;
		}
		
		return checkresult;
		
	}

}
