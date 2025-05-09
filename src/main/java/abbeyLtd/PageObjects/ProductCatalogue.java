package abbeyLtd.PageObjects;

import abbeyLtd.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    /**
     * Can I apply PageFactory inside webelement.findElement?
     * No, because PageFactory is designed to initialize fields at the class level, not for finding elements inside another WebElement.
     * PageFactory uses the driver to search the DOM globally
     * WebElement form = driver.findElement(By.id("loginForm"));
     * WebElement username = form.findElement(By.name("username"));
     * WebElement password = form.findElement(By.name("password"));
     * PageFactory won't help — you must use .findElement() manually.
     */
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy (css = ".mb-3")
    List<WebElement> products;

    @FindBy (css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }










}
