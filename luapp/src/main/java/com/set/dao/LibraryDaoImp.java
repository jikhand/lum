package com.set.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

//import javax.persistence.criteria.Expression;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.dto.LibraryDto;
import com.set.dto.LibraryRequestBooksListDto;
import com.set.dto.LibraryRequestDto;
import com.set.dto.TextBookListDto;
import com.set.model.LibraryBooks;
import com.set.model.LibraryDetails;
import com.set.model.LibraryRequest;
import com.set.model.LibraryRequestDetails;
import com.set.model.LibraryRequestId;
import com.set.model.TextBookMaster;
@Repository
public class LibraryDaoImp implements LibraryDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("LibraryBooks.class");
	
	@Override
	public void save(LibraryBooks libraryBooks) {
		sessionFactory.getCurrentSession().save(libraryBooks);
	}
	
	@Override
	public void editLibBooks(LibraryDto libraryDto,String userId) {	
		String bookId = libraryDto.getBookId();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
			LibraryBooks libBooks = sessionFactory.getCurrentSession().load(LibraryBooks.class, bookId);
			
			libBooks.setAuthor(libraryDto.getAuthor());			
			libBooks.setBookIsbn(libraryDto.getBookIsbn());
			libBooks.setBookPublisher(libraryDto.getBookPublisher());
			libBooks.setBookPublishYear(libraryDto.getBookPublishYear());
			libBooks.setCopiesAvailable(libraryDto.getCopiesAvailable());
			libBooks.setCopyType(libraryDto.getCopyType());
			libBooks.setEcopyPath(libraryDto.getEcopyPath());
			libBooks.setNoOfCopies(libraryDto.getNoOfCopies());
			libBooks.setPublisher(libraryDto.getPublisher());
			libBooks.setSubCategoryId(libraryDto.getSubCategoryId());
			libBooks.setTitle(libraryDto.getTitle());
			libBooks.setUpdatedBy(userId);
			libBooks.setUpdatedTime(timestamp);
			sessionFactory.getCurrentSession().update(libBooks);	
	}
	
	
	@Override
	public void deleteLibBooks(LibraryBooks libraryBooks) {
		sessionFactory.getCurrentSession().delete(libraryBooks);	
	}
	
	@Override
	public LibraryDetails listLibBooks(int pageNumber, String searchData) {
		int maxPageData=10;
		int start = pageNumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryBooks.class);
		//System.out.println("Search data: "+searchData);
		if (!searchData.equalsIgnoreCase("null")) {
			String matchString = "%"+searchData+"%";
			//System.out.println("matchString: "+matchString);
			
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("title", matchString, MatchMode.ANYWHERE))
			.add(Restrictions.like("author", matchString, MatchMode.ANYWHERE))
			.add(Restrictions.like("bookId", matchString, MatchMode.ANYWHERE));
		//	.add(Restrictions.like("publisher", matchString, MatchMode.ANYWHERE))
		//	.add(Restrictions.like("bookIsbn", matchString, MatchMode.ANYWHERE))
		//	.add(Restrictions.like("bookPublisher", matchString, MatchMode.ANYWHERE));
			
			criteria.add(or);
		}
		System.out.println("title +title");
		System.out.println("author +author");
		System.out.println("bookId +bookId");
		LibraryDetails libraryDetails = new LibraryDetails();
		logger.info("total number of record="+criteria.list().size());
		libraryDetails.setCount(criteria.list().size());
		libraryDetails.setLibraryBooks((List<LibraryBooks>) criteria.list());
		criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);		
		return libraryDetails;
	}	
	
	//	int maxPageData=10;
	//	int start = pageNumber * maxPageData - maxPageData;
	//	int end =10;
	//	String searchString="";
	//	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryBooks.class);
	//	//System.out.println("Search data: "+searchData);
	//	if (!searchData.equalsIgnoreCase("null")) {
	//		String matchString = "%"+searchData+"%";
			//System.out.println("matchString: "+matchString);
			
		//	Disjunction or = Restrictions.disjunction();
		//	or.add(Restrictions.like("title", matchString, MatchMode.ANYWHERE))
	//		.add(Restrictions.like("author", matchString, MatchMode.ANYWHERE))
		//	.add(Restrictions.like("bookId", matchString, MatchMode.ANYWHERE))
	//		.add(Restrictions.like("publisher", matchString, MatchMode.ANYWHERE))
	//		.add(Restrictions.like("bookIsbn", matchString, MatchMode.ANYWHERE))
	//		.add(Restrictions.like("bookPublisher", matchString, MatchMode.ANYWHERE));
			
	//		criteria.add(or);
	//	}
	//	LibraryDetails libraryDetails = new LibraryDetails();
	//	logger.info("total number of record="+criteria.list().size());
	//	libraryDetails.setCount(criteria.list().size());
	//	libraryDetails.setLibraryBooks((List<LibraryBooks>) criteria.list());
	//	criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
	//	criteria.setMaxResults(end);		
	//	return libraryDetails;
//	}	
	
	@Override
	public LibraryDetails listLibBooks() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryBooks.class);
		
		LibraryDetails libraryDetails = new LibraryDetails();
		
		logger.info("total number of record="+criteria.list().size());
		
		libraryDetails.setCount(criteria.list().size());
		libraryDetails.setLibraryBooks((List<LibraryBooks>) criteria.list());		
		return libraryDetails;
	}	
	
	//Lib Req started*************
	@Override
	public void saveLibraryRequest(LibraryRequest libraryRequest) {
		sessionFactory.getCurrentSession().save(libraryRequest);
	}
	
	@Override
	public void editLibRequest(LibraryRequestDto libraryRequestDto,String userId) {	
		String bookId= libraryRequestDto.getBookId();
		String studentId=libraryRequestDto.getStudentId();
		//System.out.println("bookId: "+bookId+"****Stu***"+studentId);
		
		LibraryRequestId libraryRequestId = new LibraryRequestId(bookId,studentId);
		//System.out.println("libraryRequestId: "+libraryRequestId);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		    LibraryRequest libraryRequest = sessionFactory.getCurrentSession().load(LibraryRequest.class, libraryRequestId);
			
		    libraryRequest.setBookIsbn(libraryRequestDto.getBookIsbn());
		    libraryRequest.setDuesCharged(libraryRequestDto.getDuesCharged());
		    libraryRequest.setDuesPaid(libraryRequestDto.getDuesPaid());
		    libraryRequest.setExpectedReturn(libraryRequestDto.getExpectedReturn());		    
		    libraryRequest.setIssuedOn(libraryRequestDto.getIssuedOn());
		    libraryRequest.setNoOfRenewals(libraryRequestDto.getNoOfRenewals());
		    libraryRequest.setRemarks(libraryRequestDto.getRemarks());
		    libraryRequest.setRenewedOn(libraryRequestDto.getRenewedOn());
		    libraryRequest.setRequestOn(libraryRequestDto.getRequestOn());
		    libraryRequest.setReturnOn(libraryRequestDto.getRequestOn());
		    libraryRequest.setTitle(libraryRequestDto.getTitle());
		    libraryRequest.setUpdatedBy(userId);
		    libraryRequest.setUpdatedTime(timestamp);
		    sessionFactory.getCurrentSession().update(libraryRequest);	
	}
	
	@Override
	public void deleteLibRequest(String bookId, String studentId) {		
		LibraryRequestId libraryRequestId = new LibraryRequestId(bookId,studentId);
		LibraryRequest libraryRequest = sessionFactory.getCurrentSession().load(LibraryRequest.class, libraryRequestId);
		sessionFactory.getCurrentSession().delete(libraryRequest);	
	}
	
	@Override
	public LibraryRequestDetails listLibBooksRequest(int pageNumber, String searchData) {
		int maxPageData=10;
		int start = pageNumber * maxPageData - maxPageData;
		int end =10;
		String searchString="";
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LibraryRequest.class);
		
		if (!searchData.equalsIgnoreCase("null")) {
			SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date matchString = null;
			try {
				matchString=formatter6.parse(searchData);
				
				System.out.println("matchString: "+matchString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  			
			
			Disjunction or = Restrictions.disjunction();
			or.add(Expression.ge("requestOn", matchString))
			.add(Expression.ge("issuedOn",matchString)); 			
			criteria.add(or);
		}
		LibraryRequestDetails libraryRequestDetails = new LibraryRequestDetails();
		//logger.info("total number of record="+criteria.list().size());
		libraryRequestDetails.setCount(criteria.list().size());
		libraryRequestDetails.setLibraryRequest((List<LibraryRequest>) criteria.list());
		criteria.setFirstResult(pageNumber * maxPageData - maxPageData);
		criteria.setMaxResults(end);
		
		return libraryRequestDetails;
	}
	
	@Override
	public LibraryRequestBooksListDto recentLibBooks(String id) {

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT a.book_id, a.title,"
				+ " a.student_id, a.book_isbn, a.issued_on, a.request_on, a.expected_return,"
				+ " a.dues_charged, a.dues_paid, a.no_of_renewals, a.renewed_on, a.remarks"
				+ " from lutbl_libreq a"
				+ " WHERE a.issued_on <= DATE_ADD(CURDATE(), INTERVAL 1 MONTH) and student_id = '"+id+"'");
		
		System.out.println("query: "+query);
		List<Object[]> listOfTextBooks = query.list();		
		
		LibraryRequestBooksListDto data = new LibraryRequestBooksListDto();
		List<LibraryRequestDto> bookList= new ArrayList<>();
		LibraryRequestDto bookDetails=null;

		for (Object[] objArr : listOfTextBooks) {
			
			bookDetails = new LibraryRequestDto();
			bookDetails.setBookId((String)objArr[0]);
			bookDetails.setTitle((String)objArr[1]);
			bookDetails.setStudentId((String)objArr[2]);
			bookDetails.setBookIsbn((String)objArr[3]);
			bookDetails.setIssuedOn((Date)objArr[4]);
			bookDetails.setRequestOn((Date)objArr[5]);
			bookDetails.setExpectedReturn((Date)objArr[6]);
			bookDetails.setDuesCharged((float)objArr[7]);
			bookDetails.setDuesPaid((float)objArr[8]);
			bookDetails.setNoOfRenewals((int)objArr[9]);
			bookDetails.setRenewedOn((Date)objArr[10]);
			bookDetails.setRemarks((String)objArr[11]);			
			bookList.add(bookDetails);
		}
		data.setCount(bookList.size());
		data.setLibraryRequests(bookList);
		data.setMessage("Library Book Data");		
		return data;
	}	
}