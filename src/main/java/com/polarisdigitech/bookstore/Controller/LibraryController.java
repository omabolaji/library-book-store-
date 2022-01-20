/**
 * 
 */
package com.polarisdigitech.bookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polarisdigitech.bookstore.Responses.LibraryResponse;
import com.polarisdigitech.bookstore.Responses.ResponseMessage;
import com.polarisdigitech.bookstore.ServiceInt.LibraryService;

/**
 * @author Famous B
 *
 */
@RestController
@RequestMapping(path="/book")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public LibraryResponse fetchAllBooks(
			@RequestParam("pageNumber") int pageNumber) {
		LibraryResponse response = new LibraryResponse(true);
		response = libraryService.getAllBooks(pageNumber);
		return response;
	}
	
	
	@RequestMapping(value="/fetchBy/titleOrAuthor", 
			method= RequestMethod.GET, 
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public LibraryResponse fetchByTitleOrAuthor(
			@RequestParam(name="title",required=false) String title,
			@RequestParam(name="author",required=false) String author,
			@RequestParam(name="pageNumber",required=true) int pageNumber) {
		LibraryResponse response = new LibraryResponse(true);
		response = libraryService.fetchByTitleOrAuthor(pageNumber, title, author);
		return response;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseMessage createBook(
			@RequestParam(name="title", required = true) String title,
			@RequestParam(name="gender", required = true) String gender,
			@RequestParam(name="country", required = true) String country,
			@RequestParam(name="isbn", required = true) String isbn,
			@RequestParam(name="author", required = true) String author,
			@RequestParam(name="publisher", required = true) String publisher) {
		ResponseMessage response = new ResponseMessage();
		response = libraryService.createBook(title, author, isbn, publisher, gender, country);
		return response;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseMessage updateBook(
			@RequestParam(name="bookId", required = true) Long bookId,
			@RequestParam(name="title", required = false) String title,
			@RequestParam(name="gender", required = false) String gender,
			@RequestParam(name="country", required = false) String country,
			@RequestParam(name="isbn", required = false) String isbn,
			@RequestParam(name="author", required = false) String author,
			@RequestParam(name="publisher", required = false) String publisher) {
		ResponseMessage response = new ResponseMessage();
		response = libraryService.update(bookId, title, author, isbn, publisher, gender, country);
		return response;
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseMessage updateBook(
			@RequestParam(name="bookId", required = true) Long bookId) {
		ResponseMessage response = new ResponseMessage();
		response = libraryService.delete(bookId);
		return response;
	}
	
}
