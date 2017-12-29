package exception;


public class TitleNotMatchingException extends RuntimeException
{ 
	
	public TitleNotMatchingException(String msg)
	{
		System.out.println(msg);
	}
}
