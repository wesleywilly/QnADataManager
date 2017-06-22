/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekaDB;

import dataModel.Sentence;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 *
 * @author wesley
 */
public class WekaDB {
    public Instances instances;
    public List<Sentence> sentences;
    public int index;

    public WekaDB(){
        
        instances;
        sentences = new ArrayList<>();
        index = 0;
    }

    public WekaDB(Instances instances, List<Sentence> sentences, int index) {
        this.instances = instances;
        this.sentences = sentences;
        this.index = index;
    }

    

    

    
    
    
}
