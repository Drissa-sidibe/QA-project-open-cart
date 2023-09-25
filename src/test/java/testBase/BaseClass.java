package testBase;



import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass{
    public static WebDriver driver;//we have to make this static because we have instantiated baseClass in report manager.
    //if we don't make this one static there will be a conflict between driver objects
    public Logger logger;
    public ResourceBundle resourceBundle;
    @BeforeClass(groups = {"Master","Sanity","Regression"})
    @Parameters("browser")
   public void setup(String br){

        resourceBundle= ResourceBundle.getBundle("config"); //Loads config.properties file
        logger= LogManager.getLogger(this.getClass());
        /*
       options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);*/
        if(br.equals("chrome")){
            driver=new ChromeDriver();
        } else if (br.equals("edge")) {
            driver=new EdgeDriver();
        }
        else driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.get(resourceBundle.getString("appURL"));
        //driver.get("https://demo.opencart.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @AfterClass(groups = {"Master","Sanity","Regression"})
   public void tearDown(){
    driver.quit();
    }
    public String randomEmail(){
        String generateRandomEmail = RandomStringUtils.randomAlphabetic(5);
        return (generateRandomEmail);
    }
    public String randomStrings(){
        String randomUsername = RandomStringUtils.randomAlphabetic(8);
        return (randomUsername);
    }
    public String randomGeneratePhone(){
        String generatedPhone = RandomStringUtils.randomNumeric(10);
        return (generatedPhone);
    }
    public String randomAlphaNumericPassword(){
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);
        return (str+"@"+num);
    }

    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyMMddHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"\\screenshots\\"+tname +"_" +timeStamp +".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }
}
