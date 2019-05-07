
public class NotFullCardException extends RuntimeException {
	public NotFullCardException() {
		super("The text file has not enough card to generate key");
	}
}
