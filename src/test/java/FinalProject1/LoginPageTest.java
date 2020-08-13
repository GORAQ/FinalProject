package FinalProject1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPageTest {
    private static WebDriver driver;
    public LoginPageTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void loginAs(String email, String password) {
        WebElement loginInput = driver.findElement(By.name("email"));
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        WebElement signInButton = driver.findElement(By.id("submit-login"));
        signInButton.click();
    }
    public String getLoggedUsername() {
        WebElement userName = driver.findElement(By.xpath("//*[@id=\'_desktop_user_info\']/div/a[2]/span"));
        return userName.getText();
    }
}
