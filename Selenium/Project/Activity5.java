package ProjectActivities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Activity5
{

    //Declare global object

    WebDriver driver;
    WebDriverWait wait;
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
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //Create the test method
    @Test(priority = 1)
    public void myAccountTest(){
        //click on my account
        driver.findElement(By.xpath("//a[@href=\'https://alchemy.hguy.co/lms/my-account/\']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"uagb-infobox-d9258720-3455-443e-b618-b57211636d84\"]/div/div/div/div[1]/h1")));
        String myAccountTitle=driver.getTitle();
        System.out.println(myAccountTitle);
        //Assertion
        Assert.assertEquals(myAccountTitle, "My Account – Alchemy LMS");
    }
    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
