package uitests.smoke;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;

public class SampleTestClass extends BaseTest
{
    private WebElement element;

    @Parameters({ "expected_text" })
    @Test(groups = { "Smoke", "SampleModule" })
    public void testMethod(String expected_text)
    {
        element = new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        element.sendKeys("Hello There!" + Keys.RETURN);
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        /*element = driver.findElement(By.name("btnK"));
        element.click();
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/
        Assert.assertTrue(driver.getPageSource().contains(expected_text), "Expected text not found on page!");
    }
}
