package exception;


public class UrlNotMatchingException extends RuntimeException{
	
	public UrlNotMatchingException(String msg)
	{
		System.out.println(msg);
	}

}
