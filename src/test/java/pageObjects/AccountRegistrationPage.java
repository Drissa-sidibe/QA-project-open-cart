package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }
    JavascriptExecutor js = (JavascriptExecutor) driver;
    @FindBy(name = "firstname")
    WebElement txtFirstName;
    @FindBy(name = "lastname")
    WebElement txtLastName;
    @FindBy(name = "email")
    WebElement txtEmail;
    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-newsletter-yes']")
    WebElement ckbxNewsLetterAgree;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    //a[normalize-space()='Continue']
    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement btnContinue;
    @FindBy(xpath = "//a[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String fName){
        txtFirstName.sendKeys(fName);
    }
    public void setLastName(String lName){
        txtLastName.sendKeys(lName);
    }
    public void setPassword(String pass){
        txtPassword.sendKeys(pass);
    }

    public void setEmail(String mail) {
        txtEmail.sendKeys(mail);
    }
    public void setNewsLetterAgreement() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ckbxNewsLetterAgree);
        System.out.println("Setting -----yes");
    }
    public void setPrivacyPolicy(){
        js.executeScript("window.scrollBy(40,5000)");
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chkdPolicy);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chkdPolicy);
        System.out.println("Setting -----policy");
    }
    public void clickContinue() {
        js.executeScript("window.scrollBy(40,5000)");
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnContinue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinue);
        System.out.println("Continuing -----");
    }
    public String getConfirmationMsg(){
        try {
            return (msgConfirmation).getText();
        }catch (Exception e){
            return (e.getMessage());
        }
    }


}
