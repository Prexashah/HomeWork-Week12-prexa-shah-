package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void selectMyAccountOption(String option) {
        clickOnElement(By.xpath("//span[text() = 'My Account']" + option + "'][1]"));

    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        // 1.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        // 1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        List<WebElement> optionsList = getElements(By.xpath("(//li[@class = 'dropdown'])[1]"));
        for (WebElement list : optionsList) {
            if (list.getText().equalsIgnoreCase("Register")) {
                selectMyAccountOption(list.getText().toLowerCase());
                break;
            }
        }
        //1.3 Verify the text “Register Account”.
        // 1.3 Verify the text “Register Account”
        Assert.assertEquals("Incorrect page.", "Register Account", getTextFromElement(By.xpath("//div[@id='content']/h1")));

    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter Login
        List<WebElement> optionsList = getElements(By.xpath("(//li[@class = 'dropdown'])[1]"));
        for (WebElement list : optionsList) {
            if (list.getText().equalsIgnoreCase("Login")) {
                selectMyAccountOption(list.getText().toLowerCase());
                break;
            }
            // 2.3 Verify the text ““Returning Customer”
            Assert.assertEquals("New Customer",
                    getTextFromElement(By.xpath("//div[@class='well']/h2[text()='New Customer']")));

        }
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        //3.13.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter Register
        List<WebElement> optionsList = getElements(By.xpath("(//li[@class = 'dropdown'])[1]"));
        for (WebElement list : optionsList) {
            if (list.getText().equalsIgnoreCase("Register")) {
                selectMyAccountOption(list.getText().toLowerCase());
                break;

            }//first name
            sendTextToElement(By.id("input-payment-firstname"), "Marry");
            //lastname
            sendTextToElement(By.id("input-payment-lastname"), "Roshan");
            //email
            sendTextToElement(By.id("input-payment-email"), "mroshan@gmail.com");
            //phone
            sendTextToElement(By.id("input-payment-telephone"), "0778899665");
            //password
            sendTextToElement(By.id("input-payment-password"), "abcd123");
            //confirm password
            sendTextToElement(By.id("input-payment-confirm"), "abcd123");
            //3.9 Select Subscribe Yes radio button
            List<WebElement> radioButton = getElements(By.xpath("//input[@name='newsletter']"));
            for (WebElement r : radioButton) {
                if (r.getText().equalsIgnoreCase("Yes")) {
                    r.click();
                    break;
                }
            }
        }
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));

        //3.11 Click on Continue button
        clickOnElement(By.linkText("Continue"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //4.2 4.2 Call the method “selectMyAccountOptions” method and pass the parameter Login
        List<WebElement> optionsList = getElements(By.xpath("(//li[@class = 'dropdown'])[1]"));
        for (WebElement list : optionsList) {
            if (list.getText().equalsIgnoreCase("Login")) {
                selectMyAccountOption(list.getText().toLowerCase());
                break;


            }
        }
        //4.3 Enter Email address
        sendTextToElement(By.id("input-email"), "ltester85@gmail.com");

        //4.5 Enter password
        sendTextToElement(By.id("input-password"), "Password1");

        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        //4.7 Verify text “My Account”
        validateResponse("User navigated to wrong page", "My Account", getTextFromElement(By.xpath("//div[@id='content']/h2[text()='My Account']")));

        // 4.8 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));

        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        optionsList = getElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));
        for (WebElement x : optionsList) {
            if (x.getText().equalsIgnoreCase("Logout")) {
                selectMyAccountOption(x.getText().toLowerCase());
                break;
            }
        }

        //4.10 Verify the text “Account Logout”
        validateResponse("The user is not directed to logout page", "Account Logout", getTextFromElement(By.xpath("//div[@id='content']/h1")));

        //4.11 Click on Continue button
        clickOnElement(By.linkText("Continue"));


    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}






    }
}
