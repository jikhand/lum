package com.set.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.Country;
import com.set.model.CountryDetails;
import com.set.model.State;
import com.set.model.StateDetails;
@Repository
@Transactional
public class StateDaoImp implements StateDao {
	@Autowired
    SessionFactory sessionFactory;
	@Override
	public StateDetails getAllState(int pagenumber,String searchdata) {
		System.out.println("searchdata: "+searchdata);
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(State.class,"s"); 
//		criteria.add(Restrictions.eq("s.isDeleted",0));
//		return (List<State>) criteria.list();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(State.class,"state");
		criteria.add(Restrictions.eq("isDeleted",0));
		if (!searchdata.equalsIgnoreCase("null")) {
			criteria.createAlias("state.country","country");
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("country.countryName", searchdata, MatchMode.ANYWHERE))
			.add(Restrictions.like("stateName", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		StateDetails stateDetails = new StateDetails();
		System.out.println("total number of record="+criteria.list().size());
		stateDetails.setCount(criteria.list().size());
		stateDetails.setState((List<State>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);	
		return stateDetails;
	}

	@Override
	public State getStateByStateId(Long stateId) {
		System.out.println("stateId="+stateId);
		return sessionFactory.getCurrentSession().get(State.class, stateId);
	}

	@Override
	public void save(State state) {
		sessionFactory.getCurrentSession().saveOrUpdate(state);
		
	}

	@Override
	public void deleteState(long id) {
		System.out.println("id: "+id);
		State state=this.getStateByStateId(id);
		state.setIsDeleted(1);
		sessionFactory.getCurrentSession().update(state);
	}

	@Override
	public List<State> getAllStateSelect(long countryId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(State.class,"s"); 
		if(Long.valueOf(countryId)!=0) {
			System.out.println("in condition countryId=="+countryId);
			Country country=new Country();
			country.setCountryId(countryId);
			criteria.add(Restrictions.eq("country",country));
		}
		return (List<State>) criteria.list();
	}
	@Override
	public boolean IsExist(String stateName, long countryId) {
		Country country=new Country();
		country.setCountryId(countryId);
		System.out.println("stateName: "+stateName);
		System.out.println("countryId: "+countryId);
		Criteria cr= sessionFactory.getCurrentSession().createCriteria(State.class);
         cr.add(Restrictions.eq("stateName",stateName));
         cr.add(Restrictions.eq("country",country));
         cr.add(Restrictions.eq("isDeleted",0));
         int results = cr.list().size();
         System.out.println("cr.list(): "+cr.list());
         System.out.println("results: "+results);
        if(results>0) {
            return true;
        }else {
            return false;   
        }
	}

}