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
         * Main Java we will use to store all reusable utilities, page object files later in this section.         *
         *
         * "In interviews, it's common to be asked how you implement data-driven testing using external files and TestNG's DataProvider.
         * In our framework, we achieve this by following a step-by-step approach: first, we drive the test data from external sources
         * such as Excel or JSON files using utility classes. Then, we integrate TestNG's @DataProvider annotation to feed this data into our test methods.
         * This allows us to parameterize the tests and execute them multiple times with different input sets. With this approach,
         * two tests have passed successfully, demonstrating how parametrization is effectively handled in our framework."
         */


       // String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
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
    @Test (dependsOnMethods = "submitOrder", dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> input)  {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrderPage();
        boolean match = orderPage.verifyOrderDisplay(input.get("productName"));
        Assert.assertTrue(match);
    }


    // Extent Report

        // the below data provider is for data driven testing also json file too can be used so that the test.
        // will not have test data, but read from external json file. the 3 approaches are below.

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\abbeyLtd\\Data\\PurchaseOrder.json"); //getJsonDataToMap();
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

    /*@DataProvider
	  public Object[][] getData()
	  {
	    return new Object[][]  {{"abbeylincoln@gmail.com" ,"Abbey100!","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };

	  }*/

    /*@DataProvider
    public Object[][] getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "abbeylincoln@gmail.com");
        map.put("password", "Abbey100!");
        map.put("productName", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", "shetty@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("productName", "ADIDAS ORIGINAL");

        return new Object[][] {{map}, {map1}};
    }*/




}
