/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wesley
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                help();
            } else if (args[0].equals("-pp")) {
                if (args.length == 3) {
                    preprocess(args[1], args[2]);
                } else {
                    preprocessHelp();
                }
            } else if (args[0].equals("-s")) {
                if (args.length == 3) {
                    select(args[1], args[2]);
                } else {
                    selectHelp();
                }
            }
        }else{
            help();
        }
    }
    
    /**
     * Help for command line
     */
    private static void help() {
        System.out.println("HELP!");
    }
    
    /**
     * Help for preprocess method
     */
    private static void preprocessHelp() {
        System.out.println("PREPROCESS HELP!");
    }

    /**
     * Help for select method
     */
    private static void selectHelp() {
        System.out.println("SELECT HELP!");
    }

    /**
     * Preprocess method
     * 
     * This method gets a question and answers raw data and perform the preprocess
     *
     * Preprocess:
     * - Tokenization;
     * - POS Tagging
     * - Stop-words filtering
     * - Semantic analysis
     *
     * After the preprocess a new JSON file is saved in preprocess_path
     * 
     * @param raw_path
     * @param preprocessed_path 
     */
    private static void preprocess(String raw_path, String preprocessed_path) {
        System.out.println("PREPROCESS");
    }
    
    /**
     * Selection method
     * 
     * this method gets a preprocessed data (from a file generated in preprocess
     * method), select and prepare the the dataset for data mining process.
     * 
     * Instances: words
     * 
     * Features:
     * - value
     * - tag
     * - length
     * - is contained in a category
     * - is contained in a category instance
     * - is contained in a relation
     * - (same informations from neighbor words)
     * 
     * After the select process an Arff file is saved in arff_path.
     * 
     * @param preprocessed_path
     * @param arff_path 
     */
    private static void select(String preprocessed_path, String arff_path) {
        System.out.println("SELECT!");
    }

}
