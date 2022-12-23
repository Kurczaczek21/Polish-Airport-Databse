import java.io.IOException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/*
	https://jsoup.org/download
	jsoup-1.13.1.jar core library
	jsoup-1.13.1-sources.jar optional sources jar
	jsoup-1.13.1-javadoc.jar optional javadoc jar
*/


    public class Lyngsat1 {

        private final static String url = "https://www.flightradar24.com/data/flights/lo3923";

        public static void main(String[] args) throws IOException {


            // Parse the web page
            Document doc = Jsoup.connect(url).get();    // wywolujemy klasye podstawowa jsopu. na klasie jsoup wywoluemy metode connect
            // metoda get zwroci dokument co jest pod tym adresem; sout doc -> caly html


            for (Element row : doc.select("table#tbl-datatable")){
                System.out.println(row);
            }

//            for (Element row : doc.select("tr.hidden-md.hidden-lg.ng-scope")){
//                System.out.println(doc.select("tr.hidden-md.hidden-lg.ng-scope"));


            // Choose TAG: TABLE where width=468 and border=1

//            Elements media = doc.select("table[width=468][border=1]");
//
//            if (media.size()!=1) {
//                System.out.println(("Expected one row only!\n"+media));
//                System.exit(1);
//            }
//            System.out.println(doc.outerHtml());
//            System.out.println(media);
        }

    }
