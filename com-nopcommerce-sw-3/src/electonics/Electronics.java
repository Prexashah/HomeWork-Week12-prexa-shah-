package electonics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.Utility;

public class Electronics extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        //2.1 Mouse Hover on “Electronics” Tab
        //2.2 Mouse Hover on “Cell phones” and click
        WebElement electronicTab = driver.findElement(By.xpath("//a[text() = 'Desktops']"));
        WebElement cellPhone = driver.findElement(By.xpath("(//a[text() = 'Cell phones '])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicTab).build().perform();
        actions.click(cellPhone).build().perform();

        //2.3 Verify the text “Cell phones”
        String expectedText = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//h1[text() ='Cell phones']"));
        Assert.assertEquals(expectedText, actualText);

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[@class = 'viewmode-icon list']"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//a[normalize-space() = 'Nokia Lumia 1020']"));

        //2.6 Verify the text “Nokia Lumia 1020”
        String nokiaText = "Nokia Lumia 1020";
        String actualNokiaText = getTextFromElement(By.xpath("//h1[text() = 'Nokia Lumia 1020']"));
        Assert.assertEquals(nokiaText, actualNokiaText);
        //2.7 Verify the price “$349.00”
        String nokiaPrice = "$349.00";
        String actualNokiaPrice = getTextFromElement(By.id("price-value-20"));
        Assert.assertEquals(nokiaPrice, actualNokiaPrice);

        //2.8 Change quantity to 2
        driver.findElement(By.id("product_enteredQuantity_20")).clear();
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.id("add-to-cart-button-20"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top
        String message = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//p[@class = 'content']"));
        Assert.assertEquals(message, actualMessage);

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.

        WebElement shoppingCart = driver.findElement(By.xpath("//a[@class = 'ico-cart']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[text() = 'Go to cart']"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(electronicTab).build().perform();
        actions1.click(cellPhone).build().perform();

        //2.12 Verify the message "Shopping cart"
        String message1 = "Shopping cart";
        String actualMessage1 = getTextFromElement(By.xpath("//h1"));
        Assert.assertEquals(message1, actualMessage1);
        //2.13 Verify the quantity is 2
        String quantity = "2";
        String actualQuantity = getTextFromElement(By.xpath("//input[@value = '2']"));
        Assert.assertEquals(quantity, actualQuantity);
        //2.14 Verify the Total $698.00
        String totalPrice = "$698.00";
        String actualPrice = getTextFromElement(By.xpath("(//td[@class= 'cart-total-right'])[4]"));
        Assert.assertEquals(quantity, actualQuantity);

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.16 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
        //2.17 Verify the Text “Welcome, Please Sign In!”
        String welcomeText = "Welcome, Please Sign In!";
        String actualWelcomeText = getTextFromElement(By.xpath("(//h1"));
        Assert.assertEquals(welcomeText, actualWelcomeText);

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[@class = 'button-1 register-button']"));
        //2.19 Verify the text “Register”
        String registerText = "Register";
        String actualRegisterText = getTextFromElement(By.xpath("(//h1"));
        Assert.assertEquals(registerText, actualRegisterText);

        //2.20 Fill the mandatory fields
        //radio button
        clickOnElement(By.id("gender-female"));
        //Firstname
        sendTextToElement(By.id("FirstName"), "Maria");
        //Last Name
        sendTextToElement(By.id("LastName"), "Roshan");

        //Email
        sendTextToElement(By.id("Email"), "ltester85@gmail.com");
        //password
        sendTextToElement(By.id("Password"), "Abcd1234");
        //confirm password
        sendTextToElement(By.id("ConfirmPassword"), "Abcd1234");

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));

        //2.22 Verify the message “Your registration completed”
        String registerText1 = "Your registration completed";
        String actualRegisterText1 = getTextFromElement(By.xpath("//div[text() = 'Your registration completed']"));
        Assert.assertEquals(registerText1, actualRegisterText1);
        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[text() = 'Continue']"));

        //2.24 Verify the text “Shopping card”
        String shoppingCart1 = "Shopping cart";
        String actualShoppingCart1 = getTextFromElement(By.xpath("//h1"));
        Assert.assertEquals(registerText1, actualRegisterText1);
        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.27 Fill the Mandatory fields
        //first name
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Maria");
        //last name
        sendTextToElement(By.xpath("//span[text() = 'BillingNewAddress.LastName']"), "Roshan");
        //email
        sendTextToElement(By.id("BillingNewAddress_Email"), "ltester85@gmail.com");
        //country
        selectByVisibleText(By.id("BillingNewAddress_CountryId"), "Finland");
        //City
        sendTextToElement(By.id("BillingNewAddress_City"), "Helsinki");
        //Address
        sendTextToElement(By.id("BillingNewAddress_Address1"), "1,Richard Street");
        //zip code
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "011155");
        //phone number
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "012587945664");

        //2.28 Click on “CONTINUE”


    }


}


