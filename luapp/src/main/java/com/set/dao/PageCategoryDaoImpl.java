package com.set.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.Drawing;
import com.set.model.PageCategory;
import com.set.model.PageCategoryDetails;
import com.set.model.UserRegistration;

@Transactional
@Repository
public class PageCategoryDaoImpl implements PageCategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("PageCategoryDaoImpl.class");

	@Override
	public void save(PageCategory pageCategory) {
		sessionFactory.getCurrentSession().save(pageCategory);
	}

	@Override
	public void editPageCategory(PageCategory pageCategory) {
		sessionFactory.getCurrentSession().update(pageCategory);
	}

	@Override
	public void deletePageCategory(PageCategory pageCategory) {
		sessionFactory.getCurrentSession().delete(pageCategory);
	}

	@Override
	public PageCategoryDetails listPageCategory() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PageCategory.class);
		PageCategoryDetails pageCategoryDetails = new PageCategoryDetails();

		logger.info("total number of record=" + criteria.list().size());

		pageCategoryDetails.setCount(criteria.list().size());
		pageCategoryDetails.setMessage("Page Category List");
		pageCategoryDetails.setPageCategoryDetails((List<PageCategory>) criteria.list());

		return pageCategoryDetails;
	}

	@Override
	public PageCategory getPageCategoryById(String pageCategoryId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PageCategory.class);
		criteria.add(Restrictions.eq("pageCategoryId",Integer.parseInt(pageCategoryId)));
		PageCategory pageCategory= (PageCategory)criteria.uniqueResult();
		return pageCategory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PageCategory> listPageCategorySelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PageCategory.class); 
		//criteria.add(Restrictions.eq("isDeleted",0));
		return (List<PageCategory>) criteria.list();
	}
}