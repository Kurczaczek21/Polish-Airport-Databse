import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

/**
 * A very simple program that writes some data to an Excel file
 * using the Apache POI library.
 * @author www.codejava.net
 *
 */
public class CreateInvoice {

    private static Logger logger= LogManager.getLogger(WebScrape.class);

    public static void main(String[] args) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        String columns[]={"DATE","TIME", "FLIGHT", "FROM","SHORT", "AIRLINE", "MODEL","AIRCFAT ID", "STATUS"};

        List<String> mainData = new WebScrape().getData();
        String date="";

        for(String ele:mainData) {
            System.out.println(ele);
            System.out.println("---------------");
            String line = ele;
            String lines[] = line.split("\\r?\\n");
            System.out.println(Arrays.toString(lines));
            String flightData[] = lines[0].split(" ");  // 0-> H , +" "+ 1-> AM/PM , 2-> flight number
            String airportData[] = lines[1].split(" "); // 0 -> airport, 1-> shortname
            String planeData[] = lines[2].split(" "); // 0-> airlines, 1-> model, 2-> tag !!!!!  2 od konca to model, ostatnie to ID

            int rowCount = 0;

            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (int i=0;i<10;i++) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }
//            if(ele.getText().contains(",")){
//                System.out.println(ele.getText());
//                continue;
//            }                                                       //Arrays.toString(array)
//            String line = ele.getText();
//            String lines[] = line.split("\\r?\\n");
//            logger.info(Arrays.toString(lines));
//            System.out.println(Arrays.toString(lines));
//            String flightData[] = lines[0].split(" ");  // 0-> H , 1-> AM/PM , 2-> flight number
//            String airportData[] = lines[1].split(" "); // 0 -> airport, 1-> shortname
//            String planeData[] = lines[2].split(" "); // 0-> airlines, 1-> model, 2-> tag
//            System.out.println(date);
//            System.out.println(flightData[0]);
//            System.out.println(airportData[0]);
//            System.out.println(planeData[0]);
//            System.out.println("-----------------------");
////            model.addRow(new Object[]{date,flightData[0],flightData[1], flightData[2],airportData[0],airportData[1],planeData[0], planeData[1], planeData[2], lines[3]});
//        }

        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };




        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
    }

}