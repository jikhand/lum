package com.set.service;

import java.util.Date;

import com.set.dto.LibraryDto;
import com.set.dto.LibraryRequestBooksListDto;
import com.set.dto.LibraryRequestDto;
import com.set.model.LibraryBooks;
import com.set.model.LibraryDetails;
import com.set.model.LibraryRequest;
import com.set.model.LibraryRequestDetails;

public interface LibraryService {
	  public void save(LibraryBooks libraryBooks);
	  public void editLibBooks(LibraryDto libraryDto,String userId);
	  public void deleteLibBooks(LibraryBooks libraryBooks);
	  public LibraryDetails listLibBooks(int pagenumber,String searchdata);
	  public LibraryDetails listLibBooks();	  
	  
	  public void saveLibraryRequest(LibraryRequest libraryRequest);
	  public void editLibRequest(LibraryRequestDto libraryRequestDto,String userId);
	  public void deleteLibRequest(String bookId, String studentId);
	  public LibraryRequestDetails listLibBooksRequest(int pagenumber,String searchdata);
	  public LibraryRequestBooksListDto recentLibBooks(String id);
	  
}
