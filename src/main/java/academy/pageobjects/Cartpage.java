 package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import academy.Abstactcomponents.Abstractcomponent;

public class Cartpage extends Abstractcomponent {
	@FindBy(css=".cartSection h3")
	List <WebElement> prodtitles;
	
	@FindBy(xpath="//button[text()='Checkout'] ")
	WebElement checkoutbtn;
	
WebDriver driver;
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public  Boolean verifyprod(String item) {
	      Boolean match=prodtitles.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
	      return match;
	}
	
	public Checkoutpage checkoutbtn() {
		checkoutbtn.click();
		Checkoutpage checkout=new Checkoutpage(driver);
		return checkout;
	}

}
