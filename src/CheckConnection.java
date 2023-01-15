import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckConnection {
    private static final Logger logger= Logger.getLogger(CheckConnection.class);

    public Boolean isInternetAvailable() throws IOException {
        logger.info("Checking internet connection");
        return isHostAvailable("google.com") || isHostAvailable("amazon.com")
                || isHostAvailable("facebook.com")|| isHostAvailable("apple.com");
    }
    private static boolean isHostAvailable(String hostName) throws IOException {
        try(Socket socket = new Socket()){
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName,port);
            socket.connect(socketAddress,3000);
            return true;
        }catch (Exception e){
            logger.error("No internet connection :"+e);
            return false;
        }
    }
}
