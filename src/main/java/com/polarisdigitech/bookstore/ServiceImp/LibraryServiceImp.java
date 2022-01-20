/**
 * 
 */
package com.polarisdigitech.bookstore.ServiceImp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisdigitech.bookstore.Entity.Book;
import com.polarisdigitech.bookstore.Repository.LibraryRepository;
import com.polarisdigitech.bookstore.Responses.LibraryResponse;
import com.polarisdigitech.bookstore.Responses.ResponseMessage;
import com.polarisdigitech.bookstore.ServiceInt.LibraryService;

/**
 * @author Famous B
 *
 */
@Service
public class LibraryServiceImp implements LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;
	
	
	@Override
	public ResponseMessage createBook(String title, String author, String isbn, String publisher, String gender,String country) {
		ResponseMessage response = new ResponseMessage();
		try {
			ResponseMessage validate = validateField(title, author, isbn, publisher, gender, country);
			if(!validate.isSuccess()) {
				response.setDescription(validate.getDescription());
				response.setSuccess(validate.isSuccess());
				return response;
			}
			Book book = libraryRepository.findByTitle(title);
			if(book == null) {
				book = new Book();
				book.setAuthor(author);
				book.setCountry(country);
				book.setCreatedDate(new Date());
				book.setUpdatedDate(new Date());
				book.setGender(gender);
				book.setIsbn(isbn);
				book.setPublisher(publisher);
				book.setTitle(title);
				libraryRepository.save(book);
				
				response.setSuccess(true);
				response.setDescription("Book created successfully");
				return response;
			}else {
				response.setSuccess(false);
				response.setDescription("Book title already exist, try use another title");
				return response;
			}
		}catch(Exception ex) {
//			System.out.println("error -"+ex);
			response.setSuccess(false);
			response.setDescription("An error occurred why trying to create book, please try again later");
			return response;
		}
	}
	
	
	@Override
	public ResponseMessage update(Long bookId, String title, String author, String isbn, String publisher, String gender,String country) {
		ResponseMessage response = new ResponseMessage();
		try {
			
			Book book = libraryRepository.getById(bookId);
			if(book == null) {
				response.setDescription("Book does not exist, can not update");
				response.setSuccess(false);
				return response;
			}
			if(author != null)
				book.setAuthor(author);
			if(country != null)
				book.setCountry(country);
			if(gender != null)
				book.setGender(gender);
			if(isbn != null)
				book.setIsbn(isbn);
			if(publisher != null)
				book.setPublisher(publisher);
			if(title != null) {
				Book exisTitle = libraryRepository.findByTitle(title);
				if(exisTitle != null) {
					response.setDescription("Book title already exist, can not update!");
					response.setSuccess(false);
					return response;
				}
				book.setTitle(title);
			}
			book.setUpdatedDate(new Date());
			libraryRepository.save(book);
			
			response.setSuccess(true);
			response.setDescription("Book updated successfully");
			return response;
		}catch(Exception ex) {
			System.out.println("update error -"+ex);
			response.setSuccess(false);
			response.setDescription("An error occurred why trying to update book, please try again later");
			return response;
		}
	}
	
	@Override
	public ResponseMessage delete(Long bookId) {
		ResponseMessage response = new ResponseMessage();
		try {
			Book book = libraryRepository.getById(bookId);
			if(book == null) {
				response.setDescription("Book does not exist, can not delete!");
				response.setSuccess(false);
				return response;
			}
			libraryRepository.delete(book);
			
			response.setSuccess(true);
			response.setDescription("Book deleted successfully");
			return response;
		}catch(Exception ex) {
//			System.out.println("delete error -"+ex);
			response.setSuccess(false);
			response.setDescription("An error occurred why trying to delete a book, please try again later");
			return response;
		}
	}
	
	
	@Override
	public LibraryResponse getAllBooks(int pageNumber) {
		LibraryResponse response = new LibraryResponse(false);
		try {
			pageNumber = (pageNumber - 1) * 5;
			List<Book> books = libraryRepository.getAllBooks(pageNumber);
			if(books.size() < 1) {
				response.setBooks(null);
				response.setDescription("No data found");
				response.setSuccess(false);
				return response;
			}
			int count = libraryRepository.getTotalBooksCount().intValue();
			response.setTotalCount(count);
        	response.setCurrentPageCount(books.size());
        	try {
        		Double value = Math.ceil(response.getTotalCount() / 5.0);
        		response.setNoOfPage(value.intValue());
        	} catch (Exception ex) {
//        		System.out.println("page number error");
        	}
        	DateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        	List<HashMap<String, String>> maps = new ArrayList<HashMap<String,String>>();
        	for(Book book: books) {
        		HashMap<String, String> data = new HashMap<String, String>();
        		data.put("id", String.valueOf(book.getId()));
        		data.put("title", book.getTitle());
        		data.put("author", book.getAuthor());
        		data.put("gender", book.getGender());
        		data.put("publisher", book.getPublisher());
        		data.put("isbn", book.getIsbn());
        		data.put("country", book.getCountry());
        		data.put("createdDate", format.format(book.getCreatedDate()));
        		data.put("updatedDate", format.format(book.getUpdatedDate()));
        		maps.add(data);
        	}
        	
        	response.setBooks(maps);
        	response.setDescription("Books data successfully retrieved.....");
			response.setSuccess(true);
			return response;
        	
		}catch(Exception ex) {
//			System.out.println("all_book error -"+ex);
			response.setSuccess(false);
			response.setDescription("An error occurred why trying to fetch books, please try again later");
			return response;
		}
	}
	
	
	
	@Override
	public LibraryResponse fetchByTitleOrAuthor(int page,String title, String author) {
		LibraryResponse response = new LibraryResponse(false);
		try {
			page = (page - 1) * 5;
			List<Book> books = libraryRepository.getByTitleOrAuthor(title, author, page);
			if(books.size() < 1) {
				response.setBooks(null);
				response.setDescription("No data found");
				response.setSuccess(false);
				return response;
			}
			int count = libraryRepository.getTotalCountForTitleOrAuthor(title, author).intValue();
			response.setTotalCount(count);
        	response.setCurrentPageCount(books.size());
        	try {
        		Double value = Math.ceil(response.getTotalCount() / 5.0);
        		response.setNoOfPage(value.intValue());
        	} catch (Exception ex) {
//        		System.out.println("page number error");
        	}
        	DateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        	List<HashMap<String, String>> maps = new ArrayList<HashMap<String,String>>();
        	for(Book book: books) {
        		HashMap<String, String> data = new HashMap<String, String>();
        		data.put("id", String.valueOf(book.getId()));
        		data.put("title", book.getTitle());
        		data.put("author", book.getAuthor());
        		data.put("gender", book.getGender());
        		data.put("publisher", book.getPublisher());
        		data.put("isbn", book.getIsbn());
        		data.put("country", book.getCountry());
        		data.put("createdDate", format.format(book.getCreatedDate()));
        		data.put("updatedDate", format.format(book.getUpdatedDate()));
        		maps.add(data);
        	}
        	
        	response.setBooks(maps);
        	response.setDescription("Books data successfully fetched.....");
			response.setSuccess(true);
			return response;
        	
		}catch(Exception ex) {
//			System.out.println("all_book error -"+ex);
			response.setSuccess(false);
			response.setDescription("An error occurred why trying to fetch books, please try again later");
			return response;
		}
	}
	
	
	private ResponseMessage validateField(String title, String author, String isbn, String publisher, String gender,String country) {
		ResponseMessage response = new ResponseMessage();
		
		if(title == null || title == "" ) {
			response.setSuccess(false);
			response.setDescription("Title field can not be empty");
			return response;
		}
		
		if(author == null ||  author == "") {
			response.setSuccess(false);
			response.setDescription("Author field can not be empty");
			return response;
		}
		
		if(isbn == null || isbn == "") {
			response.setSuccess(false);
			response.setDescription("isbn field can not be empty");
			return response;
		}
		
		if(publisher == null || publisher == "") {
			response.setSuccess(false);
			response.setDescription("Publisher field can not be empty");
			return response;
		}
		
		if(country == null || country == "" ) {
			response.setSuccess(false);
			response.setDescription("Country field can not be empty");
			return response;
		}
		
		if(gender == null || gender == "") {
			response.setSuccess(false);
			response.setDescription("Gender field can not be empty");
			return response;
		}
		
		response.setSuccess(true);
		response.setDescription("Validation successful");
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
