
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wesley
 */
public class testclassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Instances data = wekaDB.datasetGenerator.run();
        
        String[] options = new String[1];
        options[0] = "-U";

        J48 classifier = new J48();
        
        try {
            Evaluation eval = new Evaluation(data);
            
            classifier.setOptions(options);
            
            eval.crossValidateModel(classifier, data, 2, new Random());
            
            System.out.println(eval.toSummaryString("\nResults\n\n", false));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
