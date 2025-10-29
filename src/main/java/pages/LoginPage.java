package pages;

import com.companyname.projectname.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginButton");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(findElement(usernameField), username);
    }

    public void enterPassword(String password) {
        sendKeys(findElement(passwordField), password);
    }

    public void clickLoginButton() {
        click(findElement(loginButton));
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}