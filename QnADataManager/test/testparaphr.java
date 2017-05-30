/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dataModel.Paraphrase;
import dataModel.Sentence;
import dataAccess.ParaphraseManager ;
import java.util.List;

/**
 *
 * @author WesleyW
 */
public class testparaphr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Paraphrase> paraphrasesSet =  ParaphraseManager.ImportPack() ;
        Sentence S = new Sentence("actor", paraphrasesSet)  ; 
        System.out.println(S.toJSON());
        
        
    }
    
}
