package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtLginEmail;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtLginPassword;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement txtLginBtn;

    public void setLoginEmail(String loginEmail) throws InterruptedException {
        Thread.sleep(10000);
        txtLginEmail.sendKeys(loginEmail);
    }
    public void setLoginPassWord(String loginPassWord) throws InterruptedException {
        Thread.sleep(10000);
        txtLginPassword.sendKeys(loginPassWord);
    }
    public void clickLoginButton() throws InterruptedException {
        Thread.sleep(10000);
        txtLginBtn.click();
    }
}
