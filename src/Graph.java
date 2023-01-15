import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
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
import java.io.FileNotFoundException;
import java.io.IOException;

public class Graph {
    private static final Logger logger= Logger.getLogger(Graph.class);
    public ChartPanel ramka(String airport, Boolean isArrivals, Boolean isDelayGraph) {

        int mondayFlights=1;
        int mondayDelay=0;

        int tuesdayFlights=1;
        int tuesdayDelay=0;

        int wednesdayFlights=1;
        int wednesdayDelay=0;

        int thursdayFlights=1;
        int thursdayDelay=0;

        int fridayFlights=1;
        int fridayDelay=0;

        int saturdayFlights=1;
        int saturdayDelay=0;

        int sundayFlights=1;
        int sundayDelay=0;



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
        // TODO wyswitel dane -> openfile w lotnisku i zaznaczona opcja -> ez
        //TODO komentarze
        //TODO kolory back guzikow
        // TODO brak internetu = disabled down.
        for(int i=0;i<lastRowNum;i++){      //TODO JESLI DZIEN TYG INNY NIZ POPRZEDNI WIERSZ TO NOWY DZIEN! + KOPIA DANYCH W PUSTY DZIEN XD
            Row row = sheet.getRow(i);      //TODO grafy wszytskie
            if(!row.getCell(9).getStringCellValue().contains("AM") && !row.getCell(9).getStringCellValue().contains("PM")){continue;}
            Cell dateCell = row.getCell(1);
            Cell delayCell = row.getCell(11);
            if(dateCell.getStringCellValue().contains("Monday")){
                if(delayCell.getStringCellValue()!=null) {
                    System.out.println("monday");
                    System.out.println(mondayFlights);
                    mondayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    mondayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Tuesday")) {
                if(delayCell.getStringCellValue()!=null) {
                    tuesdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    tuesdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Wednesday")){
                if(delayCell.getStringCellValue()!=null) {
                    wednesdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    wednesdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Thursday")){
                if(delayCell.getStringCellValue()!=null) {
                    thursdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    thursdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Friday")){
                if(delayCell.getStringCellValue()!=null) {
                    fridayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    fridayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Saturday")){
                if(delayCell.getStringCellValue()!=null) {
                    saturdayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    saturdayDelay+=(hours*60)+min;
                }
            }else if(dateCell.getStringCellValue().contains("Sunday")){
                if(delayCell.getStringCellValue()!=null) {
                    sundayFlights++;
                    int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                    int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                    sundayDelay+=(hours*60)+min;
                }
            }

        }

        if(!isDelayGraph){   // BY NUMBER OF FLIGHTS
            dataset.setValue(mondayFlights,"Flights","Monday");
            dataset.setValue(tuesdayFlights,"Flights","Tuesday");
            dataset.setValue(wednesdayFlights,"Flights","Wednesday");
            dataset.setValue(thursdayFlights,"Flights","Thursday");
            dataset.setValue(fridayFlights,"Flights","Friday");
            dataset.setValue(saturdayFlights,"Flights","Saturday");
            dataset.setValue(sundayFlights,"Flights","Sunday");
        }else{  // BY DELAY
            dataset.setValue(mondayDelay/mondayFlights,"Average delay","Monday");
            dataset.setValue(tuesdayDelay/tuesdayFlights,"Average delay","Tuesday");
            dataset.setValue(wednesdayDelay/wednesdayFlights,"Average delay","Wednesday");
            dataset.setValue(thursdayDelay/thursdayFlights,"Average delay","Thursday");
            dataset.setValue(fridayDelay/fridayFlights,"Average delay","Friday");
            dataset.setValue(sundayDelay/saturdayFlights,"Average delay","Saturday");
            dataset.setValue(saturdayDelay/sundayFlights,"Average delay","Sunday");
        }


        String title = "Number of Arrivals by day of week ";
        if(!isArrivals && !isDelayGraph){
            title = "Number of Departures by day of week ";
        }
        if(isArrivals && isDelayGraph){
            title = "Average delay of Arrivals by day of week ";
        }
        if(!isArrivals && isDelayGraph){
            title = "Average delay of Departures by day of week ";
        }

        JFreeChart chart = ChartFactory.createBarChart3D(
                title,
                "days of week",
                "flights data",
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

//        ChartFrame chartFrame = new ChartFrame("loty od dni",chart,true);
//        chartFrame.setVisible(true);
//        chartFrame.setSize(500,400);
        return  graphPanel;
    }

}
