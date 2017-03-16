/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qeadatamanager;

import dataModel.Paraphrase;
import dataModel.Sentence;

/**
 *
 * @author WesleyW
 */
public class testparaphr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sentence s = new Sentence("Overall most rappers makes bad actor");
        
        System.out.println(s.toJSON());
    }
    
}
