/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import dataAccess.ParaphraseManager;
import dataModel.Paraphrase;
import dataModel.Sentence;
import java.util.List;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class testWord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Paraphrase> paraphrasesSet =  ParaphraseManager.ImportPack() ;
        Sentence s = new Sentence("I need a list of horror movies.", paraphrasesSet);
        System.out.println(s.toJSON());
    }

}
