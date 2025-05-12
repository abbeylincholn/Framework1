package abbeyLtd.PageObjects;

import abbeyLtd.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //Another method to this called page factory

    @FindBy (id = "userEmail")
    WebElement userEmail;

    @FindBy (id = "userPassword")
    WebElement userPassword;

    @FindBy (id = "login")
    WebElement loginBtn;

    @FindBy (css = "[class*='flyInOut']")
    WebElement errorMsg;




    public ProductCatalogue loginApplication(String username, String password) {
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginBtn.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMsg);
       return errorMsg.getText();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }


}
