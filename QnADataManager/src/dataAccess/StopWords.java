/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author wesley
 */
public class StopWords {
    private static final String STOP_WORDS_FILE_PATH = "../stopwords.txt";
    
    
    public static boolean contains(String word){
        Boolean stopword = false;
        try{
            FileReader fileReader = new FileReader(STOP_WORDS_FILE_PATH);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            
            String line;
            
            while((line = bufferReader.readLine()) !=  null && !stopword){
                if(line.contains(word)){
                    stopword = true;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return stopword;
    }
    
}
