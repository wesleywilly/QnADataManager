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
    
    private static final String TEXT = "text";
    private static final String PARAPHRASES = "paraphrases";
    
    
    private String text;
    private List<String> paraphrases;

    public Paraphrase(String text, List<String> paraphrases) {
        this.text = text;
        this.paraphrases = paraphrases;
    }

    public Paraphrase(JSONObject jParaphrases){
        text = (String) jParaphrases.get(TEXT);
        jsonToParaphrases((JSONArray) jParaphrases.get(PARAPHRASES));
    }
    
    private void jsonToParaphrases(JSONArray jParaphrases){
        paraphrases = new ArrayList<String>();
        for(Object object: jParaphrases){
            paraphrases.add((String) object);
        }
    }
    
    public JSONObject toJSON(){
        JSONObject jParaphrase = new JSONObject();
        jParaphrase.put(TEXT, text);
        jParaphrase.put(PARAPHRASES, paraphrasesToJSON());
        
        return jParaphrase;
    }
    
    private JSONArray paraphrasesToJSON(){
        JSONArray jParaphrases = new JSONArray();
        for(String string: paraphrases){
            jParaphrases.add(string);
        }
        return jParaphrases;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getParaphrases() {
        return paraphrases;
    }

    public void setParaphrases(List<String> paraphrases) {
        this.paraphrases = paraphrases;
    }
    
    
    
}
