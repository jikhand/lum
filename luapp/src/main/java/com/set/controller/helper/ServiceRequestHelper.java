/**
 * 
 */
package com.set.controller.helper;

import org.springframework.stereotype.Component;

import com.set.dto.AssignmentEvaluationDto;
import com.set.dto.AssignmentMasterDto;
import com.set.dto.ServiceRequestCategoryDto;
import com.set.dto.ServiceRequestDto;
import com.set.model.AssignmentEvaluation;
import com.set.model.AssignmentEvaluationId;
import com.set.model.AssignmentMaster;
import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;

/**
 * @author Administrator
 *
 */
@Component
public class ServiceRequestHelper {

	/**
	 * 
	 */
	public ServiceRequestHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public ServiceRequestCategoryDto setServiceRequestCategoryDto(ServiceRequestCategory serviceRequestCategory) {
		ServiceRequestCategoryDto serviceRequestCategoryDto = new ServiceRequestCategoryDto();
		serviceRequestCategoryDto.setCategoryId(serviceRequestCategory.getCategoryId());
		serviceRequestCategoryDto.setResolveDays(serviceRequestCategory.getResolveDays());
		serviceRequestCategoryDto.setParentCategory(serviceRequestCategory.getParentCategory());
		serviceRequestCategoryDto.setCategoryType(serviceRequestCategory.getCategoryType());
		serviceRequestCategoryDto.setDescription(serviceRequestCategory.getDescription());
		return serviceRequestCategoryDto;
	}
	
	public ServiceRequestDto setServiceRequestDto(ServiceRequest serviceRequest) {
		ServiceRequestDto serviceRequestDto = new ServiceRequestDto();
		serviceRequestDto.setServiceRequestId(serviceRequest.getServiceRequestId());
		serviceRequestDto.setAssignedTo(serviceRequest.getAssignedTo());
		serviceRequestDto.setDetails(serviceRequest.getDetails());
		serviceRequestDto.setRaisedOn(serviceRequest.getRaisedOn());
		serviceRequestDto.setRemarks(serviceRequest.getRemarks());
		serviceRequestDto.setRequestorId(serviceRequest.getRequestorId());
		serviceRequestDto.setServiceRequestClosedOn(serviceRequest.getServiceRequestClosedOn());
		serviceRequestDto.setServiceRequestTitle(serviceRequest.getServiceRequestTitle());
		return serviceRequestDto;
	}
	
}
