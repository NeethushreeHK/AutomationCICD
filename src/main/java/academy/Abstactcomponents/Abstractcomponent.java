package academy.Abstactcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import academy.pageobjects.Cartpage;
import academy.pageobjects.Orderpage;

public class Abstractcomponent {
	WebDriver driver;
	public Abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//button[text()='  Cart ']")
WebElement cartbtn;

@FindBy(xpath="//button[contains(text(),'ORDERS')]")
WebElement Orderheader;

	public void waitforelement(By findBy) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitforwebelement(WebElement ele) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitforgo(WebElement spin) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.invisibilityOf(spin));
	}
	
	public Cartpage cartbtn() {
	      cartbtn.click();
	       Cartpage cartpage=new Cartpage(driver);
	       return cartpage;

	}
	
	
	public Orderpage gotoorderpage() {
		Orderheader.click();
		Orderpage orderpage=new Orderpage(driver);
		return orderpage;
	}
}
