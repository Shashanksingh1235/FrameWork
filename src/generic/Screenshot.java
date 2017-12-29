package generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static void get(WebDriver driver, String destFile)
	{
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcFile=t.getScreenshotAs(OutputType.FILE);
		
		try
		{
			FileUtils.copyFile(srcFile, new File(destFile));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	public static String generateImageName()
	{
		SimpleDateFormat s=new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		String dateTime=s.format(new Date());
		String imgName=dateTime+".png";
		return imgName;
		
	}

}
