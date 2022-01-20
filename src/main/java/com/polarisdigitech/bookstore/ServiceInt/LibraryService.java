/**
 * 
 */
package com.polarisdigitech.bookstore.ServiceInt;


import org.springframework.stereotype.Component;

import com.polarisdigitech.bookstore.Responses.LibraryResponse;
import com.polarisdigitech.bookstore.Responses.ResponseMessage;

/**
 * @author Famous B
 *
 */
@Component
public interface LibraryService {

	public ResponseMessage createBook(String title, String author, String isbn, String publisher, String gender,String country);
	public ResponseMessage update(Long bookId, String title, String author, String isbn, String publisher, String gender,String country);
	public ResponseMessage delete(Long bookId);
	public LibraryResponse getAllBooks(int pageNumber);
	public LibraryResponse fetchByTitleOrAuthor(int page,String title, String author);
}
