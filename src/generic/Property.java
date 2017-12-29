package generic;

import java.io.FileInputStream;
import java.util.Properties;

public class Property {

	public static String getValue(String path, String key)
	{
		String v="";
		try
		{
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			v=p.getProperty(key);
		}
		
		catch(Exception e)
		{
			
		}
		return v;
	}
}
