package SitecoreUpload;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class UploadPDF {

	String lang[]= {"Japanese (Japan) : 日本語 (日本)",
					"German (Germany) : Deutsch (Deutschland)",
					"English (region) : English (region)",
					"Spanish (Spain) : español (España, alfabetización internacional)",
					"Russian (Russia) : русский (Россия)",
					"Portuguese (Brazil) : português (Brasil)",
					"Italian (Italy) : italiano (Italia)",
					"French (France) : français (France)",
					"Korean (Korea) : 한국어(대한민국)",
					"Chinese (Simplified, PRC) : 中文(中华人民共和国)",
					"Chinese (Traditional, Taiwan) : 中文(台灣)"};
	String langpublish[]= {"Japanese (Japan)",
						"German (Germany)",
						"English",
						"Spanish (Spain)",
						"Russian (Russia)",
						"Portuguese (Brazil)",
						"Italian (Italy)",
						"French (France)",
						"Korean (Korea)",
						"Chinese (Simplified, PRC)",
						"Chinese (Traditional, Taiwan)"};
	int langCount= lang.length;
	int langpub= langpublish.length;
	int i,j;
	
	
	public static WebDriver driver;
	
	@Test
	public void SitecorePDFUpload() throws InterruptedException, AWTException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://aws-staging.ptc.com/sitecore/shell/Applications/Content%20Editor.aspx?fo={599DEE0A-974B-44FB-953C-CBEFCC6828C7}");
		
		System.out.println("---------Getting User ID and password---------");
		WebElement id = driver.findElement(By.id("UserName"));
		id.click();
		id.sendKeys("amc");
		Thread.sleep(1000);
		WebElement pass = driver.findElement(By.id("Password"));
		pass.click();
		pass.sendKeys("Mothermary2");
		Thread.sleep(1000);
		WebElement login = driver.findElement(By.id("LogInBtn"));
		login.click();
		Thread.sleep(10000);
		System.out.println("Logged into Sitecore page");
		
		for(i=0;i<langCount;i++)
		{
			for(j=i;j<=i;j++)
			{
						driver.findElement(By.xpath("//img[@class='scEditorHeaderVersionsLanguageGlyph']")).click();
						selectFrame("Header_Language_Gallery");
						WebElement lang1 = driver.findElement(By.xpath("//*[contains(text(),'"+lang[i]+"')]"));
						lang1.click();
						System.out.println("Language Selected - " +lang[i]);
						
						LocEdit();
						
						Attach();
						StringSelection selectPDF = new StringSelection("C:\\Users\\amc\\Downloads\\ja\\StudioGettingStarted.pdf");
//						System.out.println(System.getProperty("PDFLocation"));
						Thread.sleep(3000);
						
						//Upload File Name declaration
//						StringSelection selectPDF = new StringSelection(PDFSelection(System.getProperty("PDFLocation")));
						Thread.sleep(3000);

						//Getting toolkit
						Toolkit toolKit = Toolkit.getDefaultToolkit();
						Thread.sleep(1000);
						
						//Getting clip board as file upload window
						Clipboard clipBoard = toolKit.getSystemClipboard();
						Thread.sleep(1000);
						
						//Copying string file name to the file upload window
						clipBoard.setContents(selectPDF, null);
						Thread.sleep(1000);
						
						System.out.println("PDF Selection- " +selectPDF);
						
						Robot robot = new Robot();
						robot.setAutoDelay(2000);
						
						robot.setAutoDelay(4000);
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						
						robot.setAutoDelay(4000);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_V);
						
						robot.setAutoDelay(4000);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						

						Thread.sleep(2000);

						driver.findElement(By.xpath("//*[@id='OK']")).click();

						deSelectFrame();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//img[@alt='Save any changes. (Ctrl+S)']")).click();
						Thread.sleep(2000);
						System.out.println("PDF attached and changes are saved");
						
						System.out.println("---------------Publish PDF----------------");
						driver.findElement(By.xpath("//a[@title=' (Alt+P)']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("(//img[@src='/sitecore/shell/themes/standard/Images/ribbondropdown.gif'])[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("(//td[@class='scMenuItemCaption'])[2]")).click();
						Thread.sleep(2000);
						
						selectFrame("jqueryModalDialogsFrame");
						selectFrame("scContentIframeId0");
						Thread.sleep(1000);
						driver.findElement(By.xpath("//input[@id='SelectAllLanguages']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@id='SelectAllLanguages']")).click();
						Thread.sleep(2000);
						

						driver.findElement(By.xpath("//label[contains(text(),'"+langpublish[i]+"')]")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[@id='NextButton']")).click();
						Thread.sleep(4000);
						deSelectFrame();
						selectFrame("jqueryModalDialogsFrame");
						Thread.sleep(1000);
						selectFrame("scContentIframeId1");
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[@id='OK']")).click();
						Thread.sleep(6000);
						deSelectFrame();
						selectFrame("jqueryModalDialogsFrame");
						Thread.sleep(1000);
						selectFrame("scContentIframeId0");
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button[@id='CancelButton']")).click();
						Thread.sleep(5000);
						deSelectFrame();
						System.out.println("PDF published in " +langpublish[i]);
						Thread.sleep(1000);
						
						System.out.println("-----------Checkin-----------------");
						Thread.sleep(1000);
						driver.findElement(By.xpath("//a[@title=' (Alt+R)']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//a[@title='Check this item in.']")).click();
						Thread.sleep(3000);
						System.out.println("Checked in");
						
						System.out.println("------------------------------------------------");
				
			}
			
			System.out.println("Changing Language..");
		}
		driver.close();
	}
	
	public static void selectFrame( String frameid) {
	    WebElement frame = driver.findElement(By.xpath("//iframe[@id='"+frameid+"']"));
	    driver.switchTo().frame(frame);
	  } 
	
	public static void selectFrame1( String frameid) {
	    WebElement frame = driver.findElement(By.xpath("//frame[@id='"+frameid+"']"));
	    driver.switchTo().frame(frame);
	  } 
	
	  public static void deSelectFrame() {
		  driver.switchTo().window(driver.getWindowHandle());
	  } 
	  
	public static void LocEdit() throws InterruptedException {
		if(!driver.findElements(By.linkText("Add a new version.")).isEmpty()) { 
			System.out.println("---------------Add a new version----------------");
			Thread.sleep(3000);
			driver.findElement(By.linkText("Add a new version.")).click();
			Thread.sleep(2000);
			System.out.println("Adding a new version");
			Thread.sleep(3000);
		}
		
			else if(!driver.findElements(By.linkText("Lock and Edit")).isEmpty()) {
					System.out.println("---------------Lock and Edit----------------");
					Thread.sleep(3000);
					driver.findElement(By.linkText("Lock and Edit")).click();
					Thread.sleep(2000);
					System.out.println("Clicked on Lock and Edit");
					Thread.sleep(3000);
			}
	
		else if(driver.findElements(By.linkText("Lock and Edit")).isEmpty()) {
			System.out.println("---------------Lock and Edit----------------");
			Thread.sleep(1000);
			System.out.println("Lock and Edit link is not present");
			Thread.sleep(2000);
		}
	}
	
	public static String PDFSelection(String PDFLocation) {
		return PDFLocation;			
	}
	
	public void Attach() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("---------------Attach PDF and Save changes----------------------");
		Thread.sleep(1000);
		WebElement AttachPDF = driver.findElement(By.linkText("Attach"));
		Thread.sleep(2000);
		AttachPDF.click();
		Thread.sleep(1000);
		
		selectFrame("jqueryModalDialogsFrame");
		Thread.sleep(1000);
		selectFrame("scContentIframeId0");
		Thread.sleep(1000);
		selectFrame1("Attach");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='file']")).click();				
		Thread.sleep(2000);
	}
}
