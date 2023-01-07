import com.beust.jcommander.Strings;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class WebScrape {
    private final static String url = "https://www.flightradar24.com/data/airports/krk/arrivals";
    private static Logger logger= LogManager.getLogger(WebScrape.class);

    public List<String> getData() throws Exception {
        List<String> output = new ArrayList<>();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("general.useragent.override","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless");
        WebDriver driver = new FirefoxDriver( new FirefoxOptions().addPreference("general.useragent.override","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless"));
        driver.get(url);
        Thread.sleep(4000);

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

        try {
            WebElement Acceptbutton = driver.findElement(By.xpath("//button[text()='Akceptuję']"));
            Acceptbutton.click();
        }catch (Exception e){
            WebElement Acceptbutton = driver.findElement(By.xpath("//button[text()='I accept']"));
            Acceptbutton.click();
        }
        Thread.sleep(4000);
        Thread.sleep(4000);
        Thread.sleep(4000);
        Thread.sleep(4000);

        WebElement button = driver.findElement(By.xpath("//button[text()='Load earlier flights']"));
        button.click();

        Thread.sleep(4000);
        button.click();
        Thread.sleep(4000);
        Thread.sleep(4000);

        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//tr"));
        for(WebElement ele:allHeaders) {
            if(ele.getText()=="" || ele.getText().contains("TIME") || ele.getText().contains("Scheduled")|| ele.getText().contains("Estimated") || ele.getText().contains("later") || ele.getText().contains("local")) {
                continue;
            }
            output.add(ele.getText());
        }

        driver.quit();
        return output;
    }
}