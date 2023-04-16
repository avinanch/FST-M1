package ProjectActivities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Activity7 {

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
    public void searchAllCoursesTset(){
        //Click on All Courses Link
        String expr = "//*[contains(@href, 'all-courses')]";
        driver.findElement(By.xpath(expr)).click();

        List<WebElement> allCourses= driver.findElements(By.className("entry-title"));
        System.out.println("Number of courses found in All Courses page are :" +allCourses.size());

        //Print name of all the courses
        for(int i=1; i<=allCourses.size();i++){
            System.out.println("Course Number " + i +" title is " + allCourses.get(i-1).getText());
        }
        //Assert Number of courses found is 3.
        Assert.assertEquals(3,allCourses.size());
    }

    @AfterTest
    public void closeBrow(){
        driver.close();
    }
}
