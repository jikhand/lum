package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ServiceRequestCategoryDao;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestSubCategoryDetails;

@Service
public class ServiceRequestCategoryServiceImpl implements ServiceRequestCategoryService {
	@Autowired
	private ServiceRequestCategoryDao serviceRequestCategoryDao;
	@Override
	public void save(ServiceRequestCategory serviceRequestCategory) {
		// TODO Auto-generated method stub
		serviceRequestCategoryDao.save(serviceRequestCategory);
	}

	@Override
	public ServiceRequestCategoryDetails list(String category) {
		return serviceRequestCategoryDao.list(category);
	}


	@Override
	public void update(ServiceRequestCategory serviceRequestCategory) {
		serviceRequestCategoryDao.update(serviceRequestCategory);
	}

	@Override
	public void deleteElementById(ServiceRequestCategory id) {
		serviceRequestCategoryDao.deleteElementById(id);
	}

	@Override
	public boolean IsExist(String details, String categoryId) {
		return serviceRequestCategoryDao.IsExist(details, categoryId);
	}

	@Override
	public boolean SubCategoryIsExist(String details, String categoryId, String subCategoryId) {
		return serviceRequestCategoryDao.SubCategoryIsExist(details, categoryId, subCategoryId);
	}

	@Override
	public int gettotalcount() {
		return serviceRequestCategoryDao.gettotalcount();
	}

	@Override
	public ServiceRequestCategory getCategoryById(String CID) {
		// TODO Auto-generated method stub
		return serviceRequestCategoryDao.getCategoryById(CID);
	}

	@Override
	public ServiceRequestCategoryDetails getListCategory(String category, int pageNumber, String SearchData) {
		return serviceRequestCategoryDao.getListCategory(category,pageNumber,SearchData);
	}

	@Override
	public ServiceRequestSubCategoryDetails getListSubCategory(String category, int pageNumber, String SearchData) {
		return serviceRequestCategoryDao.getListSubCategory(category,pageNumber,SearchData);
	}


}
