package ProjectActivities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {

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
    public void homepageTest(){
        // Get the page title
        String srtTitle=driver.getTitle();
        //Assertion
        Assert.assertEquals(srtTitle, "Alchemy LMS – An LMS Application");
        //AssertJUnit.assertEquals(srtTitle, "Training Support");

    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //Close the browser
        driver.quit();
    }
}
