package rahul.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import academy.pageobjects.Cartpage;
import academy.pageobjects.Checkoutpage;
import academy.pageobjects.Confirmpage;
import academy.pageobjects.Landingpage;
import academy.pageobjects.Orderpage;
import academy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahul.Testcomponents.Basetest;
import rahul.data.datareader;
public class StandAloneorg extends Basetest {
	public String item="ZARA COAT 3";
	
	@Test(dataProvider="getdata",groups= {"purchase"})
//	public void Submitorder(String email,String password,String item)  if we use normal data provider without hashmap
	public void Submitorder(HashMap<String,String> input) throws InterruptedException, IOException{
        ProductCatalogue productcatalogue=landingpage.logonapp(input.get("email"), input.get("password"));//input.get used to get the property from hashmap
//        -------------------------------------------------------------
        //we are adding explicit wait and getting the product list 
       List <WebElement> products= productcatalogue.getproductlist();
 //      --------------------------------------------------------------
       //we are finding the required item and add to cart 
       //click on add to cart
       //wait for toast msg and the spinner all inside below mwthod
       productcatalogue.addtocart(input.get("item"));
//---------------------------------------------------------------------------------       
      //click on header where cart btn is present we will not add to product catalogue as it os commoon we add in abstract
       Cartpage cartpage=productcatalogue.cartbtn(); //we should not create more objects it makes overhead, inorder to avoid that we find endpoints and create t
      
//-----------------------------------------------------------------------------
       //things will go on in cart page now,i.e, we are verifying whether the item is present or not
       Boolean match=cartpage.verifyprod(input.get("item"));
      //no assertions should be written in pom files
      Assert.assertTrue(match);
      
      Checkoutpage checkout=cartpage.checkoutbtn(); 
      //-------------------------------in checkout page
      
      //select country
      checkout.selectcountry("india");
           
   //after clicking submit land on confirmation page
      Confirmpage confirm=checkout.submitorder();
      String confirm1=confirm.verifytext();
      //verify text
      Assert.assertTrue(confirm1.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
 	}
	
	
	
	//running dependency test
	@Test(dependsOnMethods={"Submitorder"})//i.e, the method on which this depends run first
	
	public void orderhistroy() {
		ProductCatalogue productcatalogue=landingpage.logonapp("neethushreehk@gmail.com", "Neethu569");
		Orderpage orderpage=landingpage.gotoorderpage();//abstract component can be accessed  from any class.
		Assert.assertTrue(orderpage.verifyorder(item));//verifying if order is present in 
	}
	
	
	 
	@DataProvider
	public Object[][] getdata() throws IOException {
		
		
		//3.read jsondata 
		List<HashMap<String,String>> data=getjsondatatomap(System.getProperty("user.dir") + "\\src\\test\\java\\rahul\\data\\purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		1. using data matrix
		//return new Object[][] {{"neethushreehk@gmail.com","Neethu569","ZARA COAT 3"},{"swathi@gmail.com","swathi123","ADIDAS ORIGINAL"}}; normal data matrix method
		
		
		//2.using hasmap
		//if we mention object,object, we are mentioning that data type can be any in hash map
//		HashMap<String,String> map= new HashMap<String,String>();
//		map.put("email", "neethushreehk@gmail.com");
//		map.put("password", "Neethu569");
//		map.put("item", "ZARA COAT 3");
//		 
//		HashMap<String,String> map1= new HashMap<String,String>();
//		map1.put("email", "swathi@gmail.com");
//		map1.put("password", "swathi123");
//		map1.put("item", "ADIDAS ORIGINAL");
//		//firstly we passed the data through multidimmensional array, if we have more datasets like 15 to 20,
//		//readability decreases as we have to accept in method required also.
//		return new Object[][] {{map},{map1}};
	}
}

