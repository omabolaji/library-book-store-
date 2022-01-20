/**
 * 
 */
package com.polarisdigitech.bookstore.Responses;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Famous B
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LibraryResponse extends ResponseMessage {

	public LibraryResponse (boolean isSuccess) {
		super(isSuccess);
	}
	
	private int totalCount;
	private int currentPageCount;
	private int noOfPage;
	private List<HashMap<String, String>> books;
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the currentPageCount
	 */
	public int getCurrentPageCount() {
		return currentPageCount;
	}
	/**
	 * @param currentPageCount the currentPageCount to set
	 */
	public void setCurrentPageCount(int currentPageCount) {
		this.currentPageCount = currentPageCount;
	}
	/**
	 * @return the noOfPage
	 */
	public int getNoOfPage() {
		return noOfPage;
	}
	/**
	 * @param noOfPage the noOfPage to set
	 */
	public void setNoOfPage(int noOfPage) {
		this.noOfPage = noOfPage;
	}
	/**
	 * @return the books
	 */
	public List<HashMap<String, String>> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<HashMap<String, String>> books) {
		this.books = books;
	}
	
}
