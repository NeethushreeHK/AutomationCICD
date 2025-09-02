package rahul.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import academy.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {
	public WebDriver driver;
	public Landingpage landingpage;
public WebDriver initialize() throws IOException {
	
	//properties class- read global properties and decide on which browser we need to run.
	//we should add .properties to the global file to access them through property class
	Properties prop= new Properties();
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//academy//Resources//Globaldata.properties");
	//using user.dir, we need not to hard code the system path, will automatically fetch it
	prop.load(fis);//to load the file we need to 
	
	//to read data from Maven command in terminal
	String browsername=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	
//	String browsername=prop.getProperty("browser");
	
	//using global properties we are changing the browsers
	
	if(browsername.contains("chrome")) {
		ChromeOptions options = new ChromeOptions();
		

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		Map<String, Object> profile = new HashMap<String, Object>();
		profile.put("password_manager_leak_detection", false);
		prefs.put("profile", profile);
		options.setExperimentalOption("prefs", prefs);
		
		//for headless execution of chrome
		if(browsername.contains("headless")){
			options.addArguments("headless");
		}
		
	 WebDriverManager.chromedriver().setup(); 
	 options.addArguments("--window-size=1440,900");
      driver = new ChromeDriver(options);  
      driver.manage().window().setSize(new Dimension(1440,900));//to ensure it is opened in maximize mode in headless mode.
    
	}
	else if (browsername.contains("firefox")) {
		 driver = new FirefoxDriver();
		
		
	}
    else if (browsername.contains("edge")) {
    	 EdgeOptions options = new EdgeOptions();
         // Preferences Map
         Map<String, Object> prefs = new HashMap<>();
         prefs.put("credentials_enable_service", false);
         prefs.put("password_manager_enabled", false);
         Map<String, Object> profile = new HashMap<>();
         profile.put("password_manager_leak_detection", false);
         prefs.put("profile", profile);
         // Apply prefs
         options.setExperimentalOption("prefs", prefs);

         // Setup and Launch Edge
         WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver(options);
    }
	 driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
     System.out.println("Driver initialized: " + driver);
     return driver;
}
@BeforeMethod(alwaysRun=true)
public Landingpage launchapp() throws IOException {
	driver=initialize();
	 landingpage=new Landingpage(driver);
    landingpage.goTo();
    return landingpage;
}

@AfterMethod(alwaysRun=true)
public void teardown() {
	driver.close();
}
 
//readjson data
public List<HashMap<String, String>> getjsondatatomap(String filepath) throws IOException {
	//read json to string
	String jsoncontent = FileUtils.readFileToString(
	        new File(filepath), 
	        StandardCharsets.UTF_8);
		
		//convert data to hashmap, instal new dependency Jackson databind
		ObjectMapper mapper=new ObjectMapper();
		//we are aking in which way it should return the data
		  List<HashMap<String, String>> data =  mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
		  return data;
}

//screenshot
public String getscreenshot(String testcasename,WebDriver driver) throws IOException {
	
	TakesScreenshot pic = (TakesScreenshot) driver;
	File src=pic.getScreenshotAs(OutputType.FILE);
	File dest= new File("C:\\Users\\Shree\\Pictures\\Saved Pictures"+testcasename+".png");  
	FileUtils.copyFile(src, dest);
	return "C:\\Users\\Shree\\Pictures\\Saved Pictures"+testcasename+".png";
}
}
