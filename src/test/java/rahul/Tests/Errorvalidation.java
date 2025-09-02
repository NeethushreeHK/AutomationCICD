package rahul.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import academy.pageobjects.Cartpage;
import academy.pageobjects.Checkoutpage;
import academy.pageobjects.Confirmpage;
import academy.pageobjects.ProductCatalogue;
import rahul.Testcomponents.Basetest;
import rahul.Testcomponents.Retry;

public class Errorvalidation extends Basetest {
	@Test(groups= {"Errorvalidation"},retryAnalyzer=Retry.class)//we should add retry analzer if we doubt that this test would fail b9
	public void Submitorder() throws InterruptedException, IOException{
		String item="ZARA COAT 3";
		
        ProductCatalogue productcatalogue=landingpage.logonapp("neethushreehk@gmail.com", "Neethu5690");
      Assert.assertEquals("Incorrect email or password.", landingpage.geterror());
 	}
}
