package testsuite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utility;

public class LoginTest extends Utility {

    String baseUrl = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //* Enter “standard_user” username
        sendTextToElement(By.id("user-name"), "standard_user");

        // * Enter “secret_sauce” password
        sendTextToElement(By.id("password"), "secret_sauce");

        //* Click on ‘LOGIN’ button
        clickOnElement(By.id("login-button"));

        //* Verify the text “PRODUCTS”
        Assert.assertEquals("in correct page", "Products",
                getTextFromElement(By.xpath("xpath(//span[normalize-space()='Products']")));

    }
    
}
