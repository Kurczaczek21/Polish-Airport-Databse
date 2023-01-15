import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class GraphByDayOfWeek {
    private static final Logger logger= Logger.getLogger(GraphByDayOfWeek.class);
    public ChartPanel ramka(String airport, Boolean isArrivals, Boolean isDelayGraph) {

        int mondayFlights=0;
        int mondayDelay=0;
        int mondays=0;

        int tuesdayFlights=0;
        int tuesdayDelay=0;
        int tuesdays=0;

        int wednesdayFlights=0;
        int wednesdayDelay=0;
        int wednesdays=0;

        int thursdayFlights=0;
        int thursdayDelay=0;
        int thursdays=0;

        int fridayFlights=0;
        int fridayDelay=0;
        int fridays=0;

        int saturdayFlights=0;
        int saturdayDelay=0;
        int saturdays=0;

        int sundayFlights=0;
        int sundayDelay=0;
        int sundays=0;



        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String manuever = "Departures";
        if(isArrivals){manuever="Arrivals";}

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream("DataBase\\"+airport+"_"+manuever+".xlsx"));
            logger.info("File: "+airport+"_"+manuever+".xlsx successfully opened.");
        }catch (IOException e){
            logger.error("Error occured:"+e);
            System.exit(0);
        }
        XSSFSheet sheet = workbook.getSheet("Main Data");
        int lastRowNum = sheet.getLastRowNum();

        String lastDay = "Friday";

        for(int i=0;i<lastRowNum;i++){
            Row row = sheet.getRow(i);
            if(!row.getCell(9).getStringCellValue().contains("AM") && !row.getCell(9).getStringCellValue().contains("PM")){continue;}
            Cell dateCell = row.getCell(1);
            Cell delayCell = row.getCell(11);
            if(dateCell.getStringCellValue().contains("Monday")){
                if(!lastDay.equals("Monday")){
                    mondays++;
                    lastDay="Monday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    mondayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    mondayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Tuesday")) {
                if(!lastDay.equals("Tuesday")){
                    tuesdays++;
                    lastDay="Tuesday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    tuesdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    tuesdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Wednesday")){
                if(!lastDay.equals("Wednesday")){
                    wednesdays++;
                    lastDay="Wednesday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    wednesdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    wednesdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Thursday")){
                if(!lastDay.equals("Thursday")){
                    thursdays++;
                    lastDay="Thursday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    thursdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    thursdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Friday")){
                if(!lastDay.equals("Friday")){
                    fridays++;
                    lastDay="Friday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    fridayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    fridayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Saturday")){
                if(!lastDay.equals("Saturday")){
                    saturdays++;
                    lastDay="Saturday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    saturdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    saturdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Sunday")){
                if(!lastDay.equals("Sunday")){
                    sundays++;
                    lastDay="Sunday";
                }
                if(delayCell.getStringCellValue()!=null) {
                    sundayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    sundayDelay+=(hours*60)+min;
                }
            }
        }

        System.out.println(mondayDelay);
        System.out.println(mondayFlights);

        System.out.println(tuesdayDelay);
        System.out.println(tuesdayFlights);

        System.out.println(wednesdayDelay);
        System.out.println(wednesdayFlights);

        System.out.println(fridayDelay);
        System.out.println(fridayFlights);

        if(!isDelayGraph){   // BY NUMBER OF FLIGHTS
            if(mondays!=0) {
                dataset.setValue(mondayFlights / mondays, "Flights", "Monday");
            }else {dataset.setValue(0, "Flights", "Monday");}
            if(tuesdays!=0) {
                dataset.setValue(tuesdayFlights/tuesdays,"Flights","Tuesday");
            }else {dataset.setValue(0, "Flights", "Tuesday");}
            if(wednesdays!=0) {
                dataset.setValue(wednesdayFlights/wednesdays,"Flights","Wednesday");
            }else {dataset.setValue(0, "Flights", "Tuesday");}
            if(thursdays!=0) {
                dataset.setValue(thursdayFlights/thursdays,"Flights","Thursday");
            }else {dataset.setValue(0, "Flights", "Thursday");}
            if(fridays!=0) {
                dataset.setValue(fridayFlights/fridays,"Flights","Friday");
            }else {dataset.setValue(0, "Flights", "Friday");}
            if(saturdays!=0) {
                dataset.setValue(saturdayFlights/saturdays,"Flights","Saturday");
            }else {dataset.setValue(0, "Flights", "Saturday");}
            if(sundays!=0) {
                dataset.setValue(sundayFlights/sundays,"Flights","Sunday");
            }else {dataset.setValue(0, "Flights", "Sunday");}
        }else{  // BY DELAY
            if(mondayFlights!=0) {
                dataset.setValue(mondayDelay / mondayFlights, "Flights", "Monday");
            }else {dataset.setValue(0, "Flights", "Monday");}
            if(tuesdayFlights!=0) {
                dataset.setValue(tuesdayDelay/tuesdayFlights,"Flights","Tuesday");
            }else {dataset.setValue(0, "Flights", "Tuesday");}
            if(wednesdayFlights!=0) {
                dataset.setValue(wednesdayDelay/wednesdayFlights,"Flights","Wednesday");
            }else {dataset.setValue(0, "Flights", "Tuesday");}
            if(thursdayFlights!=0) {
                dataset.setValue(thursdayDelay/thursdayFlights,"Flights","Thursday");
            }else {dataset.setValue(0, "Flights", "Thursday");}
            if(fridayFlights!=0) {
                dataset.setValue(fridayDelay/fridayFlights,"Flights","Friday");
            }else {dataset.setValue(0, "Flights", "Friday");}
            if(saturdayFlights!=0) {
                dataset.setValue(saturdayDelay/saturdayFlights,"Flights","Saturday");
            }else {dataset.setValue(0, "Flights", "Saturday");}
            if(sundayFlights!=0) {
                dataset.setValue(sundayDelay/sundayFlights,"Flights","Sunday");
            }else {dataset.setValue(0, "Flights", "Sunday");}

        }

        String title = "Number of Arrivals by day of week ";
        String graphInfo = "Average delay in minutes ";
        if(!isDelayGraph){
            graphInfo="Number of flights";
        }

        if(!isArrivals && !isDelayGraph){
            title = "Number of Departures by day of week ";
        }
        if(isArrivals && isDelayGraph){
            title = "Average delay of Arrivals by day of week ";
        }
        if(!isArrivals && isDelayGraph){
            title = "Average delay of Departures by day of week ";
        }

        logger.info("Created \""+title+"\" graph.");

        JFreeChart chart = ChartFactory.createBarChart3D(
                title,
                "Day of week",
                graphInfo,
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.gray);
        plot.getDataRange(new NumberAxis());

        CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();

        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);

        ChartPanel graphPanel = new ChartPanel(chart);

        return  graphPanel;
    }

}
