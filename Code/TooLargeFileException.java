
public class TooLargeFileException extends RuntimeException {
	public TooLargeFileException() {
		super("The text file contains more than 28 cards.");
	}
}
