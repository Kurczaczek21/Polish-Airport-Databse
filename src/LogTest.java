import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static Logger logger= LogManager.getLogger(LogTest.class);
    //Jtable filtrowanie !

    public static void main(String[] args){
        System.out.println("start \n");
        logger.info("hello test");
        logger.error("error msg");
        logger.warn("warnn");
        logger.fatal("fatal");

        System.out.println("\n"+ logger.getName());
    }
}
