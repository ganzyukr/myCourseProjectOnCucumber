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
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Steps {

    protected WebDriver driver;

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        System.setProperty("webdriver.chrome.driver", "/Users/tatiana/myCourseProjectOnCucumber/src/main/resources/drivers/chromedriver");
        //System.setProperty("webdriver.chrome.driver",  System.getProperty("user")+"//resources/drivers/chromedriver");
        driver = new ChromeDriver();
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

            WebElement formGroupBlock = driver.findElement(By.xpath("//label[(text()='" + item.get(0).toString() + "')]/following::select"));
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
                .xpath("//a[contains(text(),'" + itemName + "')]/ancestor::div[@class='book-item']//a[contains(text(),'"+ button +"')]"));
        addToBasketButton.click();
//a[contains(text(),'Thinking in Java')]/ancestor::div[@class='book-item']
        //a[contains(text(),'Add to basket')][1]
        //a[contains(text(),'Thinking in Java')]
        //div[@class=’listitem Folder’]/descendant::span[text()=’Folder name’]
    }


    @And("I select {string} in basket pop-up")
    public void iSelectBasketCheckoutInBasketPopUp() {
    }

    @And("Basket order summary is as following:")
    public void basketOrderSummaryIsAsFollowing() {
    }

    @And("I click {string} button on {string} page")
    public void iClickCheckoutButtonOnBasketPage() {
    }

    @And("I checkout as a new customer with email {string}")
    public void iCheckoutAsANewCustomerWithEmail(String arg0) {
    }

    @And("Checkout order summary is as following:")
    public void checkoutOrderSummaryIsAsFollowing() {
    }

    @And("I fill delivery address information manually:")
    public void iFillDeliveryAddressInformationManually() {
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
