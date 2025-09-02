 package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import academy.Abstactcomponents.Abstractcomponent;
//whenever u find a resuable code, make it a proper class and reuse it
public class ProductCatalogue extends Abstractcomponent{
	WebDriver driver;//from constructor value of driver is assigned to local var
	public ProductCatalogue(WebDriver driver) {
		//constructor used for initializations
		super(driver);//every child class has to provide the value or it willl give error
		this.driver=driver;//assigning driver to local class var driver,as the value of driver is only method scoped.
		PageFactory.initElements(driver, this);//take driver as argument and initialize this i.e, local var
		
	}
	
	//Page factory:reduce the syntax of webelement
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
By product=By.cssSelector(".mb-3");
By addtocart=By.cssSelector(".card-body button:last-of-type");
By toastmsg =By.cssSelector("#toast-container");
    //action methods

//getlist of products
	public List<WebElement> getproductlist() {
		waitforelement(product);
		return products;
	}
	
	//filter based on name
   public WebElement getproductbyname(String item) {
	   WebElement prod =getproductlist().stream().filter(product ->product.findElement(By.tagName("b"))
				.getText().equals(item)).findFirst().orElse(null);
	   return prod;
   }
   
   //click onadd to cart btn of product
   public void addtocart(String productname) {
	   WebElement prod= getproductbyname(productname);
	   prod.findElement(addtocart).click();
	   waitforelement(toastmsg);
	   waitforgo(spinner);
   }
	}
