package support_automation;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

public class supportpdfs {
//private static final WebElement WebElement = null;
@Test
public void test() throws InterruptedException, AWTException, IOException {
	
System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");

WebDriver driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
capabilities.setCapability(ChromeOptions.CAPABILITY, options);

driver.manage().window().maximize();


String baseUrl = "https://support.ptc.com/";

driver.get(baseUrl);

System.out.println("Getting user id and password from Jenkins");
//Username.sendKeys("abc");
WebElement id = driver.findElement(By.id("ptcSecureUser"));
id.clear();
id.click();
id.sendKeys(System.getProperty("Id"));


//Password.sendKeys("123");
WebElement pass = driver.findElement(By.id("ptcSecurePass"));
pass.clear();
pass.click();
pass.sendKeys(System.getProperty("Password"));

WebElement Login = driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]"));

Login.click();

System.out.println("Logged in succesfully");

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
Search.sendKeys(System.getProperty("Title"));
Thread.sleep(2000);
Search.sendKeys(Keys.ENTER);
System.out.println("Title searched successfully");
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
    
    //Click on one week ago filter 
    
    JavascriptExecutor js1 = (JavascriptExecutor) driver;

    js1.executeScript("window.scrollBy(0,5000)");
    
    
    Thread.sleep(2000);
    
 
    driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[8]/fieldset/div/div[5]/label/input")).click();
    
    Thread.sleep(1000);
  
    //Click on apply link
    driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[8]/fieldset/div/div[7]/div/a[1]/span")).click();
    
    Thread.sleep(1000);
    
    //Remove reference doc
    
   // driver.findElement(By.xpath("//*[@id=\"searchbreadcrumbs\"]/span[1]/input")).click();
    
    Thread.sleep(1000);
  
	//Mouse hover for first element 
	WebElement ele = driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/a"));
	//Create object 'action' of an Actions class
	Actions action = new Actions(driver);
	//Mousehover on an element
	action.moveToElement(ele).perform();
	
	Thread.sleep(2000);
	
	//Screenshot of the first element
	
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //The below method will save the screen shot in C drive with name "screenshot.png"
    FileUtils.copyFile(scrFile, new File("D:\\Selenium_Screenshot\\Screenshot1.png"));
    System.out.println("Screenshot taken of the search result");
    Thread.sleep(1000);
    
    //Click on the pdf link
    //Need to use loop to open multipe PDFs
    
    driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/a")).click();
 
    Thread.sleep(1000);
    
    JavascriptExecutor js3 = (JavascriptExecutor) driver;

    js3.executeScript("window.scrollBy(1000,1000)");
    
    Thread.sleep(1000);
    
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //The below method will save the screen shot in d drive with name "screenshot.png"
    FileUtils.copyFile(scrFile1, new File("D:\\Selenium_Screenshot\\Screenshot2.png"));
    System.out.println("Screenshot of detail page of search result");
    Thread.sleep(1000);
    
    
    String[] links = null;
    int linksCount = 0;
    List<WebElement> linksize = driver.findElements(By.xpath("//a[@role = 'button']")); 
    linksCount = linksize.size();
    System.out.println("Total no of links Available: "+linksCount);
    links= new String[linksCount];
    System.out.println("Taking screenshots of "+linksCount+" PDFs: ");  
    // print all the links from webpage 
    for(int i=0;i<linksCount;i++)
    {
    links[i] = linksize.get(i).getAttribute("href");
  // System.out.println(all_links_webpage.get(i).getAttribute("href"));
    } 
    // navigate to each Link on the webpage
    
    for(int i=1;i<=linksCount;i++)
    
    {  
    	
    	driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/a["+ i +"]")).click();	
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in C drive with name "screenshot.png"
        FileUtils.copyFile(scrFile2, new File("D:\\Selenium_Screenshot\\ScreenshotPdfs["+ i +"].png"));
        System.out.println("Screenshot of pdf ["+ i +"]");
        Thread.sleep(1000);
        driver.navigate().back();
      
    }
    System.out.println("Screenshots are availabe here : \\ppumsv-Win16Jen\\Selenium_Screenshot");
}
}



     







	




