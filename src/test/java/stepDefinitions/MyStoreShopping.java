package stepDefinitions;

import FinalProject1.LoginPageTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class MyStoreShopping<DateTimeFormattter> {

    LoginPageTest loginPageTest;
    WebDriver driver;

    @Given("^User is logged in MyStore shop$")
    public void userIsLoggedInMyStoreShop() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

        LoginPageTest loginPageTest = new LoginPageTest(driver);
        loginPageTest.loginAs("kat.tut@mail.pl" , "Pass123");
    }

    @When("^User puts Product name in Search our catalog$")
    public void userPutsProductNameInSearchOurCatalog() {
        // Write code here that turns the phrase above into concrete actions
        WebElement SearchOurCatalog = driver.findElement(By.name("s"));
        SearchOurCatalog.clear();
        SearchOurCatalog.sendKeys("Hummingbird Printed Sweater");

        WebElement Search = driver.findElement(By.xpath("//*[@id=\'search_widget\']/form/button/i"));
        Search.click();

    }

    @And("^User chooses search icon$")
    public void userChoosesSearchIcon() {

        WebElement Search = driver.findElement(By.xpath("//*[@id=\'js-product-list\']/div[1]/article[1]/div/div[1]/h2/a"));
        Search.click();


    }

    @And("^User chooses product from the list$")
    public void userChoosesProductFromTheList() {
        WebElement roleSelectElement = driver.findElement(By.name("group[1]"));
        Select roleSelect = new Select(roleSelectElement);
        roleSelect.selectByValue("2");
    }


    @And("^User selects size of the product$")
    public void userSelectsSizeOfTheProduct() throws InterruptedException {

        WebElement Size = driver.findElement(By.id("group_1"));
        Select roleSelect = new Select(Size);
        roleSelect.selectByValue("2");

        Thread.sleep(2000);

    }

    @And("^User selects quantity of the product$")
    public void userSelectsQuantityOfTheProduct() throws InterruptedException {
        WebElement Size = driver.findElement(By.name("qty"));
        Size.clear();
        Size.sendKeys("5");
    }

    @And("^User puts product to the cart$")
    public void userPutsProductToTheCart() {
        WebElement Search = driver.findElement(By.xpath("//*[@id=\'add-to-cart-or-refresh\']/div[2]/div/div[2]/button"));
        Search.click();
    }

    @And("^User proceeds to checkout$")
    public void userProceedsToCheckout() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement Search = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        Search.click();

        WebElement Search1 = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        Search1.click();
    }

    @And("^User selects delivery address$")
    public void userSelectsDeliveryAddress() {
        WebElement Choose = driver.findElement(By.xpath("//*[@id=\'id-address-delivery-address-5145\']/header/label/span[1]/span"));
        Choose.click();

    }

    @And("^User selects shipping method$")
    public void userSelectsShippingMethod() {

        WebElement Choose1 = driver.findElement(By.id("checkout-delivery-step"));
        Choose1.click();

        WebElement Choose2 = driver.findElement(By.cssSelector("#js-delivery > div > div.delivery-options > div:nth-child(1) > div > span > span"));
        Choose2.click();
    }

    @And("^User selects payment method$")
    public void userSelectsPaymentMethod() {

        WebElement Choose3 = driver.findElement(By.id("checkout-payment-step"));
        Choose3.click();

        WebElement Choose4 = driver.findElement(By.cssSelector("#payment-option-1-container > span"));
        Choose4.click();
    }

    @And("^User selects Order with an obligation to pay$")
    public void userSelectsOrderWithAnObligationToPay() {
        WebElement Agreement = driver.findElement(By.cssSelector("#conditions_to_approve\\[terms-and-conditions\\]"));
        Agreement.click();

        WebElement Obligation = driver.findElement(By.cssSelector("#payment-confirmation > div.ps-shown-by-js > button"));
        Obligation.click();
    }

    @Then("^User makes screenshot of order items$")
    public void userMakesScreenshotOfOrderItems() throws IOException {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String filename = now.format(formatter) + "-screenshot.png";
        try {
            FileUtils.copyFile(scrFile, new File("screenshots/" + filename));
        } catch (IOException e) {
            System.out.println("Screenshot failed!!!!!111oneroneoneon!!!!");
            e.printStackTrace();
        }

    }
}

