/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import dataAccess.FileManager;
import dataModel.DataSet;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class OldDataLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSet ds = FileManager.loadOld("ya-la2.json");
        FileManager.save(ds, "ya-la3.json");
        
        DataSet ds2 = FileManager.load("ya-la3.json");
        
        
    }

}
