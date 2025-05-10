package abbeyLtd.TestPage;

import abbeyLtd.PageObjects.*;
import abbeyLtd.TestComponent.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

        /**
         * Main Java we will use to store all reusable utilities, page object files later in this section.
         *
         */

       // String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderTest(HashMap<String, String> input) throws InterruptedException, IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));
        List<WebElement> productsList = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Nigeria");
        OrderConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    // Dependency attribute test strategy
    //To verify Zara Coat 3 is displaying in orders page.
    @Test (dependsOnMethods = "submitOrderTest", dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> input)  {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrderPage();
        boolean match = orderPage.verifyOrderDisplay(input.get("productName"));
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "abbeylincoln@gmail.com");
        map.put("password", "Abbey100!");
        map.put("productName", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", "shetty@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("productName", "ZARA COAT 3");
        return new Object[][] {{map}, {map1}};
    }

    /*@DataProvider
	  public Object[][] getData()
	  {
	    return new Object[][]  {{"abbeylincoln@gmail.com" ,"Abbey100!","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };

	  }*/




}
