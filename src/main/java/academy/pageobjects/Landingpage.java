 package academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.Abstactcomponents.Abstractcomponent;

public class Landingpage extends Abstractcomponent{
	WebDriver driver;//from constructor value of driver is assigned to local var
	public Landingpage(WebDriver driver) {
		super(driver);//with super keyword we send values from child to parent , which will be caught by constructor
		//constructor used for initializations
		this.driver=driver;//assigning driver to local class var driver,as the value of driver is only method scoped.
		PageFactory.initElements(driver, this);//take driver as argument and initialize this i.e, local var
		
	}
	
//	WebElement useremail=driver.findElement(By.cssSelector("input[id='userEmail']"));
	//Page factory:reduce the syntax of webelement
	@FindBy(css = "input[id='userEmail']")
	WebElement useremail;
	
//	driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Neethu569");
	@FindBy(css="input[id='userPassword']")
	WebElement password;
	
//    driver.findElement(By.cssSelector("input[name='login']")).click();
    @FindBy(css="input[name='login']")
	WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
    WebElement errormsg;
    
    public String geterror() {
    	waitforwebelement(errormsg);
    	String error=errormsg.getText();
    	return error;
    }
    
    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");

    }
    public ProductCatalogue logonapp(String email,String pass) {
    	useremail.sendKeys(email);
    	password.sendKeys(pass);
    	submit.click();
    	 ProductCatalogue productcatalogue=new ProductCatalogue(driver);
    	 return productcatalogue;
    }
   
	}
