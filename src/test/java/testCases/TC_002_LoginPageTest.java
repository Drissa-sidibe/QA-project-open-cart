package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginPageTest extends BaseClass {
    @Test(groups = {"Sanity", "Master"})
    public void testLogin(){
        try {
            logger.info("Starting TC_002_LoginPageTest ******");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lg = new LoginPage(driver);
            lg.setLoginEmail(resourceBundle.getString("email"));
            lg.setLoginPassWord(resourceBundle.getString("password"));
            lg.clickLoginButton();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            Assert.assertEquals(targetPage, true);
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("TC_002_LoginPageTest ****** Finished");
    }

}
