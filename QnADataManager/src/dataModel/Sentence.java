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
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class Sentence {

    private static final String TEXT = "text";
    private static final String WORDS = "words";
    private static final String PARAPHRASES = "paraphrases";

    private String text;
    private List<Word> words;
    private List<Paraphrase> paraphrases;

    public Sentence(String text, List<Word> words, List<Paraphrase> paraphrases) {
        this.text = text;
        this.words = words;
        this.paraphrases = paraphrases;
    }

    public Sentence(String text) {
        this.text = text;
        generateWords();
        generateParaphrases();
    }

    public Sentence(JSONObject jSentence) {

        if (jSentence.containsKey(TEXT)) {
            text = (String) jSentence.get(TEXT);
        } else {
            text = null;
        }

        if (jSentence.containsKey(WORDS)) {
            jsonToWords((JSONArray) jSentence.get(WORDS));
        } else {
            words = null;
        }

        if (jSentence.containsKey(PARAPHRASES)) {
            jsonToParaphrases((JSONArray) jSentence.get(PARAPHRASES));
        } else {
            paraphrases = null;
        }

    }

    private void jsonToWords(JSONArray jWords) {
        words = new ArrayList<Word>();

        for (Object object : jWords) {
            words.add(new Word((JSONObject) object));
        }

    }

    private void jsonToParaphrases(JSONArray jParaphrases) {
        paraphrases = new ArrayList<Paraphrase>();
        for (Object object : jParaphrases) {
            paraphrases.add(new Paraphrase((JSONObject) object));
        }

    }

    public JSONObject toJSON() {
        JSONObject jSentence = new JSONObject();

        jSentence.put(TEXT, text);
        jSentence.put(WORDS, wordsToJSON());
        jSentence.put(PARAPHRASES, paraphrasesToJSON());

        return jSentence;
    }

    private JSONArray wordsToJSON() {
        JSONArray jWords = new JSONArray();
        if (words != null && !words.isEmpty()) {
            for (Word word : words) {
                jWords.add(word.toJSON());
            }
        }
        return jWords;
    }

    private JSONArray paraphrasesToJSON() {
        JSONArray jParaphrases = new JSONArray();
        if (paraphrases != null && !paraphrases.isEmpty()) {
            for (Paraphrase paraphrase : paraphrases) {
                jParaphrases.add(paraphrase.toJSON());
            }
        }
        return jParaphrases;
    }

    public String getText() {
        return text;
    }

    public List<Word> getWords() {
        return words;
    }

    public List<Paraphrase> getParaphrases() {
        return paraphrases;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void setParaphrases(List<Paraphrase> paraphrases) {
        this.paraphrases = paraphrases;
    }

    private void generateWords(){
        words = new ArrayList<Word>();
        
        String taggedString = nlp.TextHandler.tagString(text);
        
        List<String> taggedStrings = nlp.TextHandler.separateWords(taggedString);
        
        for(String string: taggedStrings){
            int index = string.indexOf("_");
            String value = string.substring(0, index);
            String tag = string.substring(index+1, string.length());
            
            Word word = new Word(value,tag);
            
            words.add(word);
        }
        
    }
    
    private void generateParaphrases(){
        paraphrases = new ArrayList<Paraphrase>();
        
        //rodar o package
        
        for(Word word: this.words){
            String text = word.getValue();
            List<String> ps = new ArrayList<String>();
            ps.add(text+"x");
            ps.add(text+"y");
            ps.add(text+"z");
            
            Paraphrase paraphrase = new Paraphrase(text, ps);
            this.paraphrases.add(paraphrase);
        }
        int i = 0;
        String t2 = words.get(i).getValue()+" "+words.get(i+1).getValue();
        
        
        //Resultado do paraphrases package
        
        
        
        
        
    }
    
}
