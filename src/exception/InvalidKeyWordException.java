package exception;


public class InvalidKeyWordException  extends RuntimeException{
	
	public InvalidKeyWordException(String msg)
	{
		System.err.println(msg);
	}

}
