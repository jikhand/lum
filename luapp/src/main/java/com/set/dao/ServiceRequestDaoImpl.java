package com.set.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.EmployeeMaster;
import com.set.model.ServiceRequest;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestDetails;
import com.set.model.StudentMaster;
import com.set.model.SubjectMaster;
import com.set.model.User;
@Transactional
@Repository
public class ServiceRequestDaoImpl implements ServiceRequestDao {
	@Autowired
	private SessionFactory sessionFactory; 
	@Override
	public void save(ServiceRequest serviceRequest) {
		sessionFactory.getCurrentSession().saveOrUpdate(serviceRequest);
	}

	@Override
	public List<Map<String , String>> list(String userFilter) {
		String searchString="";
		if (!userFilter.equalsIgnoreCase("null")) {
			searchString=" and u.role_id='"+userFilter+"'";
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " s.sr_id, s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension,if(s.sr_closedon>cast((now()) as date),'Active', 'Expired') FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.requestor_id=u.user_id where s.is_soft_delete = '0' "+searchString);
		List<Object[]> totalrows = query.list();
//		int totalNumbers=totalrows.size();
//		int maxPageData=10;
//		int start = pagenumber * maxPageData - maxPageData;
//		int end =10;
//		query.setFirstResult(start);
//		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("RequestorEmailId", row[9].toString());
				hm.put("RequestorFirstName", row[10].toString());
				hm.put("RequestorLastName", row[11].toString());
				hm.put("RequestorDOB", row[12].toString());
				hm.put("RequestorMobileNo", row[13].toString());
				hm.put("RequestorAddress1", row[14].toString());
				hm.put("RequestorAddress2", row[15].toString());
				hm.put("RequestorAddress3", row[16].toString());
				hm.put("RequestorCity", row[17].toString());
				hm.put("RequestorState", row[18].toString());
				hm.put("RequestorZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("Active", row[22].toString());
				arrayList.add(hm);
		}	
		return arrayList;
	}


	@Override
	public void update(ServiceRequest serviceRequest) {
		sessionFactory.getCurrentSession().update(serviceRequest);	
	}

	@Override
	public void deleteElementById(ServiceRequest serviceRequest) {
		sessionFactory.getCurrentSession().update(serviceRequest);
		
	}

	@Override
	public boolean IsExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int gettotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceRequest.class);
		criteria.add(Restrictions.eq("isSoftDelete",0));
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}
	
	public  ServiceRequestCategory retrieveFromId(String categoryId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ServiceRequestCategory.class);
		 cr.add(Restrictions.eq("categoryId",categoryId));
			cr.add(Restrictions.eq("isSoftDelete",0));
		 ServiceRequestCategory serviceRequestCategory = (ServiceRequestCategory)cr.uniqueResult();
		 return serviceRequestCategory;
	}

	@Override
	public List<Map<String , String>> list1(String userFilter) {
		String searchString="";
		if (!userFilter.equalsIgnoreCase("null")) {
			searchString=" and s.requestor_id='"+userFilter+"'";
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " s.sr_id, s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension,if(s.sr_closedon>cast((now()) as date),'Active', 'Expired') FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.assigned_to=u.user_id  where s.is_soft_delete = '0' "+searchString);
		List<Object[]> totalrows = query.list();
//		int totalNumbers=totalrows.size();
//		int maxPageData=10;
//		int start = pagenumber * maxPageData - maxPageData;
//		int end =10;
//		query.setFirstResult(start);
//		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("AssignerEmailId", row[9].toString());
				hm.put("AssignerFirstName", row[10].toString());
				hm.put("AssignerLastName", row[11].toString());
				hm.put("AssignerDOB", row[12].toString());
				hm.put("AssignerMobileNo", row[13].toString());
				hm.put("AssignerAddress1", row[14].toString());
				hm.put("AssignerAddress2", row[15].toString());
				hm.put("AssignerAddress3", row[16].toString());
				hm.put("AssignerCity", row[17].toString());
				hm.put("AssignerState", row[18].toString());
				hm.put("AssignerZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("Active", row[22].toString());
				hm.put("comment", row[23].toString());
				arrayList.add(hm);
		}	
		return arrayList;
	}
	
	@Override
	public ServiceRequestDetails listServiceRequest(int pagenumber,String searchdata) {
		String searchString="";
		LocalDateTime datetime=null;
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString=" and s.sr_id LIKE '%"+searchdata+"%' or s.sr_title LIKE '%"+searchdata +"%'"
							+ " or u.email_id LIKE '%"+searchdata +"%' or r.email_id LIKE '%"+searchdata +"%'";
		}
		ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
		String strQuery="SELECT"
				+ " s.sr_id, s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension, "
                + "r.email_id as RequestorEmailId, r.first_name as RequestorFN,"
                + " r.last_name as RequestorLN,r.dob as RequestorDOB, r.mobile_phone as RequestorMbleNo,"
				+" r.crsp_add1 as Requestoradd1,r.crsp_add2 as Requestoradd2,r.crsp_add3 as Requestoradd3,"
				+ "r.crsp_city as Requestorcity,r.crsp_state as Requestorstate,"
				+"r.crsp_zip as Requestorzip,if(s.sr_closedon>cast((now()) as date),'Active', 'Expired')  FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.assigned_to=u.user_id join lutbl_user_reg as r on s.requestor_id=r.user_id  where s.is_soft_delete = '0' "+searchString;
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
		System.out.println("strQuery"+strQuery);
		List<Object[]> totalrows = query.list();
		int totalNumbers=totalrows.size();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("AssignerEmailId", row[9].toString());
				hm.put("AssignerFirstName", row[10].toString());
				hm.put("AssignerLastName", row[11].toString());
				hm.put("AssignerDOB", row[12].toString());
				hm.put("AssignerMobileNo", row[13].toString());
				hm.put("AssignerAddress1", row[14].toString());
				hm.put("AssignerAddress2", row[15].toString());
				hm.put("AssignerAddress3", row[16].toString());
				hm.put("AssignerCity", row[17].toString());
				hm.put("AssignerState", row[18].toString());
				hm.put("AssignerZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("RequestorEmailId", row[22].toString());
				hm.put("RequestorFirstName", row[23].toString());
				hm.put("RequestorLastName", row[24].toString());
				hm.put("RequestorDOB", row[25].toString());
				hm.put("RequestorMobileNo", row[26].toString());
				hm.put("RequestorAddress1", row[27].toString());
				hm.put("RequestorAddress2", row[28].toString());
				hm.put("RequestorAddress3", row[29].toString());
				hm.put("RequestorCity", row[30].toString());
				hm.put("RequestorState", row[31].toString());
				hm.put("RequestorZip", row[32].toString());
				hm.put("Active", row[33].toString());
				arrayList.add(hm);
		}	
		serviceRequestDetails.setServiceRequest(arrayList);
		serviceRequestDetails.setCount(totalrows.size());
		serviceRequestDetails.setMessage("");
		if(totalrows.size()>0) {
		    serviceRequestDetails.setMessage("List Of ServiceRequest");
		}else {
		    serviceRequestDetails.setMessage("Service Request Record not found");				
		}
		return serviceRequestDetails; 
	}
	
	@Override
	public ServiceRequestDetails getAllRequestorServiceRequest(int pagenumber,String searchdata,String userFilter) {
		String searchString="";
		LocalDateTime datetime=null;
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString=" and s.sr_id LIKE '%"+searchdata+"%' or s.sr_title LIKE '%"+searchdata +"%'"
					+ " or u.email_id LIKE '%"+searchdata +"%'";
		}
		ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " s.sr_id, s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension,if(s.sr_closedon>cast((now()) as date),'Active', 'Expired') as active FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.assigned_to=u.user_id  where s.is_soft_delete = '0' and s.requestor_id='"+userFilter+"' "+searchString);
		List<Object[]> totalrows = query.list();
		int totalNumbers=totalrows.size();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("AssignerEmailId", row[9].toString());
				hm.put("AssignerFirstName", row[10].toString());
				hm.put("AssignerLastName", row[11].toString());
				hm.put("AssignerDOB", row[12].toString());
				hm.put("AssignerMobileNo", row[13].toString());
				hm.put("AssignerAddress1", row[14].toString());
				hm.put("AssignerAddress2", row[15].toString());
				hm.put("AssignerAddress3", row[16].toString());
				hm.put("AssignerCity", row[17].toString());
				hm.put("AssignerState", row[18].toString());
				hm.put("AssignerZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("Active", row[22].toString());
				arrayList.add(hm);
		}	
		serviceRequestDetails.setServiceRequest(arrayList);
		serviceRequestDetails.setCount(totalrows.size());
		serviceRequestDetails.setMessage("");
		if(totalrows.size()>0) {
		    serviceRequestDetails.setMessage("List Of ServiceRequest");
		}else {
		    serviceRequestDetails.setMessage("Service Request Record not found");				
		}
		return serviceRequestDetails;
	}
	
	@Override
	public ServiceRequestDetails getAllAssignerServiceRequest(int pagenumber,String searchdata,String userFilter) {
		String searchString="";
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString=" and s.sr_id LIKE '%"+searchdata+"%' or s.sr_title LIKE '%"+searchdata +"%'"
					+ " or u.email_id LIKE '%"+searchdata +"%'";
		}
		ServiceRequestDetails serviceRequestDetails=new ServiceRequestDetails();
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " s.sr_id, s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension,if(s.sr_closedon>cast((now()) as date),'Active', 'Expired') as active  FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.requestor_id=u.user_id where s.is_soft_delete = '0' and s.requestor_id='"+userFilter+"' "+searchString);
		List<Object[]> totalrows = query.list();
		int totalNumbers=totalrows.size();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();			
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("RequestorEmailId", row[9].toString());
				hm.put("RequestorFirstName", row[10].toString());
				hm.put("RequestorLastName", row[11].toString());
				hm.put("RequestorDOB", row[12].toString());
				hm.put("RequestorMobileNo", row[13].toString());
				hm.put("RequestorAddress1", row[14].toString());
				hm.put("RequestorAddress2", row[15].toString());
				hm.put("RequestorAddress3", row[16].toString());
				hm.put("RequestorCity", row[17].toString());
				hm.put("RequestorState", row[18].toString());
				hm.put("RequestorZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("Active", row[22].toString());
				arrayList.add(hm);
		}	
		serviceRequestDetails.setServiceRequest(arrayList);
		serviceRequestDetails.setCount(totalrows.size());
		serviceRequestDetails.setMessage("");
		if(totalrows.size()>0) {
		    serviceRequestDetails.setMessage("List Of ServiceRequest");
		}else {
		    serviceRequestDetails.setMessage("Service Request Record not found");				
		}
		return serviceRequestDetails;
	}
//	@Override
//	public ServiceRequestDetails list1(int pagenumber,String searchdata,String category) {
//		int maxPageData=10;
//		int start = pagenumber * maxPageData - maxPageData;
//		int end = 10;
////		String searchString="";
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceRequest.class);
//		System.out.println(category);
//		criteria.add(Restrictions.eq("assignedTo",category));
//		if (!searchdata.equalsIgnoreCase("null")) {
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.like("details", searchdata, MatchMode.ANYWHERE))
//			  .add(Restrictions.like("serviceRequestTitle", searchdata, MatchMode.ANYWHERE));
//			  criteria.add(or);
//		}
//		ServiceRequestDetails serviceRequestDetails = new ServiceRequestDetails();
//		serviceRequestDetails.setCount(criteria.list().size());
//		serviceRequestDetails.setMessage("List of service Resquest");
//		serviceRequestDetails.setRequestorServiceRequest((List<ServiceRequest>) criteria.list());
//		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
//		criteria.setMaxResults(end);	
//		return serviceRequestDetails;
//	}

	@Override
	public ServiceRequest getElementById(int UID) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(ServiceRequest.class);
		cr.add(Restrictions.eq("serviceRequestId", UID));
		cr.add(Restrictions.eq("isSoftDelete",0));
		List results = cr.list();
		ServiceRequest serviceRequest = (ServiceRequest) cr.uniqueResult();
		return serviceRequest;
	}

	@SuppressWarnings("null")
	@Override
	public Map<String , String> getServiceRequestbyId(int UID) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " s.sr_id,s.comment,s.assigned_to, s.details, s.raised_on,"
				+ " s.remarks, s.requestor_id, s.sr_closedon, s.sr_title, "
				+ "if(s.status='I','In Progress', if(s.status='R','Rejected',if(s.status='P','Re-open',"
				+ " if(s.status='C', 'Closed','Open')))), "
				+ "u.email_id, u.first_name, u.last_name,u.dob, u.mobile_phone,"
				+ " u.crsp_add1,u.crsp_add2,u.crsp_add3,u.crsp_city,u.crsp_state,"
				+ "u.crsp_zip, s.details_attached, s.extension,"
				+ " if(s.sr_closedon>cast((now()) as date),'Active', 'Expired') "
				+ " FROM lutbl_srlog as s join lutbl_user_reg as u on"
				+ " s.assigned_to=u.user_id where s.is_soft_delete = '0' and s.sr_id='"+UID+"'");
		Object[] row = (Object[]) query.uniqueResult();
		Map<String,String> hm = new HashMap<String, String>();	
		if (row != null) {		
				hm.put("ServiceRequestId", row[0].toString());
				hm.put("AssignedTo", row[1].toString());
				hm.put("RequestDetails", row[2].toString());
				hm.put("RaisedOn", row[3].toString());
				hm.put("Remarks", row[4].toString());
				hm.put("RequestorId", row[5].toString());
				hm.put("ClosedOn", row[6].toString());
				hm.put("ServiceRequestTitle", row[7].toString());
				hm.put("Status", row[8].toString());
				hm.put("AssignerEmailId", row[9].toString());
				hm.put("AssignerFirstName", row[10].toString());
				hm.put("AssignerLastName", row[11].toString());
				hm.put("AssignerDOB", row[12].toString());
				hm.put("AssignerMobileNo", row[13].toString());
				hm.put("AssignerAddress1", row[14].toString());
				hm.put("AssignerAddress2", row[15].toString());
				hm.put("AssignerAddress3", row[16].toString());
				hm.put("AssignerCity", row[17].toString());
				hm.put("AssignerState", row[18].toString());
				hm.put("AssignerZip", row[19].toString());
				hm.put("DetailsAttached", row[20].toString());
				hm.put("Extension", row[21].toString());
				hm.put("Active", row[22].toString());
		}
		return hm;
	}

}
