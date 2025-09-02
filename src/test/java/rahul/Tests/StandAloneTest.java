package rahul.Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import academy.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {
//    	String[] item= {"ZARA COAT 3","ADIDAS ORIGINAL"};
    	String item="ZARA COAT 3";
        WebDriverManager.chromedriver().setup(); // Sets up ChromeDriver, it helps in avoiding errors due to version change , rather it provides the 
        //browser version that matches installed one
        WebDriver driver = new ChromeDriver();  
        Landingpage landingpage=new Landingpage(driver);
        // Launches Chrome browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("neethushreehk@gmail.com");
        driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Neethu569");
        driver.findElement(By.cssSelector("input[name='login']")).click();
        //wait until products load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        //taking products to list
        List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
        //using streams instead of for loop
        WebElement prod =products.stream().filter(product ->product.findElement(By.tagName("b"))
        				.getText().equals(item)).findFirst().orElse(null);
      prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
      // we have to wait after adding item to the cart as we will get toast msg saying that product is added
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
      //another black screen appear
      //using the "invisibilityOf" condition improves perfromance i.e make it fast rather than "invisibilityOfElementLocated" is slow
      wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
      driver.findElement(By.xpath("//button[text()='  Cart ']")).click();
      
      //verifying whether the items added in cart are actually present in it
      List <WebElement> cartprod=driver.findElements(By.cssSelector(".cartSection h3"));
      //do not use filter to cehck condition as it provide the array back, we do not need, so use anymatch to check only the condition
      Boolean match=cartprod.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
//      Arrays.stream(item)
//      .allMatch(it -> cartprod.stream().anyMatch(p -> p.getText().equalsIgnoreCase(it)));
      Assert.assertTrue(match);
      driver.findElement(By.xpath("//button[text()='Checkout'] ")).click();
      //after clicking on checkout we get the checkout page, we need to fil the country input box andd click order
      driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
      //giving wait as option is displayed
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
      List <WebElement> drop=driver.findElements(By.cssSelector("button.ta-item span"));
      Optional<WebElement> options=drop.stream().filter(p->p.getText().equalsIgnoreCase("India")).findFirst();
      options.get().click();
      //place order btn click, wee need to scroll inorder to find it and also we need to give delay before scrolling as it will give error or else.
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
      //scroll
      WebElement btn= driver.findElement(By.cssSelector("a.btnn"));
      Thread.sleep(300);
      JavascriptExecutor js=(JavascriptExecutor)driver;
      js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
      //click on place orderbtn
      WebElement placeorder = wait.until(ExpectedConditions.elementToBeClickable(
          By.cssSelector("a.btnn")));
      placeorder.click();
//      driver.findElement(By.cssSelector("a.btnn")).click();
      //confirm msg
 
      String confirm=driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
   
}
