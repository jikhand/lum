package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.set.dao.LogsDao;
import com.set.model.TblLogs;
@Service
@Transactional
public class LogsServiceImp implements LogsService {

	@Autowired 
	private LogsDao logsDao; 
	@Override
	public List<Map<String , String>> list(int pagenumber,String searchdata) {
		System.out.println("yy 22222>>>>");
		return logsDao.list(pagenumber,searchdata);
	}
	@Override
	public void save(TblLogs tblLogs) {
		logsDao.save(tblLogs);
	}
	@Override
	public int totalLogs() {
		// TODO Auto-generated method stub
		return logsDao.totalLogs();
	}

}
