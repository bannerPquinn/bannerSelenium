package uitests.smoke;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.SqlHandler;
import utils.TestCaseDetails;

public class SampleTestClass  extends BaseTest 
{
    private WebElement element;

    @Parameters({ "expected_text" })
    @Test(groups = { "Regression"})
    //Selenium test with example testng parameters
    public void testMethod(String expected_text) throws InterruptedException
    {
        element = new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        Thread.sleep(5000);
        element.sendKeys("Hello There!" + Keys.RETURN);
        Thread.sleep(5000);
        Assert.assertTrue(driver.getPageSource().contains(expected_text), "Expected text not found on page!");
    }  
    @Test(groups = { "Smoke" }, dataProvider = "sqlHandler", dataProviderClass = SqlHandler.class)
    //first test of using custom sql dataprovider
    public void testSqlMethod(TestCaseDetails a)
    {
    	//System.out.println("ID " + id + " first name = " + FirstName + " Last Name " + LastName);
    	//System.out.println("Hello World");		
    		Map rowDetails = a.detail;
    		
    		System.out.println("ID is " + rowDetails.get("ID") +
    						   " First Name is " + rowDetails.get("FirstName") +
    						   " Last Name is " + rowDetails.get("LastName"));
    		
    	
    }
}
