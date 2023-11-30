package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //Click on computer menu
        // driver.findElement(By.xpath("//a[@href = '/computers'][1]")).click();
        clickOnElement(By.xpath("//a[@href = '/computers'][1]"));

        //click on Desktop
        clickOnElement(By.xpath("//h2[@class = 'title']/child :: a[1]"));
        System.out.println("Click on desktop");

        //Select sort by position name : Z to A

        // Select dropdown = new Select(clickOnElement(By.id("products-orderby")));
        //dropdown.selectByVisibleText("Name: Z to A");

        selectByVisibleText(By.id("products-orderby"), (("Name: Z to A")));

        //Verify the product will arrange in Descending order
        List<WebElement> productAfterFilter = driver.findElements(By.xpath("//div[@class='details']//h2[@class='product-title']/a"));

        for (WebElement e : productAfterFilter) {
            System.out.println(e.getText());
        }

        List<String> productsAfterFilterList = new ArrayList<>();
        for (WebElement item : productAfterFilter) {
            productsAfterFilterList.add(item.getText());
        }

        //Utility class Collections 'sort' method
        Collections.sort(productsAfterFilterList); //sort the before filter list in ascending order
        // Collections.reverse(productsBeforeFilterList);//sorts list in descending order
        Assert.assertEquals( productAfterFilter, productsAfterFilterList);

    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //Click on computer menu
        // driver.findElement(By.xpath("//a[@href = '/computers'][1]")).click();
        clickOnElement(By.xpath("//a[@href = '/computers'][1]"));

        //click on Desktop
        clickOnElement(By.xpath("//h2[@class = 'title']/child :: a[1]"));

        //Select sort by position name : Z to A

        // Select dropdown = new Select(clickOnElement(By.id("products-orderby")));
        //dropdown.selectByVisibleText("Name: Z to A");

        selectByVisibleText(By.id("products-orderby"), (("Name: A to Z ")));
        driver.navigate().refresh();//refresh the page to avoid exception

        //click on Add to Cart
        clickOnElement(By.xpath("(//div[@class='buttons']/button[text()= 'Add to cart'])[1]"));
        driver.navigate().refresh();//refresh the page to avoid exception
        //
        //Verify the text Build your own computer
        String expectedText = "Build your own computer";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space() = 'Build your own computer']"));
        Assert.assertEquals(expectedText, actualText);

        // Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleText(By.id("product_attribute_1"), ("2.2 GHz Intel Pentium Dual-Core E2200"));

        //2.7.Select "8GB [+$60.00]" using Select class.
        selectByVisibleText(By.id("product_attribute_2"), "8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        clickOnElement(By.id("product_attribute_5_10"));
        clickOnElement(By.id("product_attribute_5_12"));

        //2.11 Verify the price "$1,475.00"
        String price = "$1,475.00";
        String actualPrice = getTextFromElement(By.id("price-value-1"));
        Assert.assertEquals(expectedText, actualText);

        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));
        //2.13 Verify the Message "The product has been added to your shopping cart" on Top greenbar
//        String message = "The product has been added to your shopping cart";
        //      String actualMessage = getTextFromElement(By.xpath("//p[@class = 'content']"));
        //    Assert.assertEquals(expectedText, actualText);

        //2.14 click on cross button
        // clickOnElement(By.xpath("//span[@class = 'close']"));


        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement shoppingCart = driver.findElement(By.id("topcartlink"));
        WebElement addToCart = driver.findElement(By.id("flyout-cart"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).build().perform();
        actions.click(addToCart).build().perform();

        //Verify the message "Shopping cart"
        Assert.assertEquals("The user is not navigated to shopping cart", "Shopping cart",
                getTextFromElement(By.xpath("//h1[normalize-space() = 'Shopping cart']")));

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//td[@class = 'quantity']//input")).clear();
        sendTextToElement(By.xpath("//td[@class = 'quantity']//input"), "2");

        //update shopping cart
        clickOnElement(By.id("updatecart"));

        //2.17 Verify the Total"$2,950.00"
        String total = "$2950";
        String actualTotal = getTextFromElement(By.xpath("//span[@class = 'product-subtotal']"));
        Assert.assertEquals(expectedText, actualText);

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        String welcomeTxt = "Welcome, Please Sign In!";
        String actualTxt = getTextFromElement(By.xpath("//h1[normalize-space () = 'Welcome, Please Sign In!']"));
        driver.navigate().refresh();// to avoid exception

        //2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[@class = 'button-1 checkout-as-guest-button']"));

        //2.22 Fill the all mandatory field
        //First name
        clickOnElement(By.id("BillingNewAddress_FirstName"));
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Daniel");
        //last name
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Richardson");
        //email
        sendTextToElement(By.id("BillingNewAddress_Email"), "ltester@gmail.com");
        //company
        sendTextToElement(By.id("BillingNewAddress_Company"), "ABCD Ltd");
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
        //2.23 click on CONTINUE
        clickOnElement(By.id("//button[text() = 'Continue'][@class = 'button-1 new-address-next-step-button'][@name = 'save']"));
        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));
        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class = 'button-1 payment-method-next-step-button']"));
        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        //2.27 Select “Master card” From Select credit card dropdown
        selectByVisibleText(By.id("CreditCardType"), "MasterCard");
        //2.28 Fill all the details
        //Cardholder name
        sendTextToElement(By.id("CardholderName"), "Daniel");
        //card number
        sendTextToElement(By.id("CardNumber"), "5555 5555 5555 4444\t");
        //expiry month and year
        selectByVisibleText(By.id("ExpireMonth"), "05");
        selectByVisibleText(By.id("ExpireYear"), "2028");
        //code
        sendTextToElement(By.id("CardCode"), "039");
        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick = 'PaymentInfo.save()']"));
        //2.30 Verify “Payment Method” is “Credit Card” //li[@class = 'payment-method']

        String paymentMethodCCText = "Payment Method: Credit Card";
        String actualCCText = getTextFromElement(By.xpath("//li[@class = 'payment-method']"));
        Assert.assertEquals(paymentMethodCCText, actualCCText);

        //2.32 Verify “Shipping Method” is “Next Day Air”

        String shippingMethod = "Shipping: (Next Day Air)";
        String actulaMethod = getTextFromElement(By.xpath("//span[@class = 'selected-shipping-method']"));
        Assert.assertEquals(shippingMethod, actulaMethod);

        //2.33 Verify Total is “$2,950.00”
        String total1 = "$2,950.00";
        String actualTotal1 = getTextFromElement(By.xpath("//span[@class = 'value-summary'][text() = '$2,970.00']"));
        Assert.assertEquals(total1, actualTotal1);
        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@onclick = 'ConfirmOrder.save()']"));

        //2.35 Verify the Text “Thank You”
        String thxMessage = "Thank you";
        String actulthxMessage = getTextFromElement(By.xpath("//h1[text() = 'Thank you']"));
        Assert.assertEquals(total1, actualTotal1);

        //2.36 Verify the message “Your order has been successfully processed!”
        String orderMessage = "Your order has been successfully processed!";
        String actulOrderMessage = getTextFromElement(By.xpath("//strong[text() = 'Your order has been successfully processed!']"));
        Assert.assertEquals(orderMessage, actulOrderMessage);

        //2.37 click on continue button
        clickOnElement(By.xpath("//button[@class = 'button-1 order-completed-continue-button']"));

        //2.37 Verify the text “Welcome to our store”
        String welcomeText = "Welcome to our store";
        String actualWelcomeTxt = getTextFromElement(By.xpath("//h2[text() = 'Welcome to our store']"));
        Assert.assertEquals(welcomeText, actualWelcomeTxt);


    }

    @After
    public void tearDown() {
        closeBrowser();
    }


}



