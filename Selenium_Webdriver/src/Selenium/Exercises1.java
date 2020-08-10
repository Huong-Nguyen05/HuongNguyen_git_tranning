package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercises1 {
	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",
				"/Users/thuhuong.nguyenthi/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('search_query_top').value='No product'");
		driver.findElement(By.id("searchbox")).submit();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String productName = driver.findElement(By.xpath("//*[@id='center_column']//*[@class='alert alert-warning']"))
				.getText().trim();
		if (productName.equals("No product"))
			System.out.println("The result is correct");
		else
			System.err.println("The result is incorrect");
		driver.quit();

	}
}