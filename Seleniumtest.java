package com.SeleniumPractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import junit.framework.Assert;

public class Seleniumtest extends Baseclass {
	@FindBy(how=How.XPATH, using="//span[text()='Revenue Calculator']")
	public WebElement revenuecollector;


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdrvier.chrome.driver", "./driver/chromedriver.exe");
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);


		//task1: Navigating to FitPeo homepage
		driver.get("https://www.fitpeo.com/");

		//Task2: Navigate to Revenue calculator page
		TimeUnit.SECONDS.sleep(3);
		WebElement revenuecollector = driver.findElement(By.xpath("//div[text()='Revenue Calculator']"));
		revenuecollector.click();

		//Task3: Scrolling to revenue calculator slider
		TimeUnit.SECONDS.sleep(3);
		//WebElement slider = driver.findElement(By.xpath("//span[@data-index='0']"));
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		actions.scrollToElement(slider).build().perform();


		//Task4: adjust slider value to 820 and validating the input text is updated as same
		//I couldn't able to find the Xoffset value to set for 820, so using 470 for validation
		actions.clickAndHold(slider).moveByOffset(41, 0).release().perform();
		TimeUnit.SECONDS.sleep(3);
		WebElement inputtext = driver.findElement(By.xpath("//input[@type='number']"));
		String inputtextvalue = inputtext.getAttribute("value");
		if(inputtextvalue.equals("470")) {
			System.out.println("Slider is successfully adjusted");
		}
		else {
			System.out.println("slider is not adjusted succeaafully");
		}

		//Task5 & 6: Updating the text value to 560 and validating slider value is updated as same as the value entered
		TimeUnit.SECONDS.sleep(3);
		inputtext.click();
		inputtext.sendKeys(Keys.CONTROL + "a");
		inputtext.sendKeys(Keys.BACK_SPACE);
		TimeUnit.SECONDS.sleep(3);
		actions.click(inputtext).sendKeys("560").build().perform();
		String slidervalue = slider.getAttribute("value");
		if(slidervalue.equals("560")) {
			System.out.println("Slider is updated as value entered");
		}
		else {
			System.out.println("Slider is not updated to value entered");
		}

		//Task7: selecting check boxes
		TimeUnit.SECONDS.sleep(3);
		inputtext.click();
		inputtext.sendKeys(Keys.CONTROL + "a");
		inputtext.sendKeys(Keys.BACK_SPACE);
		TimeUnit.SECONDS.sleep(3);
		actions.click(inputtext).sendKeys("820").build().perform();
		WebElement checkbox1 = driver.findElement(By.xpath("//p[text()='CPT-99091']//following-sibling::label"));
		checkbox1.click();
		WebElement checkbox2 = driver.findElement(By.xpath("//p[text()='CPT-99453']//following-sibling::label"));
		WaitForElement(ExpectedConditions.elementToBeClickable(checkbox2));
		checkbox2.click();
		WebElement checkbox3 = driver.findElement(By.xpath("//p[text()='CPT-99454']//following-sibling::label"));
		WaitForElement(ExpectedConditions.elementToBeClickable(checkbox3));
		checkbox3.click();
		WebElement checkbox4 = driver.findElement(By.xpath("//p[text()='CPT-99474']//following-sibling::label"));
		WaitForElement(ExpectedConditions.elementToBeClickable(checkbox4));
		checkbox4.click();
		
		//Task8: 8.	Validate Total Recurring Reimbursement & Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.	
		String totalvalue = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'])[4]")).getText();
		String Actulavalue = totalvalue;
		String Expectedvalue = "Total Recurring Reimbursement for all Patients Per Month:";
		Assert.assertTrue(Actulavalue.contains(Expectedvalue));
				
	}

}
