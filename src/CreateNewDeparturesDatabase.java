import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateNewDeparturesDatabase {

    private static final Logger logger= Logger.getLogger(CreateNewDeparturesDatabase.class);

    public void appendDeparturesFromAirport(String airportName) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Main Data");

        String[] columns = {"NUMBER", "DATE", "TIME", "FLIGHT", "TO", "SHORT", "AIRLINE", "MODEL", "AIRCFAT ID", "STATUS","DIFFERENCE"};
        XSSFRow row = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        logger.info("Getting data from website ...");
        String url = "https://www.flightradar24.com/data/airports/"+airportName+"/departures";
        List<String> mainData = new WebScrape().getData(url);
        String date = "";
        String differenceTime1 ="";
        String differenceTime2 ="";
        Boolean tookOff=true;
        Boolean reverseTime=false;
        Date d1 = null;
        Date d2 = null;
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat formatTimeDay = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        int index = 0;

        for (String ele : mainData) {
            if (ele.contains(",")) {
                date = ele;
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
                System.out.println(Arrays.toString(lines));

                flightData = (lines[0] + " " + lines[1]).split(" ");
                airportData = lines[2].split(" ");
                planeData = lines[3].split(" ");
            }
            if(planeData[0].equals("-") || !planeData[planeData.length-1].contains("(") ){
                continue;
            }

            row = sheet.createRow(index + 1);

            for (int j = 0; j <= columns.length+1; j++) {

                Cell cell = row.createCell(j);
                if (cell.getColumnIndex() == 0) {
                    cell.setCellValue(index + 1);
                } else if (cell.getColumnIndex() == 1) {
                    cell.setCellValue(date);
                } else if (cell.getColumnIndex() == 2) {
                    cell.setCellValue(flightData[0] + " " + flightData[1]);
                    differenceTime1=flightData[0] + " " + flightData[1];
                } else if (cell.getColumnIndex() == 3) {
                    if(flightData.length==2){
                        cell.setCellValue("UNKNOWN");
                    }else {
                        cell.setCellValue(flightData[2]);
                    }
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
                        if(lines[3].contains(":")) {
                            cell.setCellValue(lines[3].replace("Departed ",""));
                            differenceTime2=lines[3].replace("Departed ","");
                            tookOff=true;
                        }else {
                            cell.setCellValue(lines[3]);
                            tookOff=false;
                        }
                    } else {
                        if(lines[4].contains(":")) {
                            cell.setCellValue(lines[4].replace("Departed ",""));
                            differenceTime2=lines[4].replace("Departed ","");
                            tookOff=true;
                        }else {
                            cell.setCellValue(lines[4]);
                            tookOff=false;
                        }
                    }
                }else if (cell.getColumnIndex() == 11) {
                    if(tookOff){
                        d1 = formatTime.parse(differenceTime1);
                        d2 = formatTime.parse(differenceTime2);
                        long diff = d2.getTime() - d1.getTime();

                        long diffMinutes = diff / (60 * 1000) % 60;
                        long diffHours = diff / (60 * 60 * 1000) % 24;
                        if(diffHours>12 || diffHours<(-12)){
                            d1 = formatTimeDay.parse("10/05/2022 "+differenceTime1);
                            d2 = formatTimeDay.parse("11/05/2022 "+differenceTime2);
                            diff = d2.getTime() - d1.getTime();

                            diffMinutes = diff / (60 * 1000) % 60;
                            diffHours = diff / (60 * 60 * 1000) % 24;
                        }
                        cell.setCellValue(diffHours + " hours, "+diffMinutes + " minutes");

                    }
                }
            }
            index += 1;
        }

        try (FileOutputStream outputStream = new FileOutputStream("DataBase\\"+airportName+"_Departures.xlsx")) {
            workbook.write(outputStream);
            outputStream.close();
            logger.info("Excel file with departures for "+airportName+" airport created successfully");
        }catch (FileAlreadyExistsException e){
            logger.error("File already exists");
        }
    }
}