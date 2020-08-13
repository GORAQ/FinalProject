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
import java.util.List;
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
        WebElement searchOurCatalog = driver.findElement(By.name("s"));
        searchOurCatalog.clear();
        searchOurCatalog.sendKeys("Hummingbird Printed Sweater");

        WebElement searchProduct = driver.findElement(By.xpath("//*[@id=\'search_widget\']/form/button/i"));
        searchProduct.click();

    }

    @And("^User chooses search icon$")
    public void userChoosesSearchIcon() {

        WebElement chooseSearchIcon = driver.findElement(By.xpath("//*[@id=\'js-product-list\']/div[1]/article[1]/div/div[1]/h2/a"));
        chooseSearchIcon.click();

    }

    @And("^User chooses product from the list$")
    public void userChoosesProductFromTheList() {
        WebElement chooseProduct = driver.findElement(By.name("group[1]"));
        Select product = new Select(chooseProduct);
        product.selectByValue("2");
    }


    @And("^User selects size of the product$")
    public void userSelectsSizeOfTheProduct() throws InterruptedException {

        WebElement size = driver.findElement(By.id("group_1"));
        Select roleSelect = new Select(size);
        roleSelect.selectByValue("2");

        Thread.sleep(2000);

    }

    @And("^User selects quantity of the product$")
    public void userSelectsQuantityOfTheProduct() throws InterruptedException {
        WebElement quantity = driver.findElement(By.name("qty"));
        quantity.clear();
        quantity.sendKeys("5");
    }

    @And("^User puts product to the cart$")
    public void userPutsProductToTheCart() {
        WebElement putProductToTheCart = driver.findElement(By.xpath("//*[@id=\'add-to-cart-or-refresh\']/div[2]/div/div[2]/button"));
        putProductToTheCart.click();
    }

    @And("^User proceeds to checkout$")
    public void userProceedsToCheckout() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement proceedCheckout = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        proceedCheckout.click();

        WebElement proceedCheckout1 = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        proceedCheckout1.click();
    }

    @And("^User selects delivery address$")
    public void userSelectsDeliveryAddress() {

        List<WebElement> addresses = driver.findElements(By.name("id_address_delivery"));
        System.out.println(addresses.size() - 1);
        WebElement lastAddress = addresses.get(addresses.size()-1);
        lastAddress.click();

    }

    @And("^User selects shipping method$")
    public void userSelectsShippingMethod() {

        WebElement selectShippingMethod = driver.findElement(By.id("checkout-delivery-step"));
        selectShippingMethod.click();

        WebElement selectPickUpInStore = driver.findElement(By.cssSelector("#js-delivery > div > div.delivery-options > div:nth-child(1) > div > span > span"));
        selectPickUpInStore.click();
    }

    @And("^User selects payment method$")
    public void userSelectsPaymentMethod() {

        WebElement selectPaymentMethod = driver.findElement(By.id("checkout-payment-step"));
        selectPaymentMethod.click();

        WebElement selectPayByCheck = driver.findElement(By.cssSelector("#payment-option-1-container > span"));
        selectPayByCheck.click();
    }

    @And("^User selects Order with an obligation to pay$")
    public void userSelectsOrderWithAnObligationToPay() {
        WebElement agreementCheckbox = driver.findElement(By.cssSelector("#conditions_to_approve\\[terms-and-conditions\\]"));
        agreementCheckbox.click();

        WebElement selectOrderWithAnObligationToPay = driver.findElement(By.cssSelector("#payment-confirmation > div.ps-shown-by-js > button"));
        selectOrderWithAnObligationToPay.click();
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
            System.out.println("Screenshot failed!!!");
            e.printStackTrace();
        }

    }
}

