package academy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.Abstactcomponents.Abstractcomponent;

public class Confirmpage extends Abstractcomponent {
WebDriver driver;
	public Confirmpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmtext;
	public String verifytext() {
		return confirmtext.getText();
	}
}
