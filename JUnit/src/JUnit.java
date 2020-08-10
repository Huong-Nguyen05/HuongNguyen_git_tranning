import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnit {
public static WebDriver driver;
	
	@BeforeClass
	public static void beforeClass () {
		System.setProperty("webdriver.chrome.driver",
				"/Users/thuhuong.nguyenthi/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void afterClass () {
 		driver.quit();
	}
	
	@Before
	public void beforeMethod () {
		driver.get("http://automationpractice.com/index.php");
	}
	
	@After
	public void afterMethod () {
 		driver.close();
	}

	public static void main(String[] args) throws Exception {
		JUnit test = new JUnit();
		JUnit.beforeClass();
		test.beforeMethod();
		
		test.testCart();

 		test.afterMethod();
 		JUnit.afterClass();
	}

	@Test
	public void testCart () throws Exception {
		
		Thread.sleep(2000);
		
		// input search
		driver.findElement(By.id("search_query_top")).sendKeys("Faded Short Sleeve T-shirts");
		driver.findElement(By.name("submit_search")).click();
		
		// get the first result
		List<WebElement> results = driver.findElements(By.cssSelector(".product_list .product-container .product-image-container .product_img_link"));
		WebElement ahref = results.get(0);
		ahref.click();
		Thread.sleep(1000);
		
		// get product name
		WebElement h1Name = driver.findElement(By.cssSelector(".pb-center-column h1"));
		String productName = h1Name.getText().trim();
		int quantity = 2;
		
		// get price
		String unitPrice = driver.findElement(By.cssSelector("#buy_block .box-info-product .content_prices .price")).getText();
		
		// add quantity
		WebElement buttonUp = driver.findElement(By.cssSelector("#quantity_wanted_p .product_quantity_up"));
		buttonUp.click();
		
		// add to cart
		WebElement buttonAdd = driver.findElement(By.cssSelector("#add_to_cart button"));
		buttonAdd.click();
		
		Thread.sleep(2000);
		
		// goto cart
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		Thread.sleep(1000);
		
		// get name
		List<WebElement> nameElements = 
				driver.findElements(By.cssSelector("#cart_summary .cart_description .product-name"));
		String actualName = nameElements.get(0).getText().trim();
		
		// get quantity
		List<WebElement> quantityElements = 
				driver.findElements(By.cssSelector("#cart_summary .cart_quantity .cart_quantity_input"));
		String actualQuantity = quantityElements.get(0).getAttribute("value");
		
		// get unit price
		List<WebElement> unitPriceElements = 
				driver.findElements(By.cssSelector("#cart_summary .cart_unit .price"));
		String actualUnitPrice = unitPriceElements.get(0).getText().trim();
		
		// get total price
		List<WebElement> totalPriceElements = 
				driver.findElements(By.cssSelector("#cart_summary .cart_total .price"));
		String actualTotalPrice = totalPriceElements.get(0).getText().trim();
		
		Assert.assertEquals(productName, actualName);
		Assert.assertEquals(quantity + "", actualQuantity);
		Assert.assertEquals(unitPrice, actualUnitPrice);
		
		float unitPriceFloat = Float.parseFloat(actualUnitPrice.replace("$", ""));
		float totalPriceFloat = Float.parseFloat(actualTotalPrice.replace("$", ""));
		
 		float totalPriceExpected = unitPriceFloat * quantity;
 		Assert.assertEquals(totalPriceExpected+"", totalPriceFloat+"");
 		
		
	}
	
}

