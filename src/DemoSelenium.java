import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoSelenium {
    WebDriver driver;
    @BeforeTest
    public void pre(){
        System.setProperty("webdriver.chrome.driver","F:\\java_Selenium\\Sources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void getUrl(){
        driver.get("http://www.baidu.com");
    }
}
