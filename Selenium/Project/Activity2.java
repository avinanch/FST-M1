package ProjectActivities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {

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
    public void headingTest(){
        // Get the page Heading
        String strHeading=driver.findElement(By.className("uagb-ifb-title")).getText();

        //Assertion
        Assert.assertEquals(strHeading, "Learn from Industry Experts");
    }
    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
