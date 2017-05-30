/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author WesleyW
 */
public class Paraphrase {
    
    private static final String PHRASE = "text";
    private static final String PARAPHRASE = "paraphrases";
    
    
    private String phrase;
    private String paraphrase;

    public Paraphrase(String text, String paraphrases) {
        this.phrase = text;
        this.paraphrase = paraphrases;
    }

    public Paraphrase(JSONObject jParaphrases){
        phrase = (String) jParaphrases.get(PHRASE);
        paraphrase = (String) jParaphrases.get(PARAPHRASE) ;
    }
    
    
    public JSONObject toJSON(){
        JSONObject jParaphrase = new JSONObject();
        jParaphrase.put(PHRASE, phrase);
        jParaphrase.put(PARAPHRASE, paraphrase);
        
        return jParaphrase;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String text) {
        this.phrase = text;
    }

    public String getParaphrases() {
        return paraphrase;
    }

    public void setParaphrases(String paraphrases) {
        this.paraphrase = paraphrases;
    }
    
    
    
}
