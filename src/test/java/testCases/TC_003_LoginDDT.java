package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)

    void testLoginDDT(
            String email,
            String password,
            String exp
    ) throws InterruptedException {
        try {
            logger.info("Starting TC_003_LoginDDT Test******");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicking my account ******");
            hp.clickLogin();
            logger.info("Clicking login ******");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLoginEmail(email);
            loginPage.setLoginPassWord(password);
            loginPage.clickLoginButton();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();
            if (exp.equals("Valid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogoutButton();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equals("Invalid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogoutButton();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("DataDriver Test finished ******");
    }
}
