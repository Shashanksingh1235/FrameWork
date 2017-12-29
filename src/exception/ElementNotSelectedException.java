package exception;


public class ElementNotSelectedException extends RuntimeException{

	public ElementNotSelectedException(String msg)
	{
		System.out.println(msg);
	}
}
