/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrowler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author wesley
 */
public class WebCrowler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean ok = false;
        while (!ok) {
            try {
                Document doc = Jsoup.connect("http://www.answers.com/search?q=list+movies").get();

                Elements h1s = doc.getElementsByClass("title ");
                Elements as = h1s.first().getElementsByTag("a");

                Element a = as.first();

                String link = a.attr("href");

                Document topic_page = Jsoup.connect(link).get();
                ok = true;
                
                System.out.println(topic_page);

            } catch (Exception e) {
                e.printStackTrace();
                if(!ok){
                    try{
                        Thread.sleep(3000);
                    }catch(Exception et){
                        
                    }
                }
            }
        }

    }

}
