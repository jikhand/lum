package com.set.service;

import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestSubCategoryDetails;

public interface ServiceRequestCategoryService {
	  void save(ServiceRequestCategory serviceRequestCategory);
	  ServiceRequestCategoryDetails list(String category);
	  ServiceRequestCategory getCategoryById(String CID);
	  void update(ServiceRequestCategory serviceRequestCategory);
	  public void deleteElementById(ServiceRequestCategory id);
	  public boolean IsExist(String details, String categoryId);
	  public boolean SubCategoryIsExist(String details, String categoryId, String subCategoryId);
	  public int gettotalcount();
      public ServiceRequestCategoryDetails getListCategory(String category, int pageNumber, String SearchData);
      public ServiceRequestSubCategoryDetails getListSubCategory(String category, int pageNumber, String SearchData);
}
