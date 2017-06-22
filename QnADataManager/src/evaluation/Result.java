/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

/**
 *
 * @author wesley
 */
public class Result {

    public int size;

    public String[] dt_matrix;
    public String[] j48u_matrix;
    public String[] j48_matrix;
    public String[] rf_matrix;
    public String[] mp_matrix;
    public String[] bn_matrix;
    public String[] nb_matrix;

    public double[] dt_correct;
    public double[] j48u_correct;
    public double[] j48_correct;
    public double[] rf_correct;
    public double[] mp_correct;
    public double[] bn_correct;
    public double[] nb_correct;

    public Result(int num) {
        dt_matrix = new String[num];
        j48u_matrix = new String[num];
        j48_matrix = new String[num];
        rf_matrix = new String[num];
        mp_matrix = new String[num];
        bn_matrix = new String[num];
        nb_matrix = new String[num];
        
        dt_correct = new double[num];
        j48u_correct = new double[num];
        j48_correct = new double[num];
        rf_correct = new double[num];
        mp_correct = new double[num];
        bn_correct = new double[num];
        nb_correct = new double[num];

    }
    
    private double getMean(double[] correct){
        double sum = 0;
        
        for(int i=0;i<correct.length;i++){
            sum += correct[i];
        }
        
        return sum/correct.length;
    }
    
    
    public void showResults(){
        
        System.out.println("===== Decision Tree =====");
        System.out.println("Média de acertos = "+);
    }
    
}
