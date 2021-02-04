package com.set.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.set.dao.LibraryDao;
import com.set.dto.LibraryDto;
import com.set.dto.LibraryRequestBooksListDto;
import com.set.dto.LibraryRequestDto;
import com.set.model.LibraryBooks;
import com.set.model.LibraryDetails;
import com.set.model.LibraryRequest;
import com.set.model.LibraryRequestDetails;
@Service
@Transactional
public class LibraryServiceImp implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	@Override
	public void save(LibraryBooks libraryBooks) {
		// TODO Auto-generated method stub
		libraryDao.save(libraryBooks);
	}
	
	@Override
	public void editLibBooks(LibraryDto libraryDto,String userId) {
		// TODO Auto-generated method stub
		libraryDao.editLibBooks(libraryDto, userId);
	}
	
	@Override
	public void deleteLibBooks(LibraryBooks libraryBooks) {
		// TODO Auto-generated method stub
		libraryDao.deleteLibBooks(libraryBooks);
	}

	@Override
	public LibraryDetails listLibBooks(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return libraryDao.listLibBooks(pagenumber, searchdata);
	}
	
	@Override
	public LibraryDetails listLibBooks() {
		// TODO Auto-generated method stub
		return libraryDao.listLibBooks();
	}	
	
	@Override
	public void saveLibraryRequest(LibraryRequest libraryRequest) {
		// TODO Auto-generated method stub
		libraryDao.saveLibraryRequest(libraryRequest);
	}
	
	@Override
	public void editLibRequest(LibraryRequestDto libraryRequestDto,String userId) {
		// TODO Auto-generated method stub
		libraryDao.editLibRequest(libraryRequestDto, userId);
	}
	
	@Override
	public void deleteLibRequest(String bookId, String studentId) {
		// TODO Auto-generated method stub
		libraryDao.deleteLibRequest(bookId,studentId);
	}
	
	@Override
	public LibraryRequestDetails listLibBooksRequest(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return libraryDao.listLibBooksRequest(pagenumber, searchdata);
	}
	
	@Override
	public LibraryRequestBooksListDto recentLibBooks(String id) {
		// TODO Auto-generated method stub
		return libraryDao.recentLibBooks(id);
	}
}