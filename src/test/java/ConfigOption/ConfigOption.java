package ConfigOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class ConfigOption{
	
	String lang[]= {"portuguese_br","russian","chinese_cn","chinese_tw","korean","japanese","italian","spanish","french","german"};
	String code[]= {"pt_BR","RU","zh_CN","zh_TW","KO","JA","IT","ES","FR","DE"};
	int langCount= lang.length;
	int i,j;
	
	public static WebDriver driver;
	
	@Test
	public void ConfigOptionFileDownlaod() throws IOException, InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		DateFormat df1 = new SimpleDateFormat("dd-MM-YYYY_HH-mm");
		Calendar calobj = Calendar.getInstance();
		String date = df1.format(calobj.getTime());
		
		for(i=0;i<langCount;i++)
		{
			for(j=i;j<=i;j++)
			{
				String downloadpath = "E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i];
				HashMap<String,Object> chromePref = new HashMap<String,Object>();
				chromePref.put("profile.default_content_settings.popups", 0);
				chromePref.put("download.default_directory", downloadpath);
				
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePref);
				driver=new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								
				Properties properties = new Properties();
				FileInputStream reader = new FileInputStream(System.getProperty("user.dir")+"\\data.properties");
				properties.load(reader);
				
				String file1 = properties.getProperty("file1");
				String file2 = properties.getProperty("file2");
				
				driver.get("http://rdweb.ptc.com/config.pro");
				driver.manage().window().maximize();
		
				System.out.println("------------Generating PMA full list-------------");
				generateFullList("//option[@value='PMA']");
				fileFunc(file2);
				
				/*****************************RENAMING FILE NAME******************************/
				WebElement submit = driver.findElement(By.xpath("(//input[@type='button'])[2]"));
				submit.click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("(//img[@src='Images/csv.gif'])[2]")).click();
				Thread.sleep(2000);	
				//Renaming file		
				File oldPMAfull =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\resultConfigPro.csv");
				File newPMAfull =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\Creo_PMA_config_options_full_list_"+code[i]+".csv");
				System.out.println("FILE NAME : "+newPMAfull);
				if(oldPMAfull.renameTo(newPMAfull)){
					System.out.println("File renamed");
				}
				else{
					System.out.println("Not renamed");
				}
				Thread.sleep(3000);
				
				/*****************************RENAMING FILENAME DONE******************************/
				
				System.out.println("------------Generating PMA comparison list-------------");
				fileComparion("//option[@value='PMA']", file1, file2);
				
				/*****************************RENAMING FILE NAME******************************/
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@type='button'])[2]")).click();
				Thread.sleep(2500);
				driver.findElement(By.xpath("(//img[@src='Images/csv.gif'])[2]")).click();
				Thread.sleep(2000);
				//rename
				File PMACompold =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\resultConfigPro.csv");
				File PMACompnew =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\Creo_PMA_config_options_comparison_list_"+code[i]+".csv");		 
				if(PMACompold.renameTo(PMACompnew)){
					System.out.println("File renamed");
				}
				else{
						System.out.println("Not renamed");
				}
				
				Thread.sleep(3000);
				/*****************************RENAMING FILE NAME DONE******************************/
				
				
				/******************************SIMULATE*****************************/
				System.out.println("------------Generating SIM full list-------------");
				generateFullList("//option[@value='MECHANICA']");
				fileFunc(file2);
				
				/***************************** RENAMING FILE NAME ******************************/
				driver.findElement(By.xpath("(//input[@type='button'])[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//img[@src='Images/csv.gif'])[2]")).click();
				Thread.sleep(3000);
				File oldSIMfull =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\resultConfigPro.csv");
				File newSIMfull =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\Creo_SIM_config_options_full_list_"+code[i]+".csv");	 
				if(oldSIMfull.renameTo(newSIMfull)){
				System.out.println("Renamed File Name");
					}else{
						System.out.println("Not renamed");
				}
				Thread.sleep(3000);
				/*****************************RENAMING FILE NAME DONE******************************/
				
				System.out.println("------------Generating SIM comparison list-------------");
				fileComparion("//option[@value='MECHANICA']", file1, file2);
				
				/***************************** RENAMING FILE NAME ******************************/
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@type='button'])[2]")).click();
				Thread.sleep(2500);
				driver.findElement(By.xpath("(//img[@src='Images/csv.gif'])[2]")).click();
				Thread.sleep(2000);
				File SIMCompold =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\resultConfigPro.csv");
				File SIMCompnew =new File("E:\\Projects\\Creo\\config_options\\files_"+date+"\\"+lang[i]+"\\Creo_SIM_config_options_comparison_list_"+code[i]+".csv");		 
				if(SIMCompold.renameTo(SIMCompnew)){
					System.out.println("Renamed File Name");
					}
				else{
					System.out.println("Not renamed");
					}
				}
			
			/*****************************RENAMING FILE NAME DONE******************************/
			System.out.println("-----PROCESS DONE FOR-------::"+lang[i]);
			driver.close();	
		}
		System.out.println("--------------------------BROWSER CLOSED--------------------------------");
	}
	
	public void fileFunc(String file2){
		
		System.out.println("Selecting Full List");
		Select fulllistversion = new Select(driver.findElement(By.id("version")));
		driver.findElement(By.id("version")).click();
		System.out.println("DropDown Successfull");
		fulllistversion.selectByVisibleText(file2);
		driver.findElement(By.id("version")).click();
		System.out.println("File Selection DONE");
	}
	
	public void generateFullList(String data) throws InterruptedException{
		
		WebElement app = driver.findElement(By.xpath(data));
		app.click();
		WebElement lang1 = driver.findElement(By.xpath("//*[contains(text(),'"+lang[i]+"')]"));
		lang1.click();
		WebElement go = driver.findElement(By.xpath("(//input[@type='button'])[1]"));
		go.click();
		WebElement fulllist = driver.findElement(By.xpath("//a[@title='Full option list']"));
		fulllist.click();
	}
		
	public void fileComparion(String data,String file1, String file2) throws InterruptedException
	{
		driver.findElement(By.xpath(data)).click();
		driver.findElement(By.xpath("//*[contains(text(),'"+lang[i]+"')]"));
		driver.findElement(By.xpath("(//input[@type='button'])[1]")).click();
		//Thread.sleep(2000);
		WebElement comparisonlist = driver.findElement(By.xpath("//a[@title='Option comparision']"));
		comparisonlist.click();
		//Thread.sleep(2000);
		
		Select prevversion = new Select(driver.findElement(By.name("version[]")));
		System.out.println("Selecting FIRST Comparison List");
		Thread.sleep(3000);
		driver.findElement(By.name("version[]")).click();
		prevversion.selectByVisibleText(file1);
		driver.findElement(By.name("version[]")).click();
		
		WebElement plus = driver.findElement(By.xpath("//img[@src='Images/plus.gif']"));
		plus.click();
		System.out.println("Click on Plus Item done");
		
		Select newversion = new Select(driver.findElement(By.xpath("(//*[@name='version[]'])[2]")));
		System.out.println("Selecting SECOND Comparison List");
		driver.findElement(By.xpath("(//*[@name='version[]'])[2]")).click();
		newversion.selectByVisibleText(file2);
		driver.findElement(By.xpath("(//*[@name='version[]'])[2]")).click();
	}
}
