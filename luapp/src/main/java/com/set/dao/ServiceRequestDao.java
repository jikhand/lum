package com.set.dao;

import java.util.List;
import java.util.Map;

import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestDetails;
import com.set.model.User;

public interface ServiceRequestDao {
	  void save(ServiceRequest serviceRequest);
	  List<Map<String , String>> list1(String category);
	  List<Map<String , String>> list(String userFilter);
	  void update(ServiceRequest serviceRequest);
	  ServiceRequest getElementById(int UID);
	  Map<String , String> getServiceRequestbyId(int UID);
	  public void deleteElementById(ServiceRequest id);
	  public boolean IsExist(String name);
	  public int gettotalcount();
	   public  ServiceRequestCategory retrieveFromId(String categoryId);
		  public ServiceRequestDetails listServiceRequest(int pagenumber, String searchdata);
		  public ServiceRequestDetails getAllRequestorServiceRequest(int pagenumber, String searchdata,String userFilter);
		  public ServiceRequestDetails getAllAssignerServiceRequest(int pagenumber, String searchdata,String userFilter);
}
