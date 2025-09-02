 package academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.Abstactcomponents.Abstractcomponent;

public class Checkoutpage extends Abstractcomponent{
	WebDriver driver;
	public  Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By dropresult =By.cssSelector(".ta-results");//dropdown
	
@FindBy(css="a.btnn")
WebElement submit;

@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement country;

@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
WebElement selectedcountry;

By submit1=By.cssSelector("a.btnn");

public void selectcountry(String countryname) {
	country.sendKeys(countryname);
	waitforelement(dropresult);
	selectedcountry.click();
}

public Confirmpage submitorder() throws InterruptedException {
     Thread.sleep(300);
     JavascriptExecutor js=(JavascriptExecutor)driver;
     js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);
     waitforelement(submit1);
     submit.click();
     Confirmpage confirm=new Confirmpage(driver);
     return confirm;
}
}
