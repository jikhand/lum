package com.set.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.set.model.Country;
import com.set.model.CountryDetails;
@Repository
@Transactional
public class CountryDaoImp implements CountryDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void saveCountryData(Country country) {
		sessionFactory.getCurrentSession().save(country);
	}
	@Override
	public void editCountryData(Country country) {
		sessionFactory.getCurrentSession().saveOrUpdate(country);
	}
	@Override
	public void deleteCountryData(long countryId) {
		Country country=this.getCountryData(countryId);
		//country.setIsDeleted(1);
		sessionFactory.getCurrentSession().update(country);
	}
	@Override
	public Country getCountryData(long countryId) {
		return sessionFactory.getCurrentSession().get(Country.class, countryId);
	}
	@Override
	public CountryDetails getAllCountry(int pagenumber,String searchdata) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
//		criteria.add(Restrictions.eq("isDeleted",0));
//		return (List<Country>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class);
		criteria.add(Restrictions.eq("isDeleted",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			criteria.add(Restrictions.like("countryName", searchdata, MatchMode.ANYWHERE));
		}
		CountryDetails countryDetails = new CountryDetails();
		System.out.println("total number of record="+criteria.list().size());
		countryDetails.setCount(criteria.list().size());
		countryDetails.setCountry((List<Country>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return countryDetails;
		
	}
	@Override
	public List<Country> getAllCountrySelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Country.class); 
		//criteria.add(Restrictions.eq("isDeleted",0));
		return (List<Country>) criteria.list();
	}
	@Override
	public boolean IsExist(String countryName) {
		Criteria cr= sessionFactory.getCurrentSession()
                .createCriteria(Country.class);
         cr.add(Restrictions.eq("countryName",countryName));
         cr.add(Restrictions.eq("isDeleted",0));
         int results = cr.list().size();
        if(results>0) {
            return true;
        }else {
            return false;   
        }
	}

}