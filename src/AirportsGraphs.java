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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class AirportsGraphs {
    private static final Logger logger= Logger.getLogger(AirportsGraphs.class);
    public ChartPanel ramka(String airport, Boolean isArrivals, Boolean isDelayGraph) {

        HashMap<String, Integer> airlineNumber = new HashMap<String, Integer>();
        HashMap<String, Integer> airlineDelays = new HashMap<String, Integer>();

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

        for(int i=0;i<lastRowNum;i++){
            Row row = sheet.getRow(i);
            if(!row.getCell(9).getStringCellValue().contains("AM") && !row.getCell(9).getStringCellValue().contains("PM")){continue;}
            Cell airlineCell = row.getCell(6);
            Cell delayCell = row.getCell(11);

            if(!airlineNumber.containsKey(airlineCell.getStringCellValue().split("\\(")[0])) {
                airlineNumber.put(airlineCell.getStringCellValue().split("\\(")[0], 1);
            }else {airlineNumber.put(airlineCell.getStringCellValue().split("\\(")[0], airlineNumber.get(airlineCell.getStringCellValue().split("\\(")[0]) + 1 );}

            if(!airlineDelays.containsKey(airlineCell.getStringCellValue().split("\\(")[0])) {
                int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                int delay = (hours * 60) + min;
                airlineDelays.put(airlineCell.getStringCellValue().split("\\(")[0],delay );
            }else {
                int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                int delay2 = (hours * 60) + min;
                airlineDelays.put(airlineCell.getStringCellValue().split("\\(")[0], airlineDelays.get(airlineCell.getStringCellValue().split("\\(")[0]) + delay2 );}
        }

        if(!isDelayGraph){   // BY NUMBER OF FLIGHTS
            airlineNumber.forEach((airportName, flights) -> {
                dataset.setValue(flights,"Flights",airportName);
            });
        }else{  // BY DELAY
            airlineDelays.forEach((airportName, delay) -> {
                int averageDelay= airlineDelays.get(airportName)/airlineNumber.get(airportName);
                dataset.setValue(averageDelay,"Flights",airportName);
            });
        }

        String title = "Number of Arrivals by airline ";
        String graphInfo = "Average delay in minutes ";
        if(!isDelayGraph){
            graphInfo="Number of flights";
        }

        if(!isArrivals && !isDelayGraph){
            title = "Number of Departures by airline ";
        }
        if(isArrivals && isDelayGraph){
            title = "Average delay of Arrivals by airline ";
        }
        if(!isArrivals && isDelayGraph){
            title = "Average delay of Departures by airline ";
        }

        logger.info("Created \""+title+"\" graph.");

        JFreeChart chart = ChartFactory.createBarChart3D(
                title,
                "Airline",
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
