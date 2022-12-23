import java.io.IOException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



/*
	https://jsoup.org/download
	jsoup-1.13.1.jar core library
	jsoup-1.13.1-sources.jar optional sources jar
	jsoup-1.13.1-javadoc.jar optional javadoc jar
*/


    public class Lyngsat1 {

        private final static String url = "https://www.flightradar24.com/data/airports/krk/arrivals";

        public static void main(String[] args) throws IOException {


            // Parse the web page
            Document doc = Jsoup.connect(url).get();    // wywolujemy klasye podstawowa jsopu. na klasie jsoup wywoluemy metode connect
            // metoda get zwroci dokument co jest pod tym adresem; sout doc -> caly html

/*

<table width=468 border=1 cellspacing=0 cellpadding=2 bgcolor="lightyellow">
<tr>
<td width=24%><br></td>
<td align="center" width=19%><font face="Arial" size=2><b>160°W-73°E</b></td>
<td align="center" width=19%><font face="Arial" size=2><b>73°E-0°E</b></td>
<td align="center" width=19%><font face="Arial" size=2><b>0°W-61°W</b></td>
<td align="center" width=19%><font face="Arial" size=2><b>61°W-160°W</b></td>
</tr>
<tr>
<td><font face="Arial" size=2><b>Satellites:</b></td>
<td align="center"><font face="Arial" size=2><b><a href="asia.html">Asia</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="europe.html">Europe</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="atlantic.html">Atlantic</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="america.html">America</a></b></td>
</tr>
<tr>
<td><font face="Arial" size=2><b>Packages:</b></td>
<td align="center"><font face="Arial" size=2><b><a href="packages/asia.html">Asia</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="packages/europe.html">Europe</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="packages/atlantic.html">Atlantic</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="packages/america.html">America</a></b></td>
</tr>
<tr>
<td><font face="Arial" size=2><b>HD TV:</b></td>
<td align="center"><font face="Arial" size=2><b><a href="hd/asia.html">Asia</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="hd/europe.html">Europe</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="hd/atlantic.html">Atlantic</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="hd/america.html">America</a></b></td>
</tr>
<!--
<tr>
<td><font face="Arial" size=2><b>SatTracker:</b></td>
<td align="center"><font face="Arial" size=2><b><a href="tracker/asia.html">Asia</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="tracker/europe.html">Europe</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="tracker/atlantic.html">Atlantic</a></b></td>
<td align="center"><font face="Arial" size=2><b><a href="tracker/america.html">America</a></b></td>
</tr>
-->
</table>

*/

            // Choose TAG: TABLE where width=468 and border=1

//            Elements media = doc.select("table[width=468][border=1]");
//
//            if (media.size()!=1) {
//                System.out.println(("Expected one row only!\n"+media));
//                System.exit(1);
//            }
            System.out.println(doc.outerHtml());
//            System.out.println(media);
        }

    }
