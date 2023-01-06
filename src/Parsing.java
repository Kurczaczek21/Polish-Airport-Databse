import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Parsing {
    private final static String url = "https://www.flightradar24.com/data/airports/krk/arrivals";

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        Thread.sleep(4000); //EZZZZZZZZZ


        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });


        driver.findElement(By.xpath("//button[contains(@ng-click,'loadMore')][contains(@data-page,'-1')]")).click();

        // HEADERS
        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//th"));
        System.out.println(allHeaders.size());
        for(WebElement ele:allHeaders)
        {
            System.out.println(ele.getText());
        }

        // ROWS
//        List<WebElement> allRows = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//tr//td[3]//div[contains(@ng-show,'(objFlight.flight.airport.origin)')]//span"));
        List<WebElement> allRows = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//tr//td[3]//div[contains(@ng-show,'(objFlight.flight.airport.origin)')]//span"));
        System.out.println(allRows.size());
        for(WebElement ele:allRows)
        {
            System.out.println(ele.getText());
        }

        driver.quit();


    }
}