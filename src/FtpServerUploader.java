//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//
//public class FtpServerUploader {
//
//    public static void main(String[] args) {
//        String server = new FtpPassword().getServer();
//        int port = new FtpPassword().getPort();
//        String user = new FtpPassword().getUser();
//        String pass = new FtpPassword().getPass();
//
//        FTPClient ftpClient = new FTPClient();
//
//        try {
//            ftpClient.connect(server);
//            ftpClient.login(user,pass);
//            System.out.println(ftpClient.getStatus());
//            ftpClient.enterLocalPassiveMode();
//            System.out.println(ftpClient.getStatus());
//
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//            File LocalFile = new File("C:/Users/Mateusz/Documents/Java Stuff/ABCD.txt");
//
//            String remoteFile = "FlightRadarData/ABCD.txt";
//            InputStream inputStream = new FileInputStream(LocalFile);
//
//            System.out.println("Start uploading first file");
//            boolean done = ftpClient.storeFile(remoteFile, inputStream);
//            inputStream.close();
//
//            if (done) {
//                System.out.println("The first file is uploaded using FTP successfully.");
//            }
//
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//
//        } finally {
//
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}