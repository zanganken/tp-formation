package fr.eni.listesCourses.bll;

public class BLLException extends Exception {
	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public String getMessage() {
		return String.format("Couche BLL - %s", super.getMessage());
	}
}
