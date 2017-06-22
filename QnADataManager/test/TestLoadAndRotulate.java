
import dataAccess.FileManager;
import dataModel.*;
import weka.core.Instances;
import wekaDB.datasetGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wesley
 */
public class TestLoadAndRotulate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dataset_path = "dataset.json";
        String wekadb_path = "words_final.arff";
        
        //Load
        DataSet dataset = FileManager.load(dataset_path);
        //Rotulate
        wekaDB.WekaDB wekadb = FileManager.loadWekaDB();
        
        Instances wekaDataset = datasetGenerator.basicWekaDataSet(dataset,wekadb);
        //test
        FileManager.save(wekaDataset, wekadb_path);
    }
    
}
