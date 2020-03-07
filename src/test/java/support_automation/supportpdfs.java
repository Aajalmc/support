package support_automation;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import java.util.List;

public class supportpdfs {

private int index;

@SuppressWarnings("unlikely-arg-type")
@Test
public void test()  throws InterruptedException, AWTException, IOException {
	
System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");

WebDriver driver = new ChromeDriver();

driver.manage().window().maximize();
driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920,1080)); 

//Implicit wait

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
capabilities.setCapability(ChromeOptions.CAPABILITY, options);

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


//Used Absolute Xapth as the Relative Xpath was not working - span[@class='iconlink-caption']

WebElement Browse = driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/div/div[2]/div/a/div"));

Browse.click();

Set<String> handles = driver.getWindowHandles();
String currentHandle = driver.getWindowHandle();
for (String handle : handles) {

 if (!handle .equals(currentHandle))
 {
     driver.switchTo().window(handle);
 }
}


//Search Through Filter By

((JavascriptExecutor) driver).executeScript("window.focus();");
WebDriverWait wait = new WebDriverWait(driver, 60);

//Now find the client input and set value
//Enter the product to be searched 
WebElement client = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Product\"]")));
//client.sendKeys("ThingWorx Navigate");

client.sendKeys(System.getProperty("Product"));

Thread.sleep(2000);

//Now find all the showing option   
List<WebElement> dropdownOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/ul/li/div")));

//Now select the first option
dropdownOptions.get(0).click();

//Select the release version

Select release = new Select( driver.findElement(By.xpath("//*[@id=\"Release\"]")));

release.selectByIndex(1);

//Select document type

//New code start


Select select = new Select(driver.findElement(By.xpath("//*[@id=\"DocType\"]")));
//Select select = new Select(selectList);
select.selectByVisibleText("Administration & Installation");
select.selectByVisibleText("Configuration Options");
select.selectByVisibleText("Developer Guides");
select.selectByVisibleText("Matrices");
select.selectByVisibleText("Product Calendar");
select.selectByVisibleText("Read This First / Release Notes");
select.selectByVisibleText("User and Reference Guides");

//driver.findElement(By.xpath("//*[@id=\"DocType\"]/option[i]")).sendKeys(Keys.ENTER);
//*[@id="DocType"]/option[3]
//*[@id="DocType"]/option[2]
//*[@id="DocType"]/option[8]

//*[@id="DocType"]/option[5]


//Select select = new Select(driver.findElement(By.xpath("//*[@id=\"DocType\"]")));
//WebElement option = select.getFirstSelectedOption();
//option.sendKeys("");
//String defaultItem = option.getText();
//System.out.println(defaultItem);



//End 


//WebElement Document_Type=driver.findElement(By.xpath("//*[@id=\"DocType\"]"));

//Select document =new Select(Document_Type);

//document.getAllSelectedOptions();

//List<WebElement> dropdown=document.getOptions();

//for(int i=0;i<dropdown.size();i++){

//String drop_down_values=dropdown.get(i).getText();

//System.out.println("dropdown values are "+drop_down_values);
//client.sendKeys(System.getProperty(drop_down_values));

driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[1]/form/div[7]/div/div[2]/button")).click();


System.out.println("Search successful");
Set<String> handles1 = driver.getWindowHandles();
String currentHandle1 = driver.getWindowHandle();
for (String handle : handles1) {

 if (!handle .equals(currentHandle1))
 {
     driver.switchTo().window(handle);
 }
}
   //Close pop -up
    driver.switchTo().frame("sc_footer");
    driver.switchTo().defaultContent();
    
    
    String TitleofPage=driver.getTitle();
	
	System.out.println(TitleofPage);
	
	if (TitleofPage.contains("Search"))
	{
	
		WebElement popupwin  =driver.findElement(By.xpath("/html/body/div[5]/div/div[5]/a[1]"));
		if (popupwin.isDisplayed())
		{
			popupwin.click();
			System.out.println("Pop up Window displayed");
		}

		else
		{
		System.out.println("Pop up Window not displayed");
		}

		
	}
	else
	{
		System.out.println("Browse documentation Icon not displayed");
	}

    
    //driver.findElement(By.xpath("//*[@id=\"ptcapp\"]/div[5]/div/div[5]/a[1]")).click();
    
    
    //Click on one week ago filter 
		
	/*System.out.println("Scroll Down Page");
    
    JavascriptExecutor js1 = (JavascriptExecutor) driver;

    js1.executeScript("window.scrollBy(0,7000)");
 
    WebElement oneweek = driver.findElement(By.xpath("//*[@id=\"LastModifiedTime_ChkGroup_One Month Ago - One Week Ago\"]"));
    
    if (oneweek.isEnabled() && oneweek.isDisplayed())
	{
		oneweek.click();
		System.out.println("Checkbox Element not Enable and displayed");
	}
	else
	{
		System.out.println("checkbox Element not Enable and displayed");
	}

    //Click on apply link
   
    WebElement ApplyButton=driver.findElement(By.xpath("//div[@id='ctl00_ctl35_g_cda8acf7_705f_490f_bdac_8c607fbd3e66_csr7']//span[@class='ui-button-text'][contains(text(),'Apply')]"));
    

	if (ApplyButton.isDisplayed() && ApplyButton.isEnabled())
	{
		ApplyButton.click();
		System.out.println("Apply button Enable and displayed");
	}

	else
	{
		System.out.println("Apply button not Enable and displayed");
	}
    */
    
    //Scroll Page left to right 
   
    //JavascriptExecutor jse = (JavascriptExecutor) driver;
  	//jse.executeScript("window.scrollBy(-500000,0)", "");
	

   //Mouse Hover on first element and take screenshot
 
    //WebElement ele = driver.findElement(By.xpath("/html/body/form/div[3]/div/span[2]/div[1]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/a"));
    
      Thread.sleep(1000);
      
      File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshotFile, new File("D:\\Selenium Screenshot\\Screenshot1.png"));
    
	  //Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2)).takeScreenshot(driver);
	  //ImageIO.write(fpScreenshot.getImage(),"PNG",new File("D:\\Selenium_Screenshot\\Screenshot1.png"));
	  System.out.println("Screenshot taken of the search result");
 
    
    //Click on the first search link
    
    driver.findElement(By.xpath("//*[@id=\"ctl00_ctl35_g_2e89b8bd_7b92_4179_882c_cdd0b65cdad5_csr3_itemTitleLink\"]")).click();
 
    //Thread.sleep(1000);
    
    //JavascriptExecutor js3 = (JavascriptExecutor) driver;
    //js3.executeScript("window.scrollBy(1000,1000)");
    
  
    Screenshot fpScreenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2)).takeScreenshot(driver);
    ImageIO.write(fpScreenshot1.getImage(),"PNG",new File("D:\\Selenium Screenshot\\Screenshot2.png"));
    
    
    //Screenshot fpScreenshot1 = new AShot().shootingStrategy(ShootingStrategies.scaling(1)).takeScreenshot(driver);
    //ImageIO.write(fpScreenshot1.getImage(),"PNG",new File("C:\\Users\\ntiwari\\Desktop\\Selenium Screenshot\\Screenshot2.png"));
	System.out.println("Screenshot of detail page of search result");
    //Thread.sleep(1000);
    
  
}

}
    
    











	




