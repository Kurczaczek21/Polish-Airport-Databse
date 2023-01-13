import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WebScrape {
    private static final Logger logger= Logger.getLogger(WebScrape.class);

    public List<String> getData(String url) throws Exception {
        List<String> output = new ArrayList<>();

        WebDriver driver = new FirefoxDriver( new FirefoxOptions().addPreference("general.useragent.override","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless")); //.addArguments("--log-level=3")
        driver.get(url);
        Thread.sleep(4000);
        logger.info("Downloading data from website: '"+url+"'.");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });

        try {
            WebElement Acceptbutton = driver.findElement(By.xpath("//button[text()='Akceptuję']"));
            Acceptbutton.click();
        }catch (Exception e){
            WebElement Acceptbutton = driver.findElement(By.xpath("//button[text()='I accept']"));
            Acceptbutton.click();
        }
        Thread.sleep(4000);
        Thread.sleep(4000);

        WebElement button = driver.findElement(By.xpath("//button[text()='Load earlier flights']"));
        button.click();
        Thread.sleep(4000);

        if(button.isDisplayed()) {
            button.click();
            Thread.sleep(4000);
        }

        if(button.isDisplayed()) {
            button.click();
            Thread.sleep(4000);
        }

        if(button.isDisplayed()) {
            button.click();
            Thread.sleep(4000);
        }

        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//tr"));
        for(WebElement ele:allHeaders) {
            if(ele.getText().equals("") || ele.getText().contains("TIME") || ele.getText().contains("Scheduled")|| ele.getText().contains("Estimated") || ele.getText().contains("later") || ele.getText().contains("local") || ele.getText().contains("Unknown") || ele.getText().contains("Unknown") )   {
                continue;
            }
            output.add(ele.getText());
        }

//        driver.close();
        System.out.println("CLOSING -------------------------------------------");
        driver.quit();


        return output;
    }
}