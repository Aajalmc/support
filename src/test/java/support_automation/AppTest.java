package support_automation;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;



//comment the above line and uncomment below line to use Chrome

//import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
//private static final WebElement WebElement = null;
	@Test
public void test() throws InterruptedException, AWTException, IOException {
	
System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

WebDriver driver = new ChromeDriver();

ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
capabilities.setCapability(ChromeOptions.CAPABILITY, options);


driver.manage().window().maximize();

String baseUrl = "https://support.ptc.com/";

driver.get(baseUrl);

WebElement Username = driver.findElement(By.xpath("//*[@id=\"ptcSecureUser\"]"));

Username.clear();

Username.sendKeys("abc");

WebElement Password = driver.findElement(By.xpath("//*[@id=\"ptcSecurePass\"]"));

Password.clear();

Password.sendKeys("123");

WebElement Login = driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]"));

Login.click();



JavascriptExecutor js = (JavascriptExecutor) driver;

js.executeScript("window.scrollBy(0,1000)");

Thread.sleep(2000);

WebElement Browse = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div/div[2]/div/a/figure/figcaption/h3"));

Browse.click();

Set<String> handles = driver.getWindowHandles();
String currentHandle = driver.getWindowHandle();
for (String handle : handles) {

 if (!handle .equals(currentHandle))
 {
     driver.switchTo().window(handle);
 }
}


WebElement Search = driver.findElement(By.id("searchInput"));
Search.sendKeys("Installing ThingWorx® Navigate");
Thread.sleep(2000);
Search.sendKeys(Keys.ENTER);

Set<String> handles1 = driver.getWindowHandles();
String currentHandle1 = driver.getWindowHandle();
for (String handle : handles1) {

 if (!handle .equals(currentHandle1))
 {
     driver.switchTo().window(handle);
 }
}
   //Close pop -up
    Thread.sleep(2000);
    driver.switchTo().frame("sc_footer");
    driver.switchTo().defaultContent();
    Thread.sleep(10000);
    driver.findElement(By.xpath("//*[@id=\"ptcapp\"]/div[5]/div/div[5]/a[1]")).click();
    
    Thread.sleep(1000);
    
    JavascriptExecutor js1 = (JavascriptExecutor) driver;

    js1.executeScript("window.scrollBy(0,3000)");
    
    
  //Click on one month ago filter 
    driver.findElement(By.xpath("//*[@id=\"LastModifiedTime_ChkGroup_One Month Ago - One Week Ago\"]")).click();
    
    Thread.sleep(1000);
  
    //Click on apply link
    driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[8]/fieldset/div/div[7]/div/a[1]/span")).click();
    
    Thread.sleep(1000);
    
    //Remove reference doc
    
    driver.findElement(By.xpath("//*[@id=\"searchbreadcrumbs\"]/span[1]/input")).click();
    
    Thread.sleep(1000);
  
	//Mouse hover for first element 
	WebElement ele = driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/a"));
	//Create object 'action' of an Actions class
	Actions action = new Actions(driver);
	//Mouse hover on an element
	action.moveToElement(ele).perform();
	
	Thread.sleep(2000);
	
	//Screenshot of the first element
	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //The below method will save the screen shot in C drive with name "screenshot.png"
    FileUtils.copyFile(scrFile, new File("D:\\share\\Sitecore_Automation\\Selenium_Screenshot\\screenshot.png"));
    System.out.println("Screenshot taken");
    Thread.sleep(1000);
    
    //Click on the pdf link
    //Need to use loop to open multiple PDFs
    
    driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[1]/div[1]/a")).click();
 
    Thread.sleep(1000);
    
    JavascriptExecutor js3 = (JavascriptExecutor) driver;

    js3.executeScript("window.scrollBy(1000,1000)");
    
    Thread.sleep(1000);
    
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //The below method will save the screen shot in d drive with name "screenshot.png"
    FileUtils.copyFile(scrFile1, new File("D:\\share\\Sitecore_Automation\\Selenium_Screenshot\\aa.png"));
    System.out.println("Screenshot taken");
    Thread.sleep(1000);
    
    
    
    //Open all the pdf present under the browse section
    
    Thread.sleep(1000);
    
    int i;
    
    for (i=1;i<=11;i++)
    {
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/a["+ i +"]")).sendKeys(selectLinkOpeninNewTab);
        Thread.sleep(1000);
    }
    
     
    //Focus on the new tab
     int j;
     for(j=0;j<=13;j++)
     {
       ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
       driver.switchTo().window(tabs.get(j));
       ((JavascriptExecutor) driver)
       .executeScript("window.scrollTo(10, -document.body.scrollHeight)");
       //Thread.sleep(2000);
       
       for(int a= 1; a<=11;a++)
       {
       File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
       FileUtils.copyFile(scrFile2, new File("D:\\share\\Sitecore_Automation\\Selenium_Screenshot\\screenshot["+ a +"].png"));
       
     }
     
       
       
           //Screenshot of the pdf including title of the page
       
    
           //File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           //The below method will save the screen shot in d drive with name "screenshot.png"
      //FileUtils.copyFile(scrFile2, new File("C:\\Users\\ntiwari\\Desktop\\Selenium Screenshot\\bb.png"));
          
       }
      
     }
}