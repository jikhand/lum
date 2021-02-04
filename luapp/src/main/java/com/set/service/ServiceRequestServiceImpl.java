package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ServiceRequestDao;
import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestDetails;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
	@Autowired
	private ServiceRequestDao serviceRequestDao;
	@Override
	public void save(ServiceRequest serviceRequest) {
		// TODO Auto-generated method stub
		serviceRequestDao.save(serviceRequest);
	}
	  
	@Override
	public List<Map<String , String>> list(String userFilter) {
		return serviceRequestDao.list(userFilter);
	}

	@Override
	public ServiceRequest getElementById(int UID) {
		// TODO Auto-generated method stub
		return serviceRequestDao.getElementById(UID);
	}

	@Override
	public Map<String , String> getServiceRequestbyId(int UID) {
		// TODO Auto-generated method stub
		return serviceRequestDao.getServiceRequestbyId(UID);
	}

	@Override
	public void update(ServiceRequest serviceRequest) {
		serviceRequestDao.update(serviceRequest);
	}

	@Override
	public void deleteElementById(ServiceRequest id) {
		serviceRequestDao.deleteElementById(id);
	}

	@Override
	public boolean IsExist(String name) {
		return serviceRequestDao.IsExist(name);
	}

	@Override
	public int gettotalcount() {
		return serviceRequestDao.gettotalcount();
	}

	@Override
	public ServiceRequestCategory retrieveFromId(String categoryId) {
		return serviceRequestDao.retrieveFromId(categoryId);
	}

	@Override
	public List<Map<String , String>> list1(String category) {
		return serviceRequestDao.list1(category);
	}

	@Override
	public ServiceRequestDetails listServiceRequest(int pagenumber, String searchdata) {
		return serviceRequestDao.listServiceRequest(pagenumber, searchdata);
	}

	@Override
	public ServiceRequestDetails getAllRequestorServiceRequest(int pagenumber, String searchdata,String userFilter) {
		return serviceRequestDao.getAllRequestorServiceRequest(pagenumber, searchdata, userFilter);
	}

	@Override
	public ServiceRequestDetails getAllAssignerServiceRequest(int pagenumber, String searchdata,String userFilter) {
		return serviceRequestDao.getAllAssignerServiceRequest(pagenumber, searchdata, userFilter);
	}
	
}
