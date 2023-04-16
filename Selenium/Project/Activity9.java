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

public class Activity9 {

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
    @Test(priority=1)
    public void myAccountTest(){
        //click on my account
        driver.findElement(By.xpath("//a[@href=\'https://alchemy.hguy.co/lms/my-account/\']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"uagb-infobox-d9258720-3455-443e-b618-b57211636d84\"]/div/div/div/div[1]/h1")));
        String myAccountTitle=driver.getTitle();
        System.out.println(myAccountTitle);
        //Assertion
        Assert.assertEquals(myAccountTitle, "My Account – Alchemy LMS");
    }
    @Test(priority=2)
    @Parameters({"loginUserName","password"})  // Get user details from Test NG
    public void loginToMyAccountTest(String usserName, String password){
        //Find Login link and click
        driver.findElement(By.xpath("//a[@href=\"#login\"]")).click();
        //Provide credentials and Click Submit
        driver.findElement(By.id("user_login")).sendKeys(usserName);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        //Check if Edit Profile link available to confirm log in
        wait.until(ExpectedConditions.elementToBeClickable(By.className("ld-profile-edit-link")));
        String strEditProfileText=driver.findElement(By.className("ld-profile-edit-link")).getText();
        //Assertion
        Assert.assertEquals(strEditProfileText,"Edit profile");
    }
    @Test(priority = 3)
    public void markCousreAsAcompleteTest(){
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

        //Select 3rd Course - Content Marketing
        driver.findElement(By.xpath("//*[@id=\"post-24042\"]/div[2]/p[2]/a")).click();
        //Select the 1st topic in the course
        driver.findElement(By.xpath("//*[@id=\"ld-expand-283\"]/div/a/div[1]")).click();

        //Click on Mark Complete buttons
        List<WebElement> markCompleteButtons= driver.findElements(By.className("learndash_mark_complete_button"));
        //If MarkComplete Button available then Click on those
        if(markCompleteButtons.size()>0){
            for(int i=1; i<=markCompleteButtons.size();i++){
                //wait.until(ExpectedConditions.elementToBeClickable(markCompleteButtons.get(i-1)));
                markCompleteButtons.get(i-1).click();
                System.out.println("Marked completed Topic No - " + i);
            }
        }
        //Check Status
        String status=driver.findElement(By.xpath("//*[@id=\"learndash_post_283\"]/div/div[1]/div/div[2]")).getText();
        //Assert
        Assert.assertEquals(status, "COMPLETE");
    }
    @AfterTest
    public void closeBrow(){driver.close();}
}
