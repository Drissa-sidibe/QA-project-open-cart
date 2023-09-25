package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import static org.testng.AssertJUnit.assertEquals;


public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    void testAccountRegistration() throws InterruptedException {
            logger.info("*** Starting TC_001_AccountRegistrationTest");
            try {
                    HomePage hp = new HomePage(driver);
                    hp.clickMyAccount();
                    hp.clickRegister();
//                    String win = driver.getWindowHandle();
//                    System.out.println("Window is:" + win);
                    AccountRegistrationPage registPage = new AccountRegistrationPage(driver);
                    logger.info("*** Inserting customer account credentials ");
                    registPage.setFirstName(randomStrings()); //this random string generates new user credentials each time
                    registPage.setLastName(randomStrings());
                    //registPage.setEmail(randomEmail() + "@gmail.com");//random
                    //registPage.setPassword("lovely1"); instead of hardcoding the password
                    registPage.setPassword(randomAlphaNumericPassword());
                    //String password = randomAlphaNumericPassword();
//            registPage.setPassword(password);
//            registPage.setConfirmPassword(password);
                    registPage.setNewsLetterAgreement();
                    registPage.setPrivacyPolicy();
                    registPage.clickContinue();
                    logger.info("Clicked on continue.........>");
                    String message = registPage.getConfirmationMsg();
                    System.out.println(message);
                    logger.info("Validating registration message");
                    logger.info("Validating registration message");
                    assertEquals(message, "Your Account Has Been Created!");
            }catch (Exception e){
                    logger.error("Invalid login");
                    Assert.fail();
            }

    }
}
