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
import java.util.HashMap;

public class GraphByAllAirports {
    private static final Logger logger= Logger.getLogger(GraphByAllAirports.class);

    public ChartPanel ramka(Boolean isArrivals, Boolean isDelayGraph) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String manuever = "Departures";
        if(isArrivals){manuever="Arrivals";}
        String lastDay = "Friday";


        String[] airports ={"KRK","BZG","GDN","KTW","LCJ","LUZ","SZY","POZ","WMI","WAW","RZE"};
        XSSFWorkbook workbook = null;

        for(String currentAirport:airports){
            try {
                workbook = new XSSFWorkbook(new FileInputStream("DataBase\\"+currentAirport+"_"+manuever+".xlsx"));
                logger.info("File: "+currentAirport+"_"+manuever+".xlsx successfully opened.");
            }catch (IOException e){
                logger.error("Error occured:"+e);
                System.exit(0);
            }

            XSSFSheet sheet = workbook.getSheet("Main Data");
            int lastRowNum = sheet.getLastRowNum();
            int delay=0;
            int numberOfFlights=0;
            int days=0;

            for(int i=0;i<lastRowNum;i++) {

                String name = currentAirport;


                Row row = sheet.getRow(i);

                if (!row.getCell(9).getStringCellValue().contains("AM") && !row.getCell(9).getStringCellValue().contains("PM")) {continue;}

                Cell delayCell = row.getCell(11);
                int hours = Integer.parseInt(delayCell.getStringCellValue().split(" ")[0]);
                int min = Integer.parseInt(delayCell.getStringCellValue().split(" ")[2]);
                delay += (hours * 60) + min;

                Cell dateCell = row.getCell(1);
                if(!lastDay.equals(dateCell.getStringCellValue().split(",")[0])){
                    days++;
                    lastDay=dateCell.getStringCellValue().split(",")[0];
                }

                if(i==lastRowNum-1){
                    numberOfFlights = (int) row.getCell(0).getNumericCellValue();
                }
            }
            if(isDelayGraph) {
                dataset.setValue(delay/numberOfFlights, "Flights", currentAirport);
            }else {
                dataset.setValue(numberOfFlights/days, "Flights", currentAirport);
            }
        }

        String title = "Number of Arrivals by airport ";
        String graphInfo = "Average delay in minutes ";
        if(!isDelayGraph){
            graphInfo="Number of flights";
        }

        if(!isArrivals && !isDelayGraph){
            title = "Number of Departures by airport ";
        }
        if(isArrivals && isDelayGraph){
            title = "Average delay of Arrivals by airport ";
        }
        if(!isArrivals && isDelayGraph){
            title = "Average delay of Departures by airport ";
        }

        logger.info("Created \""+title+"\" graph.");

        JFreeChart chart = ChartFactory.createBarChart3D(
                title,
                "Airport",
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
