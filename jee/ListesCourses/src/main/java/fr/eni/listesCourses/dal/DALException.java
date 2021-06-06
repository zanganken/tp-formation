package fr.eni.listesCourses.dal;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}

	public DALException(String message, Throwable e) {
		super(message, e);
	}
	
	@Override
	public String getMessage() {
		return String.format("Couche DAL - %s", super.getMessage());
	}
}
