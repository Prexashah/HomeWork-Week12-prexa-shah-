package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        WebElement laptopsAndNoteBooks = getElement(By.xpath("//a[text() = 'Laptops & Notebooks']"));
        moveMouseAndClickOnElement(laptopsAndNoteBooks);

        //1.2 Click on “Show All Laptops & Notebooks”
        selectMenu(getTextFromElement(By.xpath("(//a[@class ='see-all'])[2]")));
        //1.3 1.3 Select Sort By "Price (High > Low)"
        //1.4 Verify the product price in high and low order
        //Before Filter
        List<WebElement> beforeFilterPrice = getElements(By.xpath("//p[@class='price']"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        String[] priceArray;
        //Split element to exclude child element text, replace $ symbol and ,(comma) with "", covert into double
        for (WebElement p : beforeFilterPrice) {
            //System.out.println(p.getText());
            priceArray = p.getText().split("\n");
            System.out.println(priceArray[0]); //for debug purpose
            String number = priceArray[0].replace("$", "");
            String num = number.replace(",", "");
            beforeFilterPriceList.add(Double.valueOf(num));
        }
        Collections.sort(beforeFilterPriceList);
        Collections.reverse(beforeFilterPriceList);

        //Filter price High-Low
        selectByVisibleText(By.id("input-sort"), "Price (High > Low)");

        //After filter
        List<WebElement> afterFilterPrice = getElements(By.xpath("//p[@class='price']"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        String[] priceArrayAfterFilter;
        //Split element to exclude child element text, replace $ symbol and ,(comma) with "", covert into double
        for (WebElement p : afterFilterPrice) {
            priceArrayAfterFilter = p.getText().split("\n");
            String number = priceArrayAfterFilter[0].replace("$", "");
            String num = number.replace(",", "");
            afterFilterPriceList.add(Double.valueOf(num));

        }

        Assert.assertEquals("The products are not displayed in High to Low order.", beforeFilterPriceList, afterFilterPriceList);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {

        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        WebElement laptopsAndNoteBooks = getElement(By.xpath("//a[text() = 'Laptops & Notebooks']"));
        moveMouseAndClickOnElement(laptopsAndNoteBooks);

        //2.2 Click on “Show All Laptops & Notebooks”
        selectMenu(getTextFromElement(By.xpath("(//a[@class ='see-all'])[2]")));

        //2.3 Select Sort By "Price (High > Low)"

        selectByVisibleText(By.id("input-sort"),"Price (High > Low)");

        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[text() ='MacBook']"));

        //2.5 Verify the text “MacBook”
        Assert.assertEquals("incorrect product", "MacBook", getTextFromElement(By.xpath("//div[@class='col-sm-4']/h1")));

        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));

        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        String actualText = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String expectedText = "Success: You have added MacBook to your shopping cart!";
        Assert.assertTrue(actualText.contains(expectedText));

        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[text()='shopping cart']"));

        //2.9 Verify the text "Shopping Cart"
        Assert.assertTrue("User is on incorrect page.", getTextFromElement(By.xpath("//div[@id='content']/h1")).contains("Shopping Cart"));


        //2.10 Verify the Product name "MAcBook"
        String actualText1 = getTextFromElement(By.linkText("MacBook"));
        String expectedText1 = "MacBook";
        Assert.assertEquals(actualText1,actualText1);

        //2.11 Change Quantity "2"
        getElement(RelativeLocator.with(By.xpath("//input[@class='form-control']")).below(By.xpath("//td[text()='Quantity']"))).clear();
        driver.findElement(RelativeLocator.with(By.xpath("//input[@class='form-control']")).below(By.xpath("//td[text()='Quantity']"))).sendKeys("2");


        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@class='btn btn-primary']"));

        //2.13 Verify the message “Success: You have modified your shopping cart!”
        actualText = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        expectedText = "Success: You have modified your shopping cart!";
        Assert.assertTrue(actualText.contains(expectedText));

        //2.14 Verify the Total £737.45
        String total=getTextFromElement(By.xpath("//tbody/tr/td[6]"));
        String expectedTotal="£737.45";
        Assert.assertEquals(total,expectedTotal);

        //2.15 Click on “Checkout” button
        clickOnElement(By.linkText("Checkout"));

        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout",getTextFromElement(By.xpath("//div[@id='content']/h1")));//div[@id='content']/h1

        //2.17 Verify the Text “New Customer"
        Assert.assertEquals("New Customer",getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']")));

        //2.18 Click on “Guest Checkout” radio button
        List<WebElement> radioButton=getElements(By.xpath("//input[@name='account']"));
        for(WebElement r: radioButton){
            if(r.getText().equalsIgnoreCase("Guest Checkout")){
                r.click();
                break;
            }
        }

        //2.19 Click on “Continue” tab
        clickOnElement(By.id("button-account"));

        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"),"Marry");
        sendTextToElement(By.id("input-payment-lastname"),"Roshan");
        sendTextToElement(By.id("input-payment-email"),"mroshan@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"),"0778899665");
        sendTextToElement(By.id("input-payment-password"),"abcd123");
        sendTextToElement(By.id("input-payment-confirm"),"abcd123");
        sendTextToElement(By.id("input-payment-address-1"),"1,Richardson Street");
        sendTextToElement(By.id("input-payment-city"),"London");
        sendTextToElement(By.id("input-payment-postcode"),"HA1 5SW");
        selectByVisibleText((By.id("input-payment-country")),"United Kingdom");
        selectByVisibleText((By.id("input-payment-zone")),"Harrow");


        //click on check box
        clickOnElement(By.xpath("//input[@name='agree']"));

        //2.21 Click on “Continue”
        clickOnElement(By.xpath("//div[@class='pull-right']/input[@value='Continue']"));

        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']")," Please deliver through speed post");

        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));

        //2.24 Click on “Continue” button
        clickOnElement(By.id("button-payment-method"));

        //2.25 Verify the message “Warning: Payment method required!”
        Assert.assertEquals("Payment method required message not displayed.","Warning: Payment method required!",getTextFromElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));

    }

    @After
    public void tearDown() {
        // closeBrowser();
    }












    }
