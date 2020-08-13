package stepDefinitions;

import FinalProject1.LoginPageTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyStoreAddresses {

    LoginPageTest loginPageTest;
    WebDriver driver;

    @Given("^User is logged in to MyStore shop$")
    public void userIsLoggedInToMyStoreShop() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.loginAs("kat.tut@mail.pl", "Pass123");

    }

    @When("^User goes to YourAccountPage$")
    public void userGoesToYourAccountPage() {
        WebElement yourAccountPage = driver.findElement(By.xpath("//*[@id=\'_desktop_user_info\']/div/a[2]/span"));
        yourAccountPage.click();

    }

    @And("^User goes to Addresses$")
    public void userGoesToAddresses() {
        WebElement addresses = driver.findElement(By.xpath("//*[@id=\'addresses-link\']/span"));
        addresses.click();

    }


    @And("^User creates new address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCreatesNewAddress(String arg0, String arg1, String arg2, String arg3, String arg4) {

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");

        WebElement company = driver.findElement(By.name("company"));
        company.clear();
        company.sendKeys(arg0);


        WebElement address = driver.findElement(By.name("address1"));
        address.clear();
        address.sendKeys(arg1);


        WebElement city = driver.findElement(By.name("city"));
        city.clear();
        city.sendKeys(arg2);

        WebElement postcode = driver.findElement(By.name("postcode"));
        postcode.clear();
        postcode.sendKeys(arg3);


        WebElement roleSelectElement = driver.findElement(By.name("id_country"));
        Select roleSelect = new Select(roleSelectElement);
        roleSelect.selectByValue("17");

        WebElement phone = driver.findElement(By.name("phone"));
        phone.clear();
        phone.sendKeys(arg4);


        WebElement save5 = driver.findElement(By.xpath("//*[@id=\'content\']/div/div/form/footer/button"));
        save5.click();

    }


    @Then("^User checks data of new created address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userChecksDataOfNewCreatedAddress(String arg0, String arg1, String arg2, String arg3, String arg4)  {
        // Write code here that turns the phrase above into concrete actions

        List<WebElement> addresses = driver.findElements(By.xpath("//article[@class='address']"));

        System.out.println(addresses.size() - 1);
        WebElement lastAddress = addresses.get(addresses.size()-1);
        String actualAddress = lastAddress.getText();
        System.out.println(actualAddress);

        Assert.assertTrue(actualAddress.contains(arg0));
        Assert.assertTrue(actualAddress.contains(arg1));
        Assert.assertTrue(actualAddress.contains(arg2));
        Assert.assertTrue(actualAddress.contains(arg3));
        Assert.assertTrue(actualAddress.contains(arg4));

    }
}
