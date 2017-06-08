/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekaDB;

import java.util.ArrayList;
import weka.core.*;

/**
 *
 * @author wesley
 */
public class datasetGenerator {
    
    public static Instances run(){
        ArrayList<String> labels = new ArrayList< >();
        labels.add("Subs");
        labels.add("Verbo");
        labels.add("Adjetivo");
        labels.add("Artigo");
        Attribute tag = new Attribute("POS Tag", labels) ;
        labels = new ArrayList< >();
        labels.add("y");
        labels.add("n");
        Attribute issub = new Attribute("Is Sub", labels) ;
        labels = new ArrayList< >();
        labels.add("y");
        labels.add("n");
        Attribute iscategory = new Attribute("Is Category", labels) ;
        labels = new ArrayList< >();
        labels.add("y");
        labels.add("n");
        Attribute isrelation = new Attribute("Is Relation", labels) ;
        ArrayList<Attribute> attributes = new ArrayList< >() ;
        attributes.add(tag);
        attributes.add(iscategory);
        attributes.add(isrelation);
        attributes.add(issub) ;
        
        Instances dataset = new Instances("DB", attributes, 0) ;
        
        dataset.setClassIndex(3);
        
        // Inserindo valores
        
        double[] values = new double[dataset.numAttributes()];
        values[0] = dataset.attribute(0).indexOfValue("Verbo");
        values[1] = dataset.attribute(1).indexOfValue("n");
        values[2] = dataset.attribute(2).indexOfValue("n");
        values[3] = dataset.attribute(3).indexOfValue("n");
       
        dataset.add(new DenseInstance(1.0, values));
        
        values = new double[dataset.numAttributes()];
        values[0] = dataset.attribute(0).indexOfValue("Subs");
        values[1] = dataset.attribute(1).indexOfValue("n");
        values[2] = dataset.attribute(2).indexOfValue("n");
        values[3] = dataset.attribute(3).indexOfValue("y");
        dataset.add(new DenseInstance(1.0, values));
        
        
        
        return dataset;
        
    }
}
