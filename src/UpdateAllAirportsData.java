import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateAllAirportsData {
    private static final Logger logger = LogManager.getLogger(WebScrape.class);

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        String airport1 = "KRK";
        String airport2 = "BZG";
        String airport3 = "GDN";
        String airport4 = "KTW";
        String airport5 = "LCJ";     // lodz
        String airport6 = "LUZ";     // Lublin
        String airport7 = "SZY";     // Szymany - Olsztyn
        String airport8 = "POZ";
        String airport9 = "WMI";     // Warsaw Modlin
        String airport10 = "WAW";
        String airport11 = "RZE";

        String[] airports ={"KRK","BZG","GDN","KTW","LCJ","LUZ","SZY","POZ","WMI","WAW","RZE"};

        for(String airport:airports){
            Thread thread1 = new Thread() {
                public void run() {
                    try {
//                    new CreateNewArrivalsDatabase().createArrivalsFromAirport(airport);
                        new AddNewArrivalData().appendArrivalsFromAirport(airport);
                    } catch (Exception e) {
                        logger.info(airport+"XXXXXXXXXXXXXXXXXXXX CRASHED !!! in arr");
                        throw new RuntimeException(e);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    try {
//                    new CreateNewDeparturesDatabase().appendDeparturesFromAirport(airport);
                        new AddNewDeparturesData().appendDeparturesFromAirport(airport);
                    } catch (Exception e) {
                        logger.info(airport+"XXXXXXXXXXXXXXXXXXXX CRASHED !!! in dep");
                        throw new RuntimeException(e);
                    }
                }
            };
            thread1.start();
            thread2.start();
//
            thread1.join();
            thread2.join();
        }

//        new AddNewArrivalData().appendArrivalsFromAirport("POZ");
//        new AddNewDeparturesData().appendDeparturesFromAirport("GDN");

        logger.info("Data for all airports updated successfully.");     // Tu zwraca co i jak czy git ktore byly :)
        long finish = System.currentTimeMillis();
        logger.info("Process took about "+ (finish - start)/60000L +" minutes and "+ ((finish - start)%60000L)/1000L+" seconds.");


    }
}
