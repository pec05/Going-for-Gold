package exception;

public class ExceptionEmptyField  extends Exception{

	/**
	 * This class is used to tell if a question has a parameter is empty.
	* @author Peccio Leandro
	*
	*/
	private static final long serialVersionUID = 1L;
	public ExceptionEmptyField() {
		super("Please fill all the field!");
	}

}
