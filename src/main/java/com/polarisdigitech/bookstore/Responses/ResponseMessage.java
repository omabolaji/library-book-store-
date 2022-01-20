/**
 * 
 */
package com.polarisdigitech.bookstore.Responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Famous B
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessage implements Serializable {

	private boolean isSuccess;
	private String description;
	/**
	 * 
	 */
	public ResponseMessage() {
		super();
	}
	/**
	 * @param isSuccess
	 */
	
	public ResponseMessage(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}
	/**
	 * @param isSuccess
	 * @param description
	 */
	public ResponseMessage(boolean isSuccess, String description) {
		super();
		this.isSuccess = isSuccess;
		this.description = description;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
