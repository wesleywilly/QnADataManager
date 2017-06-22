
import dataAccess.FileManager;
import dataModel.*;
import weka.core.Instances;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wesley
 */
public class TestSelection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dataset_path = "";
        String weka_dataset_path = "";

        //Loading dataset
        if (FileManager.isFileExist(dataset_path)) {
            DataSet dataset = FileManager.load(dataset_path);
            
            for(Topic topic: dataset.getTopics()){
                for(Sentence sentence: topic.getSubject().getSentences()){
                    
                }
            }
            
        } else {
            System.out.println("[MAIN] Data set not exist in this path: " + dataset_path);
        }
        
        

    }
    
    private static Instances selection(Sentence sentence){
        
    }

}
