package abbeyLtd.TestPage;

import abbeyLtd.PageObjects.*;
import abbeyLtd.TestComponent.BaseTest;
import com.thoughtworks.qdox.model.expression.LogicalAnd;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

        /**
         * Main Java we will use to store all reusable utilities, page object files later in this section.
         *
         */
    @Test
    public void submitOrderTest() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";
        LandingPage landingPage = launchApp();
        ProductCatalogue productCatalogue = landingPage.loginApplication("abbeylincoln@gmail.com", "Abbey100!");

        List<WebElement> productsList = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Nigeria");
        OrderConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
