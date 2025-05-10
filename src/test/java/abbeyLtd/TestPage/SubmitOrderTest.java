package abbeyLtd.TestPage;

import abbeyLtd.PageObjects.*;
import abbeyLtd.TestComponent.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

        /**
         * Main Java we will use to store all reusable utilities, page object files later in this section.
         *
         */


        String productName = "ZARA COAT 3";

    @Test
    public void submitOrderTest() throws InterruptedException, IOException {
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
    }

    // Dependency attribute test strategy
    //To verify Zara Coat 3 is displaying in orders page.
    @Test (dependsOnMethods = "submitOrderTest")
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("abbeylincoln@gmail.com", "Abbey100!");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        boolean match = orderPage.verifyOrderDisplay(productName);
        Assert.assertTrue(match);
    }


}
