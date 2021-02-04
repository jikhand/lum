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
import com.set.model.City;
import com.set.model.CityDetails;
import com.set.model.State;
@Repository
@Transactional
public class CityDaoImp implements CityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CityDetails getAllCityList(int pagenumber,String searchdata) {
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class,"city");
		criteria.add(Restrictions.eq("isDeleted",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			criteria.createAlias("city.stateMaster","state");
			criteria.createAlias("state.country","country");
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("cityName", searchdata, MatchMode.ANYWHERE))
			  .add(Restrictions.like("state.stateName", searchdata, MatchMode.ANYWHERE)).
			   add(Restrictions.like("country.countryName", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		CityDetails cityDetails = new CityDetails();
		System.out.println("total number of record="+criteria.list().size());
		cityDetails.setCount(criteria.list().size());
		cityDetails.setCity((List<City>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return cityDetails;
	}

	@Override
	public void saveCityMaster(City city) {
		sessionFactory.getCurrentSession().saveOrUpdate(city);
	}

	@Override
	public City getCityMasterDto(long cityId) {
		System.out.println("cityId: "+cityId);
		return sessionFactory.getCurrentSession().get(City.class, cityId);
	}

	@Override
	public void deleteCity(long id) {
		City city=this.getCityMasterDto(id);
		//city.setIsDeleted(1);
		sessionFactory.getCurrentSession().update(city);
		
	}

	@Override
	public boolean IsExist(String cityName, long stateId) {
		State state=new State();
		state.setStateId(stateId);
		Criteria cr= sessionFactory.getCurrentSession().createCriteria(City.class);
         cr.add(Restrictions.eq("cityName",cityName));
         cr.add(Restrictions.eq("stateMaster",state));
         cr.add(Restrictions.eq("isDeleted",0));
         int results = cr.list().size();
        if(results>0) {
            return true;
        }else {
            return false;   
        }
	}

	@Override
	public List<Map<String , String>> getAllCitySelect(long stateid) {
String strQuery="select city_name,city_id from lutbl_city where state_id="+stateid;
SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(strQuery);
List<Object[]> totalrows = query.list();
List<Object[]> rows = query.list();
List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
for(Object[] row : rows){
	Map<String,String> hm = new HashMap<String,String>();
	if(null!=row[0]) {
		hm.put("city_name", row[0].toString());
	}else {
		hm.put("city_name", "");
	}
	if(null!=row[1]) {
		hm.put("city_id", row[1].toString());
	}else {
		hm.put("city_id", "");
	}
	arrayList.add(hm);
	}
	return arrayList;
}
}
