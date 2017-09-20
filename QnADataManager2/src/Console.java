/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wesley
 */
public class Console {

    private static final String INSERT = "-insert";
    private static final String CONVERT = "-convert";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case INSERT:
                    insert(args[1], args[2]);
                    break;
                case CONVERT:
                    convert(args[1], args[2]);
                    break;
                default:
                    help();
            }
        } else {
            help();
        }
    }

    private static void help() {
        System.out.println("\n===== HELP =====");
        System.out.println("\n -insert [raw path] [database path]");
        System.out.println("\n -convert [database path] [arff path]\n");
    }

    private static void insert(String rawPath, String dataBasePath) {
        System.out.println("\n===== INSERT =====");
        System.out.println("RAW: " + rawPath);
        System.out.println("DATABASE: " + dataBasePath);

        System.out.println("INSERTING...");

        System.out.println("\nDone!");
    }

    private static void convert(String databasePath, String arffPath) {

    }

}
