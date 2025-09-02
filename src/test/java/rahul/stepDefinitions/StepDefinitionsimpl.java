package rahul.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import academy.pageobjects.Cartpage;
import academy.pageobjects.Checkoutpage;
import academy.pageobjects.Confirmpage;
import academy.pageobjects.Landingpage;
import academy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahul.Testcomponents.Basetest;

public class StepDefinitionsimpl extends Basetest{
	public Landingpage landingpage;
	public ProductCatalogue productcatalogue;//declaring publically
	public Confirmpage confirm;
	@Given("I landed on Ecommerce page")//matching pattern
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingpage =launchapp();
	}
	@Given("^Logged in with username (.+) and password (.+)$")//adding (.+) regular expression to match the pattern
	public void Logged_in_with_username(String name,String password) {
	 productcatalogue=landingpage.logonapp(name,password);
	}
	@When("^Add the product (.+)$")
	public void Add_The_Product(String prodname) {
		List <WebElement> products= productcatalogue.getproductlist();
		productcatalogue.addtocart(prodname);
	}
 @When("^Checkout (.+) and submit order$")
 public void checkout_the_submitorder(String prodname) throws InterruptedException {
	 Cartpage cartpage=productcatalogue.cartbtn();
	 Boolean match=cartpage.verifyprod(prodname);
	 Assert.assertTrue(match);
	 Checkoutpage checkout=cartpage.checkoutbtn(); 
	 checkout.selectcountry("india");
	 confirm=checkout.submitorder();
 }
 @Then("{string} message is displayed on confirmpage.")//for static value comparision {string} will be in run time
 public void message_displayed(String string) {
	 String confirm1=confirm.verifytext();
	 Assert.assertTrue(confirm1.equalsIgnoreCase(string));
	 driver.close();
 }
 
 @Then("{string} message is displayed.")
 public void error(String string) {
	 Assert.assertEquals(string, landingpage.geterror());
 }
}
