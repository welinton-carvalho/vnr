package br.com.vnr.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 3075185827267684941L;

	public ServiceException() {

	}

	public ServiceException(String descriptionOfError) {
		super(descriptionOfError);
	}

	public ServiceException(String descriptionOfError, Throwable throwable) {
		super(descriptionOfError, throwable);
	}

}
