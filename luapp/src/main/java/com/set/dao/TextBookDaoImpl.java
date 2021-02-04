package com.set.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.TextBookListDto;
import com.set.model.SportsActivity;
import com.set.model.SportsActivityDetails;
import com.set.model.TextBookDetails;
import com.set.model.TextBookMaster;

@Transactional
@Repository
public class TextBookDaoImpl implements TextBookDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("TextBookDaoImpl.class");
	@Override
	public void save(TextBookMaster textBookMaster) {
		sessionFactory.getCurrentSession().save(textBookMaster);
	}
	
    //GetTextBookDataByClassId
@Override
public TextBookListDto getAllTextBooksByClassId(String classId) {
Query query = sessionFactory.getCurrentSession().createQuery("SELECT distinct tbm.pdfUrl, tbm.stockBorrowQuantity, tbm.bookName, tbm.subCatogeryName,"
+ " tbm.catogeryName, tbm.bookAuthor, tbm.bookIsbn, tbm.bookPublisher, tbm.bookPublishYear, tbm.subCategoryId, tbm.bookType,"
+ " tbm.bookLength, tbm.bookCoverImage, tbm.bookBarcodeImage, tbm.isDeleted, tbm.createdAt, tbm.categoryId, tbm.status"
+ " FROM TextBookMaster tbm, ClassSubject cs"
+ " WHERE tbm.textBookMasterId.subjectId=cs.classSubjectId.subjectId and cs.classSubjectId.classId='"+classId+"'");
List<Object[]> listOfTextBooks = query.list(); 
TextBookListDto textBookListDto = new TextBookListDto();
List<TextBookMaster> textBookList= new ArrayList<>();
TextBookMaster textBookDetails=null;

for (Object[] objArr : listOfTextBooks) {
textBookDetails = new TextBookMaster();
textBookDetails.setPdfUrl((String)objArr[0]);
textBookDetails.setStockBorrowQuantity((String)objArr[1]);
textBookDetails.setBookName((String)objArr[2]);
textBookDetails.setSubCatogeryName((String)objArr[3]);
textBookDetails.setCatogeryName((String)objArr[4]);
textBookDetails.setBookAuthor((String)objArr[5]);
textBookDetails.setBookIsbn((String)objArr[6]);
textBookDetails.setBookPublisher((String)objArr[7]);
textBookDetails.setBookPublishYear((String)objArr[8]);
textBookDetails.setSubCategoryId((String)objArr[9]);
textBookDetails.setBookType((String)objArr[10]);
textBookDetails.setBookLength((int)objArr[11]);
textBookDetails.setBookCoverImage((String)objArr[12]);
textBookDetails.setBookBarcodeImage((String)objArr[13]);
textBookDetails.setIsDeleted((int)objArr[14]);
textBookDetails.setCreatedAt((Date)objArr[15]);
textBookDetails.setCategoryId((String)objArr[16]);
textBookDetails.setStatus((String)objArr[17]); 
textBookList.add(textBookDetails);
}
textBookListDto.setCount(textBookList.size());
textBookListDto.setCode("200");
textBookListDto.setBookData(textBookList);
textBookListDto.setMessage("Book List");
return textBookListDto; 
}
//GetTextBookDataByClassIdSectionId
@Override
public TextBookListDto getAllTextBooksByClassIdSectionId(String classId, String sectionId) {
Query query = sessionFactory.getCurrentSession().createQuery("SELECT distinct tbm.pdfUrl, tbm.stockBorrowQuantity, tbm.bookName, tbm.subCatogeryName,"
+ " tbm.catogeryName, tbm.bookAuthor, tbm.bookIsbn, tbm.bookPublisher, tbm.bookPublishYear, tbm.subCategoryId, tbm.bookType,"
+ " tbm.bookLength, tbm.bookCoverImage, tbm.bookBarcodeImage, tbm.isDeleted, tbm.createdAt, tbm.categoryId, tbm.status, tbm.pdfUrl"
+ " FROM TextBookMaster tbm, ClassSubject cs"
+ " WHERE tbm.textBookMasterId.subjectId=cs.classSubjectId.subjectId"
+ " and cs.classSubjectId.classId='"+classId+"' and cs.classSubjectId.sectionId='"+sectionId+"'");
List<Object[]> listOfTextBooks = query.list(); 
TextBookListDto textBookListDto = new TextBookListDto();
List<TextBookMaster> textBookList= new ArrayList<>();
TextBookMaster textBookDetails=null;

for (Object[] objArr : listOfTextBooks) {
textBookDetails = new TextBookMaster();
textBookDetails.setPdfUrl((String)objArr[0]);
textBookDetails.setStockBorrowQuantity((String)objArr[1]);
textBookDetails.setBookName((String)objArr[2]);
textBookDetails.setSubCatogeryName((String)objArr[3]);
textBookDetails.setCatogeryName((String)objArr[4]);
textBookDetails.setBookAuthor((String)objArr[5]);
textBookDetails.setBookIsbn((String)objArr[6]);
textBookDetails.setBookPublisher((String)objArr[7]);
textBookDetails.setBookPublishYear((String)objArr[8]);
textBookDetails.setSubCategoryId((String)objArr[9]);
textBookDetails.setBookType((String)objArr[10]);
textBookDetails.setBookLength((int)objArr[11]);
textBookDetails.setBookCoverImage((String)objArr[12]);
textBookDetails.setBookBarcodeImage((String)objArr[13]);
textBookDetails.setIsDeleted((int)objArr[14]);
textBookDetails.setCreatedAt((Date)objArr[15]);
textBookDetails.setCategoryId((String)objArr[16]);
textBookDetails.setStatus((String)objArr[17]); 
textBookList.add(textBookDetails); 
}
textBookListDto.setCount(textBookList.size());
textBookListDto.setCode("200");
textBookListDto.setBookData(textBookList);
textBookListDto.setMessage("Book List");
return textBookListDto; 
}
	
	@Override
	public TextBookDetails textBookSearch(int pageNumber, String searchData) {
		int maxPageData=10;
		int start = pageNumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TextBookMaster.class);		
		if (!searchData.equalsIgnoreCase("null")) {
			String matchString = "%"+searchData+"%";			
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("bookName", matchString, MatchMode.ANYWHERE))
			.add(Restrictions.like("bookAuthor", matchString, MatchMode.ANYWHERE));
		//	.add(Restrictions.like("bookType", matchString, MatchMode.ANYWHERE))
			//.add(Restrictions.like("categoryId", matchString, MatchMode.ANYWHERE))
			//.add(Restrictions.like("subCatogeryName", matchString, MatchMode.ANYWHERE))
			//.add(Restrictions.like("bookIsbn", matchString, MatchMode.ANYWHERE))
			//.add(Restrictions.like("bookPublisher", matchString, MatchMode.ANYWHERE));			
			criteria.add(or);
		}
		TextBookDetails textBookDetails = new TextBookDetails();
		logger.info("total number of record="+criteria.list().size());
		textBookDetails.setCount(criteria.list().size());
		textBookDetails.setTextBooks((List<TextBookMaster>) criteria.list());
		criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);		
		return textBookDetails;
	}

	@Override
	public void update(TextBookMaster textBookMaster) {
		sessionFactory.getCurrentSession().update(textBookMaster);
	}
	
    //GetTextBookDataByClassId
@Override
public TextBookListDto getAllTextBooksByTeacherId(String teacherId) {
Query query = sessionFactory.getCurrentSession().createQuery("SELECT distinct tbm.pdfUrl, tbm.stockBorrowQuantity, tbm.bookName, tbm.subCatogeryName,"
+ " tbm.catogeryName, tbm.bookAuthor, tbm.bookIsbn, tbm.bookPublisher, tbm.bookPublishYear, tbm.subCategoryId, tbm.bookType,"
+ " tbm.bookLength, tbm.bookCoverImage, tbm.bookBarcodeImage, tbm.isDeleted, tbm.createdAt, tbm.categoryId, tbm.status"
+ " FROM TextBookMaster tbm, ClassSubject cs"
+ " WHERE tbm.textBookMasterId.subjectId=cs.classSubjectId.subjectId and cs.classSubjectId.teacherId='"+teacherId+"'");
List<Object[]> listOfTextBooks = query.list(); 
TextBookListDto textBookListDto = new TextBookListDto();
List<TextBookMaster> textBookList= new ArrayList<>();
TextBookMaster textBookDetails=null;

for (Object[] objArr : listOfTextBooks) {
textBookDetails = new TextBookMaster();
textBookDetails.setPdfUrl((String)objArr[0]);
textBookDetails.setStockBorrowQuantity((String)objArr[1]);
textBookDetails.setBookName((String)objArr[2]);
textBookDetails.setSubCatogeryName((String)objArr[3]);
textBookDetails.setCatogeryName((String)objArr[4]);
textBookDetails.setBookAuthor((String)objArr[5]);
textBookDetails.setBookIsbn((String)objArr[6]);
textBookDetails.setBookPublisher((String)objArr[7]);
textBookDetails.setBookPublishYear((String)objArr[8]);
textBookDetails.setSubCategoryId((String)objArr[9]);
textBookDetails.setBookType((String)objArr[10]);
textBookDetails.setBookLength((int)objArr[11]);
textBookDetails.setBookCoverImage((String)objArr[12]);
textBookDetails.setBookBarcodeImage((String)objArr[13]);
textBookDetails.setIsDeleted((int)objArr[14]);
textBookDetails.setCreatedAt((Date)objArr[15]);
textBookDetails.setCategoryId((String)objArr[16]);
textBookDetails.setStatus((String)objArr[17]); 
textBookList.add(textBookDetails);
}
textBookListDto.setCount(textBookList.size());
textBookListDto.setCode("200");
textBookListDto.setBookData(textBookList);
textBookListDto.setMessage("Book List");
return textBookListDto; 
}	
}