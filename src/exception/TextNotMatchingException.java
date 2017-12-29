package exception;


public class TextNotMatchingException  extends RuntimeException {
	
	public TextNotMatchingException(String msg)
	{
		System.out.println(msg);
	}

}
