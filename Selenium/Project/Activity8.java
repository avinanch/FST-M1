package ProjectActivities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Activity8 {

    //Declare global object

    WebDriver driver;
    WebDriverWait wait;

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
        //Setup wait time
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    //Create the test method
    @Test(priority = 1)
    @Parameters({"visitorName","email", "subject", "comment"})
    public void contactTest(String visitorName, String email, String subject, String comment){

        //Click on Contact Link
        String expr = "//*[contains(@href, 'contact')]";
        driver.findElement(By.xpath(expr)).click();

        //Wait until 'Send us a message' appears
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"uagb-adv-heading-d2a65f11-dc57-40e4-a40d-228a720e0948\"]/h2")));

        //Set up values to be entered in the form

        //Enter details and Click Submit
        driver.findElement(By.id("wpforms-8-field_0")).sendKeys(visitorName); //Enter Name
        driver.findElement(By.id("wpforms-8-field_1")).sendKeys(email); //Enter Email
        driver.findElement(By.id("wpforms-8-field_3")).sendKeys(subject); //Enter Subject
        driver.findElement(By.id("wpforms-8-field_2")).sendKeys(comment); //Enter Comment
        //Enter details and Click Submit
        driver.findElement(By.xpath("//button[text()='Send Message']")).click(); //Click Send Message

        //wait for confirmation message to appear
        wait.until(ExpectedConditions.elementToBeClickable(By.id("wpforms-confirmation-8")));
        //Read and print confirmation message
        String confMessage=driver.findElement(By.id("wpforms-confirmation-8")).getText();

        System.out.println("Confirmation Message appeared after submit : " + confMessage);
        //Assert
        Assert.assertEquals(confMessage, "Thanks for contacting us! We will be in touch with you shortly.");
    }

    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
