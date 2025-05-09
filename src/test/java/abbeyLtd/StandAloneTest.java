package abbeyLtd;

import abbeyLtd.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        /**
         * Main Java we will use to store all reusable utilities, page object files later in this section.
         *
         */

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("abbeylincoln@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Abbey100!");
        driver.findElement(By.id("login")).click();
        LandingPage landingPage = new LandingPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"Nigeria").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:last-child")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

       /* driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("abbey");
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).click();
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Nigeria");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));*/

        driver.close();
    }
}
