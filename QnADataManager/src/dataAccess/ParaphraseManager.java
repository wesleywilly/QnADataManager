/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;
import java.io.*;
import java.* ;
import dataModel.Paraphrase ;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/*
 * 
 *
 * @author Pedro Vinícius
 */

public class ParaphraseManager 
{
        private static final String SEPARATOR = " ||| " ; 
        private static final String FILEPATH  = "../pack/ppdb-2.0-s-all (1)";
                
      public static List<Paraphrase> ImportPack() {


        // This will reference one line at a time
        String line = null;
        List<Paraphrase> paraphrases = new ArrayList<Paraphrase>() ;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(FILEPATH);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String linha = line.toString() ;
                
                int index1 = linha.indexOf(SEPARATOR, 0) ;
                int index2 = linha.indexOf(SEPARATOR, index1+SEPARATOR.length()) ;
                
                String par1 = linha.substring(index1+SEPARATOR.length(), index2) ;
               
                
                int index3 = linha.indexOf(SEPARATOR, index2+SEPARATOR.length()) ;
                String par2 = linha.substring(index2+4, index3) ;
                par1 = nlp.TextHandler.removeSymbolsFromBegin(par1) ;
                par2 = nlp.TextHandler.removeSymbolsFromBegin(par2) ;
                
                
                Paraphrase p = new Paraphrase(par1, par2) ;
                paraphrases.add(p) ;
                
                
                
                
                }
               
            

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILEPATH + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILEPATH + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return paraphrases;
      }        
}
       
      


    




