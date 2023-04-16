package ProjectActivities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Activity4 {

    //Declare global object

    WebDriver driver;

    //Create the setup methods
    @BeforeTest
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
    public void searchSecondMostPopularCourseTest(){
        // Get the 2nd most Popular Course Title
        List<WebElement> mostPopularCourses= driver.findElements(By.className("entry-title"));
        //Get 2nd mist popular Course Title
        String courseTitle=mostPopularCourses.get(1).getText();
        mostPopularCourses.get(1).click();
        //Assertion
        Assert.assertEquals(courseTitle, "Email Marketing Strategies");
    }

    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
