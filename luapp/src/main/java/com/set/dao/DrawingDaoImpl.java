package com.set.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.DrawingDetailsDto;
import com.set.dto.DrawingDto;
import com.set.model.ClassSubject;
import com.set.model.ClassSubjectId;
import com.set.model.Drawing;
import com.set.model.DrawingCategory;
import com.set.model.DrawingDetails;
import com.set.model.DrawingListDetails;

@Transactional
@Repository
public class DrawingDaoImpl implements DrawingDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("DrawingImp.class");

	@Override
	public void save(Drawing drawing) {
		sessionFactory.getCurrentSession().save(drawing);
	}

	@Override
	public DrawingDetails getAllDrawing(int pagenumber, String searchdata) {
		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(Country.class);
		// criteria.add(Restrictions.eq("isDeleted",0));
		// return (List<Country>) criteria.list();
		 System.out.println("searchdata="+searchdata);
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Drawing.class);
		criteria.add(Restrictions.eq("isDeleted", false));
		if (!searchdata.equalsIgnoreCase("null")) {
			Disjunction or = Restrictions.disjunction();
			 System.out.println("searchdata="+searchdata);
			// or.add(Restrictions.like("drawingCategory", searchdata, MatchMode.ANYWHERE))
			// .add(Restrictions.like("drawingCode", searchdata, MatchMode.ANYWHERE))
			or.add(Restrictions.like("drawingName", searchdata, MatchMode.ANYWHERE));
			// .add(Restrictions.like("drawingType", searchdata, MatchMode.ANYWHERE));
			criteria.add(or);
		}
		DrawingDetails drawingDetails = new DrawingDetails();
		logger.info("total number of record=" + criteria.list().size());
		drawingDetails.setCount(criteria.list().size());
		drawingDetails.setDrawing((List<Drawing>) criteria.list());
		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		return drawingDetails;
	}

	@Override
	public DrawingListDetails getAllStudentDrawingList(int pagenumber, String searchdata, String studentId,
			String teacherId) {
		String searchString = "";
		String joinString = "";
		DrawingListDetails drawingListDetails=new DrawingListDetails();
		if (null != studentId) {
			searchString = " and d.student_id ='" + studentId + "' ";
		}
		if (null != teacherId && teacherId != "") {
			//String[] splitstr = TeacherClassSection.split("StRST");
			joinString = "JOIN lutbl_class_subj as cs on cs.class_id=d.class_id and cs.section_id=d.section_id join lutbl_user_reg as ur on ur.user_id=cs.teacher_id";
			searchString="and cs.teacher_id='"+teacherId+"'";
		}
		if (!searchdata.equalsIgnoreCase("null") && searchdata!=" ") {
		 searchString+= " and d.drawing_name LIKE '%" + searchdata + "%' or c.drawing_category_name LIKE '%" + searchdata
		 + "%'";
		 }
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT" + " d.drawing_id, d.class_id, d.drawing_category, "
						+ "d.drawing_name, d.image_url, d.section_id, "
						+ "d.student_id, c.drawing_category_name,d.created_at  FROM lutbl_drawing as d "
						+ "join lutbl_drawing_category as c on c.drawing_category_id=d.drawing_category "+joinString
						+ " where d.is_deleted='0' and c.is_deleted='0' " + searchString + " GROUP BY d.drawing_id");
		System.out.println("query=" + query);
		List<Object[]> totalrows = query.list();
		int totalNumbers = totalrows.size();
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("DrawingId", row[0].toString());
			} else {
				hm.put("DrawingId", "");
			}

			if (null != row[1]) {
				hm.put("ClassId", row[1].toString());
			} else {
				hm.put("ClassId", "");
			}
			if (null != row[2]) {
				hm.put("DrawingCategory", row[2].toString());
			} else {
				hm.put("DrawingCategory", row[2].toString());
			}
			if (null != row[3]) {
				hm.put("DrawingName", row[3].toString());
			} else {
				hm.put("DrawingName", "");
			}
			if (null != row[4]) {
				hm.put("DrawingUrl", row[4].toString());
			} else {
				hm.put("DrawingUrl", "");
			}
			if (null != row[5]) {
				hm.put("SectionId", row[5].toString());
			} else {
				hm.put("SectionId", "");
			}
			if (null != row[6]) {
				hm.put("StudentId", row[6].toString());
			} else {
				hm.put("StudentId", "");
			}
			if (null != row[7]) {
				hm.put("DrawingCategoryName", row[7].toString());
			} else {
				hm.put("DrawingCategoryName", "");
			}
			if (null != row[8]) {
				hm.put("created",row[8].toString());
			} else {
				hm.put("created", "");
			}
			arrayList.add(hm);
		}
		System.out.println("totalNumbers="+totalNumbers);
		System.out.println("array list size="+arrayList.size());
		drawingListDetails.setDrawingList(arrayList);
		drawingListDetails.setCount(totalNumbers);
		return drawingListDetails;
	}

	@Override
	public List<Drawing> getAllDrawingSelect() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Drawing.class);
		criteria.add(Restrictions.eq("isDeleted", false));
		return (List<Drawing>) criteria.list();
	}

	@Override
	public Drawing getDrawingById(String drawingId) {
		return sessionFactory.getCurrentSession().get(Drawing.class, drawingId);
	}

	@Override
	public void updateDrawing(Drawing drawing) {
		sessionFactory.getCurrentSession().update(drawing);
	}

	@Override
	public void deleteDrawing(Drawing drawing) {
		sessionFactory.getCurrentSession().update(drawing);
	}

	@Override
	public boolean IsExist(String drawing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int totalDrawing() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Drawing.class);
		int totalNumbers = criteria.list().size();
		return totalNumbers;
	}

	@Override
	public List<Object[]> getAllStudentDrawings(String studentId) {
		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(Drawing.class);
		// criteria.add(Restrictions.eq("studentId", studentId));
		// return (List<Drawing>) criteria.list();
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT" + " d.drawing_id, d.class_id, d.drawing_category, "
						+ "d.drawing_name, d.image_url, d.section_id, "
						+ "d.student_id, c.drawing_category_name  FROM lutbl_drawing as d "
						+ "join lutbl_drawing_category as c on c.drawing_category_id=d.drawing_category "
						+ " where d.is_deleted='0' and c.is_deleted='0' and d.student_id = '" + studentId + "'");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllStudentDrawingCategoryName(String studentId, String userId) {
		// TODO Auto-generated method stub
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT " + "DISTINCT(d.drawing_category),c.drawing_category_name "
						+ "from lutbl_drawing as d join lutbl_drawing_category as c ON "
						+ "d.drawing_category = c.drawing_category_id join lutbl_class_subj "
						+ "as a on a.class_id=d.class_id and a.section_id=d.section_id" + " WHERE a.teacher_id='"
						+ userId + "'" + " and d.student_id='" + studentId + "' and d.is_deleted=0 and "
						+ " c.is_deleted=0");
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> getAllStudentDrawingCategory(String studentId, String userId, String drawingCategoryId) {
		// TODO Auto-generated method stub
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT" + " d.drawing_id, d.drawing_name, d.image_url, c.drawing_category_name, "
						+ "d.student_id, d.class_id, d.section_id from lutbl_drawing"
						+ " as d join lutbl_drawing_category as c ON d.drawing_category ="
						+ " c.drawing_category_id join lutbl_class_subj as a on "
						+ "a.class_id=d.class_id and a.section_id=d.section_id " + "WHERE a.teacher_id='" + userId
						+ "' and " + "d.drawing_category='" + drawingCategoryId + "' and " + "d.student_id='"
						+ studentId + "' and d.is_deleted=0 and c.is_deleted=0 group by d.drawing_id");
		return (List<Object[]>) query.list();
	}

	@Override
	public String getClassSubject(String UserId) {
		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(ClassSubject.class);
		// criteria.add(Restrictions.eq("ClassSubjectId.teacherId", new
		// ClassSubjectId("","","",UserId)));
		// ClassSubject classSubject = (ClassSubject) criteria.uniqueResult();
		String str = "SELECT class_id,section_id FROM lutbl_class_subj where teacher_id='" + UserId + "'";
		System.out.println(str);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str);
		String strData1 = "";
		String strData2 = "";
		List<Object[]> rows = query.list();
		
		if (rows.size() > 0) {
			for (Object[] row : rows) {
				if (null != row[0]) {
					if (strData1.length() > 0) {
						strData1 = (String) row[0];
					} 
					else {
						strData1 += "," + (String) row[0];
					}
				}
				if (null != row[1]) {
					if (strData2.length() > 0) {
						strData2 = (String) row[1];
					} else {
						strData2 += "," + (String) row[1];
					}
				}
				
			}
			return strData1+"StRST"+strData2;
		} else {
			return null;
		}
	}
}
