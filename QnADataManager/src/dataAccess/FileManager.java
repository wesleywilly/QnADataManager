/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import dataModel.DataSet;
import dataModel.Paraphrase;
import dataModel.Sentence;
import dataModel.Topic;
import dataModel.TopicItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ParserException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import wekaDB.WekaDB;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class FileManager {
    
    private static final String TAGS = "POSTagger/PennTreebankIIConstituentTags.txt";
    
    private static final String SENTENCES = "sentences";
    
    private static final String INDEX_PATH = "index.txt";
    private static final String SENTENCES_PATH = "sentences.json";
    private static final String WEKADB_PATH = "words.arff";
    
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
                    
                    List<Paraphrase> paraphrasesSet = ParaphraseManager.ImportPack();
                    
                    for (Object object : jTopics) {
                        JSONObject jTopic = (JSONObject) object;
                        
                        TopicItem subject = new TopicItem((String) jTopic.get("subject"), paraphrasesSet);
                        TopicItem question = new TopicItem((String) jTopic.get("question"), paraphrasesSet);
                        
                        JSONArray jAnswers = (JSONArray) jTopic.get("answers");
                        
                        List<TopicItem> answers = new ArrayList<TopicItem>();
                        
                        if (jAnswers != null && !jAnswers.isEmpty()) {
                            for (Object answerObject : jAnswers) {
                                TopicItem answer = new TopicItem((String) answerObject, paraphrasesSet);
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
    
    public static boolean isFileExist(String filePath) {
        FileReader fr = null;
        
        try {
            fr = new FileReader(filePath);
        } catch (Exception e) {
            
        }
        return fr != null;
        
    }
    
    public static DataSet loadparaphrase(String filePath) {
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
    
    public static List<String> loadPOSTags() {
        List<String> postags = new ArrayList<>();
        if (isFileExist(TAGS)) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(TAGS));
                String line = br.readLine();
                while (line != null) {
                    postags.add(line);
                    line = br.readLine();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("[File Manager] POS Tags List not exist!");
        }
        
        return postags;
    }
    
    public static void save(Instances dataSet, String file_path) {
        try {
            Files.delete(new File(file_path).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataSet);
        try {
            saver.setFile(new File(file_path));
            //saver.setDestination(new File("./data/test.arff"));   // **not** necessary in 3.5.4 and later
            saver.writeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void save(WekaDB wekadb) {
        //Index
        FileWriter writeFile = null;
        try {
            writeFile = new FileWriter(INDEX_PATH);
            
            writeFile.write(String.valueOf(wekadb.index));
            writeFile.close();
        } catch (IOException e) {
            System.out.println("[FILE MANAGER] Error while saving file:");
            e.printStackTrace();
        }

        //Sentences
        JSONArray sentences = new JSONArray();
        
        for (Sentence sentence : wekadb.sentences) {
            sentences.add(sentence.toJSON());
        }
        
        JSONObject object = new JSONObject();
        object.put(SENTENCES, sentences);
        
        writeFile = null;
        
        try {
            writeFile = new FileWriter(SENTENCES_PATH);

            //Escreve no arquivo conteudo do Objeto JSON 
            writeFile.write(object.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            System.out.println("[FILE MANAGER] Error while saving file:");
            e.printStackTrace();
        }

        //WEKA
        save(wekadb.instances, WEKADB_PATH);
        
    }
    
    public static WekaDB loadWekaDB() {
        
        WekaDB wekadb = new WekaDB();
        
        if (isFileExist(INDEX_PATH) && isFileExist(SENTENCES_PATH) && isFileExist(WEKADB_PATH)) {
            BufferedReader br;
            //INDEX
            try {
                br = new BufferedReader(new FileReader(INDEX_PATH));
                String line = br.readLine();
                wekadb.index = Integer.parseInt(line);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Sentences
            JSONObject jObject = new JSONObject();
            
            JSONParser parser = new JSONParser();
            try {
                jObject = (JSONObject) parser.parse(new FileReader(SENTENCES_PATH));
                
                JSONArray jsentences = (JSONArray) jObject.get(SENTENCES);
                for (Object object : jsentences) {
                    wekadb.sentences.add(new Sentence((JSONObject) object));
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            //WEKADB
            try {
                wekadb.instances = DataSource.read(WEKADB_PATH);
                if (wekadb.instances.classIndex() == -1) {
                    wekadb.instances.setClassIndex(wekadb.instances.numAttributes() - 1);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return wekadb;
    }
}
