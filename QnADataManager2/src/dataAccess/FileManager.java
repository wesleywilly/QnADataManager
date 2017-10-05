/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import dataModel.DataSet;
import dataModel.Topic;
import dataModel.TopicItem;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ParserException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class FileManager {

    public static DataSet load(String filePath) {
        DataSet dataSet = null;

        JSONObject jObject = new JSONObject();

        JSONParser parser = new JSONParser();

        try {
            jObject = (JSONObject) parser.parse(new FileReader(filePath));

            dataSet = new DataSet(jObject);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] File not found!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] IO Error!");
        } catch (ParseException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[FILE MANAGER] Inconpatible JSON file!");
        }

        return dataSet;
    }

    public static boolean save(DataSet dataSet, String filePath) {
        boolean saved = false;
        FileWriter writeFile = null;

        try {
            writeFile = new FileWriter(filePath);

            //Escreve no arquivo conteudo do Objeto JSON 
            writeFile.write(dataSet.toJSON().toJSONString());
            writeFile.close();
            saved = true;
        } catch (IOException e) {
            System.out.println("[FILE MANAGER] Error while saving file:");
            e.printStackTrace();
        }
        return saved;
    }

    public static DataSet loadRaw(String filePath) {
        DataSet dataSet = null;

        JSONObject jObject = new JSONObject();

        JSONParser parser = new JSONParser();

        try {
            jObject = (JSONObject) parser.parse(new FileReader(filePath));

            List<Topic> topics = new ArrayList<Topic>();
            
            if (jObject.containsKey("topics")) {
                JSONArray jTopics = (JSONArray) jObject.get("topics");

                if (!jTopics.isEmpty() && jTopics != null) {
                    
                    for (Object object : jTopics) {
                        JSONObject jTopic = (JSONObject) object;

                        TopicItem subject = new TopicItem((String) jTopic.get("subject"));
                        TopicItem question = new TopicItem((String) jTopic.get("question"));

                        JSONArray jAnswers = (JSONArray) jTopic.get("answers");

                        List<TopicItem> answers = new ArrayList<TopicItem>();

                        if (jAnswers != null && !jAnswers.isEmpty()) {
                            for (Object answerObject : jAnswers) {
                                TopicItem answer = new TopicItem((String) answerObject);
                                answers.add(answer);
                            }
                        }
                        
                        Topic topic = new Topic(subject, question, answers);
                        topics.add(topic);
                        
                    }

                }

            }
            
            dataSet = new DataSet(topics);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] File not found!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] IO Error!");
        } catch (ParseException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[FILE MANAGER] Inconpatible JSON file!");
        }

        return dataSet;
    }
    
    public static boolean isFileExist(String filePath){
        FileReader fr = null;
        
        try{
            fr = new FileReader(filePath);
        }catch(Exception e){
            
        }
        return fr != null;
        
    }
    
    public static DataSet loadparaphrase(String filePath)
    {
        DataSet dataSet = null;

        JSONObject jObject = new JSONObject();

        JSONParser parser = new JSONParser();

        try {
            jObject = (JSONObject)parser.parse(new FileReader(filePath));

            dataSet = new DataSet(jObject);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] File not found!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[FILE MANAGER] IO Error!");
        } catch (ParseException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[FILE MANAGER] Inconpatible JSON file!");
        }

        return dataSet;
    }
    
    public static DataSet merge(DataSet source, DataSet destination){
        destination.getTopics().addAll(source.getTopics());
        
        return destination;
    }
    
    public static DataSet removeDuplicate(DataSet dataSet){
        System.out.println("Initial Size: "+dataSet.getTopics().size());
        for(int i=0; i<dataSet.getTopics().size();i++){
            Topic topic1 = dataSet.getTopics().get(i);
            for(int j=i+1;j<dataSet.getTopics().size();j++){
                Topic topic2 = dataSet.getTopics().get(j);
                if(topic1.getSubject().getText().equals(topic2.getSubject().getText())){
                    System.out.println("\nTopic "+i+": "+topic1.getSubject().getText());
                    System.out.println("Topic "+j+": "+topic2.getSubject().getText());
                    System.out.println("Removed!!!");
                    dataSet.getTopics().remove(j);
                }
            }
        }
        System.out.println("\n\nFinal Size: "+dataSet.getTopics().size());
        return dataSet;
    }
    
    
}
