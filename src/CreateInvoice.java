import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

        String columns[]={"NUMBER","DATE","TIME", "FLIGHT", "FROM","SHORT", "AIRLINE", "MODEL","AIRCFAT ID", "STATUS"};
        XSSFRow row = sheet.createRow(0);

        for(int i=0;i< columns.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }




        List<String> mainData = new WebScrape().getData();
        String date="";

        for(String ele:mainData) {
            String line = ele;
            if(line.contains(",")){
                date=line;
                continue;
            }
            String lines[] = line.split("\\r?\\n");
            String flightData[] = lines[0].split(" ");  // 0-> H , +" "+ 1-> AM/PM , 2-> flight number
            String airportData[] = lines[1].split(" "); // 0 -> airport, 1-> shortname
            String planeData[] = lines[2].split(" "); // 0-> airlines, 1-> model, 2-> tag !!!!!  2 od konca to model, ostatnie to ID



            int index=0;
            row = sheet.createRow(index+1);

            for (int j=0;j<= columns.length;j++) {

                System.out.println(index+1);
                System.out.println(date);
                System.out.println(flightData[0]);
                System.out.println(flightData[1]);
                System.out.println(Arrays.toString(lines));
                System.out.println(Arrays.toString(flightData));
                System.out.println(Arrays.toString(airportData));
                System.out.println(Arrays.toString(planeData));
                System.out.println(flightData[2]);
                System.out.println(airportData[0]);
                System.out.println(airportData[1]);
                System.out.println(planeData.length);
                System.out.println(planeData[0]);
                System.out.println(planeData[1]);
                System.out.println(planeData[planeData.length-2]);
                System.out.println(planeData[planeData.length-1]);
                System.out.println("----------------------------------");


                Cell cell = row.createCell(j);
                if(cell.getColumnIndex()==0){
                    cell.setCellValue(index+1);
                } else if(cell.getColumnIndex()==1){
                    cell.setCellValue(date);
                } else if (cell.getColumnIndex()==2) {
                    cell.setCellValue(flightData[0]+" "+flightData[1]);
                }else if(cell.getColumnIndex()==3){
                    cell.setCellValue(flightData[2]);
                } else if (cell.getColumnIndex()==4) {
                    cell.setCellValue(airportData[0]);
                }else if(cell.getColumnIndex()==5){
                    cell.setCellValue(airportData[1]);
                } else if (cell.getColumnIndex()==6) {
                    if(planeData.length==3) {
                        cell.setCellValue(planeData[0]);
                    }else {
                        cell.setCellValue(planeData[0]+" "+planeData[1]);
                    }
                } else if (cell.getColumnIndex()==7) {
                    cell.setCellValue(planeData[planeData.length-2]);
                }else if (cell.getColumnIndex()==8) {
                    cell.setCellValue(planeData[planeData.length-1]);
                }else if (cell.getColumnIndex()==9) {
                    cell.setCellValue(lines[3]);
                }
            }
            index+=1;
        }





        try (FileOutputStream outputStream = new FileOutputStream("Flights_data.xlsx")) {
            workbook.write(outputStream);
            logger.info("Excel file created success");
        }
    }

}