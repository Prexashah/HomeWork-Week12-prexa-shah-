package utility;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {
    /**
     * This method will click on element
     *
     * @param by
     */
    public void clickOnElement(By by) {

        driver.findElement(by).click();
    }

    /**
     * This method will send text on element
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * This method will get element
     */
    public WebElement getElement(By by){
        return driver.findElement(by);
    }
    /**
     * This method will find list of elements
     */

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }
//************************* Alert Methods *****************************************************

    /**
     * This method will switch to alert
     */
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    /**
     * This method will accept to alert
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }


//*****************************Select method******************************************************

    public void selectByValueFromDropDown(By by, String value){
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select Class
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    public void selectByVisibleText(By by,String text){
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select Class
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);

    }
    public void selectByIndex(By by ,int index) {
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select Class
        Select select = new Select(dropDown);
        select.selectByIndex(index);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.xpath("//a[text()='" + menu + "']"));

    }

    public void validateResponse(String msg, String expectedText, String actualText){
        Assert.assertEquals(msg,expectedText,actualText);
    }

    //**********************************Action method********************************************
public void moveMouseToElement(WebElement e){
    Actions action=new Actions(driver);
    action.moveToElement(e).build().perform();
}

    public void moveMouseAndClickOnElement(WebElement e){
        Actions action= new Actions(driver);
        action.moveToElement(e).click().build().perform();
    }
//**************
}
