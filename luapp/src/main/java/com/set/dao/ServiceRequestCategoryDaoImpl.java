package com.set.dao;

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

import com.set.model.AssignmentMaster;
import com.set.model.DrawingListDetails;
import com.set.model.NotesMaster;
import com.set.model.ServiceRequestCategory;
import com.set.model.ServiceRequestCategoryDetails;
import com.set.model.ServiceRequestSubCategoryDetails;
import com.set.model.SubjectMaster;
import com.set.model.SubjectResource;
@Transactional
@Repository
public class ServiceRequestCategoryDaoImpl implements ServiceRequestCategoryDao {
	@Autowired
	private SessionFactory sessionFactory; 
	@Override
	public void save(ServiceRequestCategory serviceRequestCategory) {
		sessionFactory.getCurrentSession().saveOrUpdate(serviceRequestCategory);
	}

	@Override
	public ServiceRequestCategoryDetails list(String category) {
//		int maxPageData=10;
//		int start = pagenumber * maxPageData - maxPageData;
//		int end =10;
//		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceRequestCategory.class);
		if(category.equals("M")) {
		    criteria.add(Restrictions.eq("categoryType","M"));
		    criteria.add(Restrictions.eq("isSoftDelete",0));
		}else {
			criteria.add(Restrictions.eq("parentCategory",category));
		    criteria.add(Restrictions.eq("isSoftDelete",0));
		}
		ServiceRequestCategoryDetails serviceRequestCategoryDetails = new ServiceRequestCategoryDetails();
		serviceRequestCategoryDetails.setCount(criteria.list().size());
		if(criteria.list().size()>0) {
			serviceRequestCategoryDetails.setMessage("List of Sub Category");
		}else {
			serviceRequestCategoryDetails.setMessage("Sub Category Record not found");				
		}
		serviceRequestCategoryDetails.setServiceRequestCategory((List<ServiceRequestCategory>) criteria.list());
//		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
//		criteria.setMaxResults(end);	
		return serviceRequestCategoryDetails;
	}


	@Override
	public void update(ServiceRequestCategory serviceRequestCategory) {
		sessionFactory.getCurrentSession().update(serviceRequestCategory);	
	}

	@Override
	public void deleteElementById(ServiceRequestCategory serviceRequestCategory) {
		sessionFactory.getCurrentSession().update(serviceRequestCategory);
		
	}

	@Override
	public boolean IsExist(String details, String categoryId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ServiceRequestCategory.class);
		 cr.add(Restrictions.eq("description",details));
		 cr.add(Restrictions.eq("isSoftDelete",0));
		int strlen=categoryId.length();
		if (strlen>0) {
			cr.add(Restrictions.not(Restrictions.eq("categoryId", categoryId)));
		}
		int results = cr.list().size();
		System.out.println("results=" + results);
		if (results > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean SubCategoryIsExist(String details, String categoryId, String subCategoryId) {
		Criteria cr= sessionFactory.getCurrentSession()
				.createCriteria(ServiceRequestCategory.class);
		 cr.add(Restrictions.eq("description",details));
		 cr.add(Restrictions.eq("parentCategory",categoryId));
		 cr.add(Restrictions.eq("isSoftDelete",0));
		int strlen=categoryId.length();
		if (strlen>0) {
			cr.add(Restrictions.not(Restrictions.eq("categoryId", subCategoryId)));
		}
		int results = cr.list().size();
		System.out.println("results=" + results);
		if (results > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int gettotalcount() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceRequestCategory.class);
		//criteria.add(Restrictions.eq("IsDeleted",false));
		int totalNumbers=criteria.list().size();
		return totalNumbers;
	}

	@Override
	public ServiceRequestCategory getCategoryById(String CID) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(ServiceRequestCategory.class);
		cr.add(Restrictions.eq("categoryId", CID));
	    cr.add(Restrictions.eq("isSoftDelete",0));
		List results = cr.list();
		ServiceRequestCategory serviceRequestCategory = (ServiceRequestCategory) cr.uniqueResult();
		return serviceRequestCategory;
	}


	@Override
	public ServiceRequestCategoryDetails getListCategory(String category, int pageNumber, String searchData) {
		int maxPageData=10;
		int start = pageNumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		ServiceRequestCategoryDetails serviceRequestCategoryDetails = new ServiceRequestCategoryDetails();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceRequestCategory.class);
		if(category.equals("M")) {
		    criteria.add(Restrictions.eq("categoryType","M"));
		    criteria.add(Restrictions.eq("isSoftDelete",0));
			if (!searchData.equalsIgnoreCase("null")) {
				String matchString = "%" + searchData + "%";
				Disjunction or = Restrictions.disjunction();
				or.add(Restrictions.like("description", matchString, MatchMode.ANYWHERE));
				criteria.add(or);
			}
			serviceRequestCategoryDetails.setCount(criteria.list().size());
			if(criteria.list().size()>0) {
				serviceRequestCategoryDetails.setMessage("List of Category");
			}else {
				serviceRequestCategoryDetails.setMessage("Category Record not found");				
			}
			serviceRequestCategoryDetails.setServiceRequestCategory((List<ServiceRequestCategory>) criteria.list());
			criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
			criteria.setMaxResults(end);	
		}
		return serviceRequestCategoryDetails;
	}


	@Override
	public ServiceRequestSubCategoryDetails getListSubCategory(String category, int pageNumber, String searchData) {
		ServiceRequestSubCategoryDetails serviceRequestSubCategoryDetails = new ServiceRequestSubCategoryDetails();
		String searchString="";
		if(category.equals("S")) {
			    searchString+= " and s.category_type = '"+category+"'";
			if (!searchData.equalsIgnoreCase("null")) {
				
				searchString+=" and s.resolve_days LIKE '%"+searchData + "%' or u.email_id LIKE '%" + searchData + "%' or c.description LIKE '%" + searchData + "%' or s.description LIKE '%" + searchData
					 + "%'";
			}
			SQLQuery query = sessionFactory.getCurrentSession()
					.createSQLQuery("SELECT any_value(s.category_id),any_value(s.assigned_to),any_value(u.email_id),any_value(u.first_name),any_value(u.mid_name),any_value(u.last_name),"
							+ " any_value(c.description) as category,any_value(s.description) as subCategory, any_value(s.resolve_days) FROM "
							+ "lutbl_srcategory s INNER JOIN lutbl_srcategory c ON c.category_id=s.parent_category"
							+ " join lutbl_user_reg u ON u.user_id=s.assigned_to WHERE s.category_type != 'M' and "
							+ "c.is_soft_delete='0' and s.is_soft_delete='0'" + searchString +" group by s.category_id");
			System.out.println("query=" + query);
			List<Object[]> totalrows = query.list();
			int totalNumbers = totalrows.size();
			int maxPageData = 10;
			int start = pageNumber * maxPageData - maxPageData;
			int end = 10;
			query.setFirstResult(start);
			query.setMaxResults(end);
			List<Object[]> rows = query.list();
			List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
			for (Object[] row : rows) {
				Map<String, String> hm = new HashMap<String, String>();
				if (null != row[0]) {
					hm.put("SubCategoryId", row[0].toString());
				} else {
					hm.put("SubCategoryId", "");
				}
				if (null != row[1]) {
					hm.put("AssignedTo", row[1].toString());
				} else {
					hm.put("AssignedTo", "");
				}
				if (null != row[2]) {
					hm.put("AssignedEmailId", row[2].toString());
				} else {
					hm.put("AssignedEmailId", "");
				}
				if (null != row[3]) {
					hm.put("FirstName", row[3].toString());
				} else {
					hm.put("FirstName", "");
				}
				if (null != row[4]) {
					hm.put("MiddleName", row[4].toString());
				} else {
					hm.put("MiddleName", "");
				}
				if (null != row[5]) {
					hm.put("LastName", row[5].toString());
				} else {
					hm.put("LastName", "");
				}
				if (null != row[6]) {
					hm.put("CategoryDescription", row[6].toString());
				} else {
					hm.put("CategoryDescription", "");
				}
				if (null != row[7]) {
					hm.put("SubCategoryDescription", row[7].toString());
				} else {
					hm.put("SubCategoryDescription", "");
				}
				if (null != row[8]) {
					hm.put("RequiredDays", row[8].toString());
				} else {
					hm.put("RequiredDays", "");
				}
				arrayList.add(hm);
			}
			System.out.println("totalNumbers="+totalNumbers);
			System.out.println("array list size="+arrayList.size());
			serviceRequestSubCategoryDetails.setCount(totalNumbers);
			serviceRequestSubCategoryDetails.setServiceRequestCategory(arrayList);
			if(totalNumbers>0) {
				serviceRequestSubCategoryDetails.setMessage("List of Sub Category");
			}else {
				serviceRequestSubCategoryDetails.setMessage("Sub Category Record not found");
			}
		}
		return serviceRequestSubCategoryDetails;		
	}

}
