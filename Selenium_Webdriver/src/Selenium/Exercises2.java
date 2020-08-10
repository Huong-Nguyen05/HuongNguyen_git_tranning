package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exercises2 {
	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",
				"/Users/thuhuong.nguyenthi/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://automationpractice.com/index.php");
		// product 1
		WebElement firstBlock = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]"));
		WebElement addToCartBtn = driver
				.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]"));
		HoverAndClick(driver, firstBlock, addToCartBtn);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\"Continue shopping\"]")));

		WebElement continueBtn = driver.findElement(By.xpath("//*[@title=\"Continue shopping\"]"));
		continueBtn.click();

		// product 2
		WebElement secondBlock = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[2]"));
		WebElement secondAddToCartBtn = driver.findElement(
				By.xpath("//*[@class=\"button ajax_add_to_cart_button btn btn-default\" and @data-id-product=\"2\"]"));
		HoverAndClick(driver, secondBlock, secondAddToCartBtn);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\"Continue shopping\"]")));

		continueBtn.click();

		// product 3
		WebElement thirdblock = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[3]"));
		WebElement thirdAddToCartBtn = driver.findElement(
				By.xpath("//*[@class=\"button ajax_add_to_cart_button btn btn-default\" and @data-id-product=\"3\"]"));
		HoverAndClick(driver, thirdblock, thirdAddToCartBtn);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\"Proceed to checkout\"]")));

		WebElement CheckoutBtn = driver.findElement(By.xpath("//*[@title=\"Proceed to checkout\"]"));
		CheckoutBtn.click();
	}

	public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();

		driver.quit();
	}
}
