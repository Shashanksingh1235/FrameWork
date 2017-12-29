package generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.junit.runners.AllTests;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.ColorMismatchException;
import exception.ElementNotPresentException;
import exception.ElementNotSelectedException;
import exception.FontMismatchException;
import exception.FontSizeMismatchException;
import exception.OptionNotFoundException;
import exception.TextNotMatchingException;
import exception.TitleNotMatchingException;
import exception.UrlNotMatchingException;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;



public class WebGeneric {
	public static void enter(WebDriver driver, String keyword2, String keyword3)
	{
		driver.findElement(By.xpath(keyword2)).sendKeys(keyword3);
	}
	
	public static void click(WebDriver driver, String keyword2)
	{
		driver.findElement(By.xpath(keyword2)).click();
	}
	
	public static void verifyElementPresent(Logger log, WebDriver driver, String keyword2)
	{
		try
		{
			boolean displayed = driver.findElement(By.xpath(keyword2)).isDisplayed();
			if(displayed)
			{
				log.info("PASS:Element is present:"+keyword2);
			}
			else
			{
				log.error("FAIL:Element is not present:");
				String msg="Element is not present";
				throw new ElementNotPresentException(msg+keyword2);
			}
		}
		catch(NoSuchElementException e)
		{
			String msg="Element is not present(in src):";
			throw new ElementNotPresentException(msg+keyword2);
		}
	}
	
	public static void verifyElementPresent(Logger log, WebDriver driver, String keyword2,String keyword3)
	{
		Long timeout=Long.parseLong(keyword3);
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(keyword2)));
				log.info("PASS:Element is present:"+keyword2);
			}
			catch(TimeoutException e)
			{
				log.error("FAIL:Element is not present even after:"+timeout+"sec");

				throw new ElementNotPresentException("Element not present:"+keyword2);
			}
	}
		
		public static void verifyTitle(Logger log, WebDriver driver, String keyword2,String keyword3)
		{
			Long timeout=Long.parseLong(keyword3);
			log.info("Expected Title:"+keyword2);
			WebDriverWait wait=new WebDriverWait(driver, timeout);
			try
			{
				wait.until(ExpectedConditions.titleIs(keyword2));
					log.info("PASS:Title is Matching:");
				}
				catch(TimeoutException e)
				{
					String aTitle=driver.getTitle();
					log.info("Actual Title:"+aTitle);
					log.error("FAIL:Element is not Matching even after:"+timeout+"sec");

					throw new TitleNotMatchingException("Title Not Matching");
				}
		
		}
		public static void verifyURLContains(Logger log, WebDriver driver, String keyword2,String keyword3)
		{
			long timeOut=Long.parseLong(keyword3);
			log.info("Expected URL fraction:"+keyword2);
			WebDriverWait wait=new WebDriverWait(driver, timeOut);
			try
			{
				wait.until(ExpectedConditions.urlContains(keyword2));
					log.info("PASS:Actual url contains Expected URL fraction");
				}
				catch(TimeoutException e)
				{
					String aURL=driver.getCurrentUrl();
					log.info("Actual url:"+aURL);
					log.error("FAIL:URL does not contain expected url fraction");

					throw new UrlNotMatchingException("URL not Matching");
					
				}
		}
		
		public static void verifyElementIsEnabled(Logger log, WebDriver driver, String keyword2)
		{
			boolean enabled=driver.findElement(By.xpath(keyword2)).isEnabled();
			if(enabled)
			{
				log.info("pass:Element is Enabled:"+keyword2);
			}
			else
			{
				
				log.error("Fail: Element is disabled:"+keyword2);
	      throw new InvalidElementStateException();//built in Exception
		}
	}
		
		public static void verifyElementIsSelected(Logger log, WebDriver driver, String keyword2)
		{
			boolean selected=driver.findElement(By.xpath(keyword2)).isSelected();
			if(selected)
			{
				log.info("pass:Element is Selected:"+keyword2);
			}
			else
			{
				
				log.error("Fail: Element is not selected:"+keyword2);
	      throw new ElementNotSelectedException("Element Not Selected");
		}
	}
		
		public static void verifyText(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			log.info("Expected Text:"+keyword3);
			String aText=driver.findElement(By.xpath(keyword2)).getText();
			log.info("actual Text:"+aText);
			if(aText.equals(keyword3))
			{
				log.info("pass:Element is Matching");
			}
			else
			{
				log.error("Fail: Element is not Matching");
	      throw new TextNotMatchingException("Element Not Selected");
		}
		
		}

		public static void verifyColor(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement element=driver.findElement(By.xpath(keyword2));
			String aColor=element.getCssValue("color");
			String hColor=Color.fromString(aColor).asHex();
			log.info("Actual color code:"+hColor);
			log.info("Expected color code:"+keyword3);
			
			if(hColor.equals(keyword3))
			{
				log.info("Pass: color is matching for:"+keyword3);
		}
			else
			{
				log.error("Fail: color is not matching for:"+keyword3);
				throw new ColorMismatchException();
			}
}
		
		public static void verifyFontType(Logger log, WebDriver driver, String keyword1, String keyword2, String keyword3)
		{
			WebElement element=driver.findElement(By.xpath(keyword2));
			String aFont=element.getCssValue("font-family");
			log.info("Actual Font:"+aFont);
			log.info("Expected Font:"+keyword3);
			
			if(aFont.equals(keyword3))
			{
				log.info("Pass: font is matching for:"+keyword3);
		}
			else
			{
				log.error("Fail: font is not matching for:"+keyword3);
				throw new FontMismatchException();
			}	
		}
		
		public static void verifyFontSize(Logger log, WebDriver driver, String keyword1, String keyword2, String keyword3)
		{
			WebElement element=driver.findElement(By.xpath(keyword2));
			String aFontSize=element.getCssValue("font-size");
			log.info("Actual FontSize:"+aFontSize);
			log.info("Expected FontSize:"+keyword3);
			
			if(aFontSize.equals(keyword3))
			{
				log.info("Pass: fontsize is matching for:"+keyword3);
		}
			else
			{
				log.error("Fail: fontsize is not matching for:"+keyword3);
				throw new FontSizeMismatchException();
			}	
		}

		public static void mouseHover(Logger log, WebDriver driver, String keyword2)
		{
			Actions actions=new Actions(driver);
			WebElement e=driver.findElement(By.xpath(keyword2));
			actions.moveToElement(e).perform();
			log.info("hovering on element:"+keyword2);
		}
		
		public static void dragDrop(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			Actions actions=new Actions(driver);
			WebElement from=driver.findElement(By.xpath(keyword2));
			WebElement to=driver.findElement(By.xpath(keyword3));
			actions.dragAndDrop(from, to).perform();
			log.info("Drang element:"+keyword2);
			log.info("Drop it on to element:"+keyword3);
		}
		
		public static void doubleClick(Logger log, WebDriver driver, String keyword2)
		{
			Actions actions=new Actions(driver);
			WebElement e=driver.findElement(By.xpath(keyword2));
			actions.doubleClick(e).perform();
			log.info("Double clicking on element:"+keyword2);
}	
		public static void rightClick(Logger log, WebDriver driver, String keyword2)
		{
			Actions actions=new Actions(driver);
			WebElement e=driver.findElement(By.xpath(keyword2));
			actions.contextClick(e).perform();
			log.info("Right clicking on element:"+keyword2);

}
		public static void selectOptionByIndex(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  int index=Integer.parseInt(keyword3);
		  select.selectByIndex(index);
		  log.info("Selecting option in listbox using index:"+keyword3);
		}
		public static void selectOptionByValue(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  select.selectByValue(keyword3);
		  log.info("selecting option in listbox using value:"+keyword3);
		}
		public static void selectOptionByText(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  select.selectByVisibleText(keyword3);
		  log.info("selecting option in listbox using text:"+keyword3);
		}
		
		public static void deselectOptionByIndex(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  int index=Integer.parseInt(keyword3);
		  select.deselectByIndex(index);
		  log.info("DeSelecting option in listbox using index:"+keyword3);
		}
		
		public static void deselectOptionByValue(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  select.deselectByValue(keyword3);
		  log.info("Deselecting option in listbox using value:"+keyword3);
		}
		
		public static void deselectOptionByText(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  select.deselectByVisibleText(keyword3);
		  log.info("deselecting option in listbox using text:"+keyword3);
		}
		
		public static void deselectAllOptions(Logger log, WebDriver driver, String keyword2)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
		  select.deselectAll();
		  log.info("selecting option in listbox");
		}
		
		public static void switchToFrameByIndex(Logger log, WebDriver driver, String keyword2)
		{
			int index=Integer.parseInt(keyword2) ;
			driver.switchTo().frame(index);
			log.info("switching in to frame by index:"+keyword2);
		}
		public static void switchToFrameByIdOrName(Logger log, WebDriver driver, String keyword2)
		{
			driver.switchTo().frame(keyword2);
			log.info("switching in to frame by Id or Nmae:"+keyword2);
		}
		
		public static void switchToFrameByAddress(Logger log, WebDriver driver, String keyword2)
		{
			WebElement e = driver.findElement(By.xpath(keyword2));
			driver.switchTo().frame(e);
			log.info("switching in to frame by address:"+keyword2);
		}
		public static void exitFromFrameToPage(Logger log, WebDriver driver)
		{
			driver.switchTo().defaultContent();
			log.info("exiting from Frame to Page");
		}
		
		public static void exitFromFrameToParent(Logger log, WebDriver driver)
		{
			driver.switchTo().parentFrame();
			log.info("exiting from Frame to Parent");
		}
		
		public static void SearchListBox(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			boolean found=false;
			log.info("Searching"+keyword3+"in listbox "+keyword2);
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
			List<WebElement> allOptions=select.getOptions();
			for(WebElement option:allOptions)
			{
				String text=option.getText();
				log.info("comparing with:"+text);
				if(text.equalsIgnoreCase(keyword3))
				{
					found=true;
					break;
				}
			}
			if(found)
			{
				log.info("PASS: option found is listbox");
			}
			else
			{
				log.error("FAIL:option not found in listbox");
				throw new OptionNotFoundException();
			}
		}
		
		public static void IsListBoxSorted(Logger log, WebDriver driver, String keyword2, String keyword3)
		{
			WebElement e=driver.findElement(By.xpath(keyword2));
			Select select=new Select(e);
			List<WebElement> allOptions=select.getOptions();
			ArrayList<String> allText=new ArrayList<String>();
			for(WebElement option:allOptions)
			{
			allText.add(option.getText());
}
		
		ArrayList<String> copy=new ArrayList<String>(allText);	
		Collections.sort(copy);
		if(allText.equals(copy)) {
			log.info("PASS: listbox is in sorted order");
		}
		else
		{
			log.error("FAIL:list box is not in sorted order");
			throw new ListBoxNotSorted();
		}
		}
}









