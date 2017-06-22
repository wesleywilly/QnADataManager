/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekaDB;

import dataAccess.FileManager;
import evaluation.Result;
import java.util.Random;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 *
 * @author wesley
 */
public class TestClassifiers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DecisionTable dt = new DecisionTable();
        String[] dt_options = new String[1];
        dt_options[0] = "";

        J48 j48u = new J48();
        String[] j48u_options = new String[1];
        j48u_options[0] = "-U";
        //j48u_options[1] = "-Q " + System.currentTimeMillis();

        J48 j48 = new J48();
        String[] j48_options = new String[1];
        j48_options[0] = "-R";
        //j48u_options[1] = "-Q " + System.currentTimeMillis();

        RandomForest rf = new RandomForest();
        String[] rf_options = new String[1];
        rf_options[0] = "";

        MultilayerPerceptron mp = new MultilayerPerceptron();
        String[] mp_options = new String[1];
        //mp_options[0] = "-S " + System.currentTimeMillis();
        mp_options[0] = "-B";

        BayesNet bn = new BayesNet();
        String[] bn_options = new String[1];
        bn_options[0] = "";

        NaiveBayes nb = new NaiveBayes();
        String[] nb_options = new String[1];
        //nb_options[0] = "-K";
        nb_options[0] = "-D";

        try {
            dt.setOptions(dt_options);
            j48u.setOptions(j48u_options);
            j48.setOptions(j48_options);
            rf.setOptions(rf_options);
            mp.setOptions(mp_options);
            bn.setOptions(bn_options);
            nb.setOptions(nb_options);

            Instances instances = FileManager.loadWekaDB().instances;

            Evaluation dt_e;
            Evaluation j48u_e;
            Evaluation j48_e;
            Evaluation rf_e;
            Evaluation mp_e;
            Evaluation bn_e;
            Evaluation nb_e;

            
            Result r = new Result(35);
            r.size = instances.numInstances();
            
            for (int i = 0; i < 35; i++) {
                dt_e = new Evaluation(instances);
                j48u_e = new Evaluation(instances);
                j48_e = new Evaluation(instances);
                rf_e = new Evaluation(instances);
                mp_e = new Evaluation(instances);
                bn_e = new Evaluation(instances);
                nb_e = new Evaluation(instances);
                
                dt_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                j48u_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                j48_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                rf_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                mp_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                bn_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                nb_e.crossValidateModel(dt, instances, 10, new Random(System.currentTimeMillis()));
                
                r.dt_matrix[i] = dt_e.toMatrixString();
                r.dt_correct[i] = dt_e.correct()/r.size;
                
                r.j48u_matrix[i] = j48u_e.toMatrixString();
                r.j48u_correct[i] = j48u_e.correct()/r.size;
                
                r.j48_matrix[i] = j48_e.toMatrixString();
                r.j48_correct[i] = j48_e.correct()/r.size;
                
                r.rf_matrix[i] = rf_e.toMatrixString();
                r.rf_correct[i] = rf_e.correct()/r.size;
                
                r.mp_matrix[i] = mp_e.toMatrixString();
                r.mp_correct[i] = mp_e.correct()/r.size;
                
                r.bn_matrix[i] = bn_e.toMatrixString();
                r.bn_correct[i] = bn_e.correct()/r.size;
                
                r.nb_matrix[i] = nb_e.toMatrixString();
                r.nb_correct[i] = nb_e.correct()/r.size;
               
            }
            
           r.showResults();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
