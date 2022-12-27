import java.util.logging.Logger;

public class LogTest {
    static Logger logger= Logger.getLogger(String.valueOf(LogTest.class));

    public static void main(String[] args){
        logger.info("hello test");
        System.out.println("hiii");
        System.out.println(logger.getName());
    }
}
