
public class InvalidCardException extends RuntimeException {
	public InvalidCardException() {
		super("The cards in text file is greater than 28");
	}
}
