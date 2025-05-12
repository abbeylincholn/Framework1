package abbeyLtd.AbstractComponents;

import abbeyLtd.PageObjects.CartPage;
import abbeyLtd.PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    //Base class
    //Creating abstract components to reuse the common variables, methods and codes in frameworks
    /**
     * A Page Object Class is a design pattern often used in test automation, especially with tools like Selenium, Playwright, or Cypress.
     * The goal of a Page Object is to model a web page (or a component of it) as a class. This helps make tests cleaner, easier to maintain,
     * and more readable.     *
     * Here’s a simple outline of what a Page Object Class typically contains:     *
     *     Locators for elements on the page (buttons, inputs, etc.)     *
     *     Methods that perform actions on those elements (clicks, typing, etc.)     *
     *     Optional Assertions for verifying page behavior.
     */

    WebDriver driver;
    WebDriverWait wait;

    @FindBy (css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy (css = "[routerlink*='myorders']")
    WebElement orderHeader;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement element) throws InterruptedException {
      // wait.until(ExpectedConditions.invisibilityOf(element));
        Thread.sleep(1000);
    }

    public CartPage goToCardPage() {
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage() {
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }




}
