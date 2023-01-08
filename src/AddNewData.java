import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddNewData {

    private static final Logger logger= LogManager.getLogger(WebScrape.class);

    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream("Flights_data.xlsx"));
            logger.info("File successfully opened.");
        }catch (FileNotFoundException e){
            logger.error("File not found.");
            System.exit(0);
        }

        XSSFSheet sheet = workbook.getSheet("Main Data");

        int lastRowNum = sheet.getLastRowNum();
        Row lastRow = sheet.getRow(lastRowNum);
        String lastDataInSheet ="";
        for(int i=2;i<10;i++){
            Cell cell = lastRow.getCell(i);
            lastDataInSheet = lastDataInSheet + " "+ cell.getStringCellValue() ;
            System.out.println(lastDataInSheet);
        }
        String[] lastRowArray = lastDataInSheet.split(" ");
        String lastDate = lastRow.getCell(1).getStringCellValue();
        boolean found = false;

        int index = (int)lastRow.getCell(0).getNumericCellValue();
        int start = index;

        logger.info("Getting data from website ...");
        List<String> mainData = new WebScrape().getData();
        String date = "";

        for (String ele : mainData) {
            if (ele.contains(",")) {
                date = ele;
                continue;
            }

            int check=0;
            for (String item : lastRowArray) {
                if (ele.contains(item) && !found && date.equals(lastDate)) {
                    check++;
                }
            }
            if(check==lastRowArray.length){
                found = true;
                continue;
            }
            if(!found){
                continue;
            }

            String[] lines = ele.split("\\r?\\n"); // Arrays.toString(lines)
            String[] flightData;
            String[] airportData;
            String[] planeData;
            if (lines.length == 4) {
                flightData = lines[0].split(" ");  // 0-> H , +" "+ 1-> AM/PM , 2-> flight number
                airportData = lines[1].split(" "); // 0 -> airport, 1-> shortname
                planeData = lines[2].split(" "); // 0-> airlines, 1-> model, 2-> tag !!!!!  2 od konca to model, ostatnie to ID
            } else {
                flightData = (lines[0] + " " + lines[1]).split(" ");
                airportData = lines[2].split(" ");
                planeData = lines[3].split(" ");
            }
            if(planeData[0].equals("-") || flightData.length==2 || !planeData[planeData.length-1].contains("(") ){
                continue;
            }

            Row row = sheet.createRow(index + 1);
            logger.info("Putting data into database ...");
            for (int j = 0; j <= 10; j++) {

                Cell cell = row.createCell(j);
                if (cell.getColumnIndex() == 0) {
                    cell.setCellValue(index + 1);
                } else if (cell.getColumnIndex() == 1) {
                    cell.setCellValue(date);
                } else if (cell.getColumnIndex() == 2) {
                    cell.setCellValue(flightData[0] + " " + flightData[1]);
                } else if (cell.getColumnIndex() == 3) {
                    cell.setCellValue(flightData[2]);
                } else if (cell.getColumnIndex() == 4) {
                    if(airportData.length ==2) {
                        cell.setCellValue(airportData[0]);
                    }else {
                        cell.setCellValue(airportData[0]+" "+ airportData[1]);
                    }
                } else if (cell.getColumnIndex() == 5) {
                    cell.setCellValue(airportData[airportData.length-1]);
                } else if (cell.getColumnIndex() == 6) {
                    String aircraft ="";
                    for (int z = 0; z < planeData.length - 2; z++) {
                        aircraft += planeData[z] + " ";
                    }
                    cell.setCellValue(aircraft);
                } else if (cell.getColumnIndex() == 7) {
                    cell.setCellValue(planeData[planeData.length - 2]);
                } else if (cell.getColumnIndex() == 8) {
                    cell.setCellValue(planeData[planeData.length - 1]);
                } else if (cell.getColumnIndex() == 9) {
                    if (lines.length == 4) {
                        cell.setCellValue(lines[3]);
                    } else {
                        cell.setCellValue(lines[4]);
                    }
                }
            }
            index += 1;
        }

        try (FileOutputStream outputStream = new FileOutputStream("Flights_data.xlsx")) {
            workbook.write(outputStream);
            logger.info("Excel file updated successfully.");
            logger.info("Added " + String.valueOf(index-start)+" new flights to database."  );
            logger.info("Currently there is " + String.valueOf(index)+" flights in database."  );
        }catch (FileAlreadyExistsException e){
            logger.error("File alredy exists.");
        }
    }
}