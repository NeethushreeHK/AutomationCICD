package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.Abstactcomponents.Abstractcomponent;

public class Orderpage extends Abstractcomponent{
	WebDriver driver;
	public Orderpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;//assigning driver to local class var driver,as the value of driver is only method scoped.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> ordertitles;
	
	public Boolean verifyorder(String item) {
		Boolean match=ordertitles.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
		return match;
	}
	
}
