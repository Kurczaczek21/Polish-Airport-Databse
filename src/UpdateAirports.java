import org.apache.log4j.Logger;

import java.util.ArrayList;

public class UpdateAirports {
    private static final Logger logger = Logger.getLogger(UpdateAllAirportsData.class);

    public UpdateAirports(ArrayList<String> airports) throws Exception {

        long start = System.currentTimeMillis();
        Thread.sleep(4000);

        for (int i = 0; i < airports.size(); i++) {
            int tmp = i;
            Thread thread1 = new Thread() {
                public void run() {
                    try {
                        logger.info("downloading Arrival data for "+airports.get(tmp));
                        new AddNewArrivalData().appendArrivalsFromAirport(airports.get(tmp));
                    } catch (Exception e) {
                        logger.info(airports.get(tmp)+"XXXXXXXXXXXXXXXXXXXX CRASHED !!! in Arrivals");
                        throw new RuntimeException(e);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    try {
                        logger.info("downloading Departure data for "+airports.get(tmp));
                        new AddNewDeparturesData().appendDeparturesFromAirport(airports.get(tmp));
                    } catch (Exception e) {
                        logger.info(airports.get(tmp)+"XXXXXXXXXXXXXXXXXXXX CRASHED !!! in Departures");
                        throw new RuntimeException(e);
                    }
                }
            };
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        }

        logger.info("Data for all airports updated successfully.");     // Tu zwraca co i jak czy git ktore byly :)
        long finish = System.currentTimeMillis();
        logger.info("Process took about "+ (finish - start)/60000L +" minutes and "+ ((finish - start)%60000L)/1000L+" seconds.");


    }
}
