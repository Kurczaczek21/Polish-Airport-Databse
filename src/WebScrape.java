import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class WebScrape {
    private final static String url = "https://www.flightradar24.com/data/airports/krk/arrivals";
    private static Logger logger= LogManager.getLogger(WebScrape.class);

    public static void main(String[] args) throws Exception {

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

        // JTABLE

        String columns[]={"DATE","TIME","AM/PM", "FLIGHT", "FROM","SHORT", "AIRLINE", "MODEL","AIRCFAT ID", "STATUS"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        for (String s:columns
             ) {
            System.out.println(s);
            model.addColumn(s);
        }
        JFrame frame = new JFrame("Window");
        frame.getContentPane().setBounds(400,400,400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(100,100,100,100);
        frame.getContentPane().add(pane);

        Object[] row  = new Object[4];

        frame.setVisible(true);

        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@class,'table table-condensed table-hover data-table m-n-t-15')]//tr"));
        System.out.println(allHeaders.size());
        for(WebElement ele:allHeaders)
        {
            String date;
            if(ele.getText()=="" || ele.getText().contains("TIME") || ele.getText().contains("Scheduled")) {
                continue;
            }
            if(ele.getText().contains(",")){
                date=ele.getText();
                continue;
            }                                                       //Arrays.toString(array)
            String line = ele.getText();
            String lines[] = line.split("\\r?\\n");
            logger.info(Arrays.toString(lines));
            String flightData[] = lines[0].split(" ");  // 0-> H , 1-> AM/PM , 2-> flight number
            String airportData[] = lines[1].split(" "); // 0 -> airport, 1-> shortname
            String planeData[] = lines[2].split(" "); // 0-> airlines, 1-> model, 2-> tag
            model.addRow(flightData);
            model.addRow(airportData);
            model.addRow(planeData);
        }

        driver.quit();


    }
}