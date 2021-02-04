package com.set.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.DrawingCategory;
import com.set.model.DrawingCategoryDetails;
@SuppressWarnings("deprecation")

@Transactional
@Repository
public class DrawingCategoryDaoImpl implements DrawingCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("DrawingCategoryImp.class");

	@Override
	public void save(DrawingCategory drawingCategory) {
		sessionFactory.getCurrentSession().save(drawingCategory);
	}

	@Override
	public DrawingCategoryDetails getAllDrawingCategory(int pagenumber, String searchdata) {
		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(Country.class);
		// criteria.add(Restrictions.eq("isDeleted",0));
		// return (List<Country>) criteria.list();
		int maxPageData = 10;
		int end = 10;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		criteria.add(Restrictions.eq("isDeleted", false));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("drawingCategoryName", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		DrawingCategoryDetails drawingCategoryDetails = new DrawingCategoryDetails();
		logger.info("total number of record=" + criteria.list().size());
		drawingCategoryDetails.setCount(criteria.list().size());
		drawingCategoryDetails.setDrawingCategory((List<DrawingCategory>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		return drawingCategoryDetails;
	}

	
	@Override
	public List<DrawingCategory> getAllDrawingCategorySelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		criteria.add(Restrictions.eq("isDeleted", false));
		return (List<DrawingCategory>) criteria.list();
	}

	@Override
	public DrawingCategory getDrawingCategoryById(String drawingCategoryId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		cr.add(Restrictions.eq("drawingCategoryName", drawingCategoryId));
		DrawingCategory drawingCategory = (DrawingCategory) cr.uniqueResult();
		return drawingCategory;
	}

	@Override
	public void updateDrawingCategory(DrawingCategory drawingCategory) {
		sessionFactory.getCurrentSession().update(drawingCategory);
	}

	@Override
	public void deleteDrawingCategory(DrawingCategory drawingCategory) {
		sessionFactory.getCurrentSession().update(drawingCategory);
	}

	@Override
	public boolean IsExist(String drawingCategory) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		cr.add(Restrictions.eq("drawingCategoryName", drawingCategory));
		int results = cr.list().size();
		if (results > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int totalDrawingCategory() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		int totalNumbers = criteria.list().size();
		return totalNumbers;
	}

	@Override
	public DrawingCategory getDrawingCategoryByName(String categoryName) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(DrawingCategory.class);
		cr.add(Restrictions.eq("drawingCategoryName", categoryName));
		DrawingCategory drawingCategory = (DrawingCategory) cr.uniqueResult();
		return drawingCategory;
	}

}
