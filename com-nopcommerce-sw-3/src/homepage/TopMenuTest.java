package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.Arrays;
import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void selectMenu(String menu, int a) {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[normalize-space()='" + menu + "']"));
        String actualText = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));

        //store expected top menu titles in an array
        String[] expectedText = {"Computers", "Electronics", "Apparel", "Digital downloads", "Books", "Jewelry", "Gift Cards"};
        Assert.assertEquals("The user is not navigated to the correct page in " + expectedText[a] + ". ",
                expectedText[a], actualText);

    }

    @Test
    public void verifyPageNavigation() {

        //declare an array to store top menu elements
        String[] topMenuArray = new String[7];
        int i = 0;

        List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/li"));

        //store each element in the array using for each loop
        for (WebElement topMenuItem : topMenu) {
            topMenuArray[i] = topMenuItem.getText();
            i++;
        }

        System.out.println(Arrays.toString(topMenuArray)); //for debug purpose

        //call the selectMenu method to validate each menu element click and navigation
        for (i = 0; i < 7; i++) {
            selectMenu(topMenuArray[i], i);
        }
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
