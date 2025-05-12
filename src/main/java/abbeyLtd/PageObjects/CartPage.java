package abbeyLtd.PageObjects;

import abbeyLtd.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy (css = ".totalRow button")
    WebElement checkoutBtn;

    @FindBy (css = ".cartSection h3")
    List<WebElement> cartProducts;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductDisplay(String productName) {
        boolean match = cartProducts.stream().anyMatch(product -> product.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckoutPage() {
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

}
