package ProjectActivities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Activity3 {

    //Declare global object

    WebDriver driver;

    //Create the setup methods
    @BeforeClass
    public void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");
        //set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        //Initialize deriver object
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        //open the browser
        driver.get("https://alchemy.hguy.co/lms");
    }
    //Create the test method
    @Test(priority = 1)
    public void fisrtInfoBoxTitleTest(){
        //Get list of Webelements of type h3 and id starts with 'uagb-infobox'
        String expr = "//*[contains(@id, 'uagb-infobox')]//h3";
        List<WebElement> infoBoxes=driver.findElements(By.xpath(expr));
        //Get title of the 1st infobox
        String strInfoBoxTitle=infoBoxes.get(0).getText();
        infoBoxes.get(0).click();
        //Assertion
        Assert.assertEquals(strInfoBoxTitle, "Actionable Training");
    }
    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
