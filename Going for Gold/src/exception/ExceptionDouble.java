package exception;

public class ExceptionDouble extends Exception {

	/**
	 * This class is used if a question is already present in the deck and which one wants to be added
	* @author Peccio Leandro
	*
	*/
	private static final long serialVersionUID = 1L;
	public ExceptionDouble() {
		super("This question is already present");
	}

}
