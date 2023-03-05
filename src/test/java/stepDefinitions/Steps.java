package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Steps {

    protected WebDriver driver;

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        Assertions.assertTrue(driver.manage().getCookies().isEmpty());
    }

    @And("I open the {string}")
    public void iOpenThe(String url) {
        driver.get(url);
    }

    @And("I search for {string}")
    public void iSearchFor(String serchString) {
        WebElement searchField = driver.findElement(By.xpath("//input[@name='searchTerm']"));
        searchField.sendKeys(serchString);

        WebElement searchButton = driver.findElement(By.xpath("//span[text()='Search']"));
        searchButton.click();
    }

    @And("I am redirected to a {string}")
    public void iAmRedirectedToA(String url) {
        Assertions.assertEquals(driver.getCurrentUrl(), url);
    }

    @And("Search results contain the following products list")
    public void searchResultsContainTheFollowingProducts(List<String> list) {
        List<WebElement> products = driver.findElements(By.xpath("//h3[@class='title']/a"));

        List<String> productsList = products.stream().map(WebElement::getText).collect(Collectors.toList());

        for (String elem : list) {
            Assertions.assertEquals(true, productsList.contains(elem));
        }
    }

    @And("I apply the following search filters")
    public void iApplyTheFollowingSearchFilters(DataTable table) {

        table.asLists().forEach((List item) -> {

            WebElement formGroupBlock = driver.findElement(By
                    .xpath("//label[(text()='" + item.get(0).toString() + "')]/following::select"));

            Select selectElement = new Select(formGroupBlock);
            selectElement.selectByVisibleText(item.get(1).toString());
        });

        WebElement refineResultsButton = driver.findElement(By.xpath("//button[contains(text(),'Refine results')]"));
        refineResultsButton.click();
    }

    @And("Search results contain only the following products list")
    public void searchResultsContainOnlyTheFollowingProducts(List<String> list) {
        List<WebElement> products = driver.findElements(By.xpath("//h3[@class='title']/a"));

        List<String> productsList = products.stream().map(WebElement::getText).collect(Collectors.toList());

        Assertions.assertEquals(productsList.size(), list.size());

        for (String elem : list) {
            Assertions.assertEquals(true, productsList.contains(elem));
        }
    }

    @And("I click {string} button for product with name {string}")
    public void iClickAddToBasketButtonForProductWithName(String button, String itemName) {
        WebElement addToBasketButton = driver.findElement(By
                .xpath("//a[contains(text(),'" + itemName + "')]/ancestor::div[@class='book-item']//a[contains(text(),'" + button + "')]"));

        addToBasketButton.click();
    }

    @And("I select {string} in basket pop-up")
    public void iSelectBasketCheckoutInBasketPopUp(String linkText) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement basketButton = driver.findElement(By.xpath("//a[(text()='" + linkText + "')]"));
        basketButton.click();
    }

    @And("Basket order summary is as following:")
    public void basketOrderSummaryIsAsFollowing(DataTable table) {
        table.asLists().forEach((List item) -> {

            WebElement totalsBlock = driver.findElement(By
                    .xpath("//dt[(text()='" + item.get(0).toString() + "')]/following::dd"));

            Assertions.assertEquals(item.get(1).toString(), totalsBlock.getText());
        });
    }

    @And("I click {string} button on {string} page")
    public void iClickCheckoutButtonOnBasketPage(String button, String page) {
        Assertions.assertTrue(driver.getTitle().contains(page));
        WebElement checkoutButton = driver.findElement(By.xpath("//a[(text()='" + button + "')][1]"));
        checkoutButton.click();
    }

    @And("I checkout as a new customer with email {string}")
    public void iCheckoutAsANewCustomerWithEmail(String email) {

        WebElement emailAddressField = driver.findElement(By.xpath("//input[@name='emailAddress']"));
        emailAddressField.sendKeys(email);
    }

    @And("Checkout order summary is as following:")
    public void checkoutOrderSummaryIsAsFollowing(DataTable table) {
        table.asLists().forEach((List item) -> {

            WebElement miniBasketBlock = driver.findElement(By
                    .xpath("//dt/strong[contains(text(),'" + item.get(0).toString() + "')]//parent::dt/following::dd"));
            Assertions.assertEquals(item.get(1).toString(), miniBasketBlock.getText());
        });
    }

    @And("I fill {string} with {string} and select {string} as {string} in delivery address information")
    public void iFillWithAndSelectAsInDeliveryAddressInformation(String inputFieldLabel, String inputText, String selectFieldLabel, String selectText) {
        WebElement inputField = driver.findElement(By
                .xpath("//label[text()='" + inputFieldLabel + "']/ancestor::div[@class='label-container']/following-sibling::div/input"));
        inputField.sendKeys(inputText);

        driver.findElement(By
                .xpath("//label[text()='" + selectFieldLabel + "']/following::div/span")).click();

        driver.findElement(By
                .xpath("//label[text()='" + selectFieldLabel + "']/following::ul/li/a[text()='Bahamas']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @And("I fill delivery address information manually:")
    public void iFillDeliveryAddressInformationManually(DataTable table) {
        WebElement buttonEnterAddressManually = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='delivery-manualEntryDeliveryAddress']")));
        buttonEnterAddressManually.click();

        table.asLists().forEach((List item) -> {


            WebElement inputManually = driver.findElement(By
                    .xpath("//label[text()='" + item.get(0).toString() + "']/ancestor::div[@class='label-container']/following-sibling::div/input"));

            inputManually.sendKeys(item.get(1).toString());
        });
    }

    @And("{string} section is disabled for editing")
    public void paymentSectionIsDisabledForEditing() {
    }

    @When("I press {string} button on checkout")
    public void iPressContinueToPaymentButtonOnCheckout() {
    }

    @And("{string} and {string} sections are disabled for editing")
    public void deliveryAddressAndBillingAddressSectionsAreDisabledForEditing() {
    }

    @And("I enter my card details")
    public void iEnterMyCardDetails() {
    }


//    @Then("I should be told {string}")
//    public void i_should_be_told(String expectedAnswer) {
//        assertEquals(expectedAnswer, actualAnswer);
//    }
}
