/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;
import dataModel.DataSet;
import dataAccess.FileManager;
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

    private String text;
    private List<Word> words;
    
    public Sentence(String text, List<Word> words) {
        this.text = text;
        this.words = words;
    }

    public Sentence(String text) {
        this.text = text;
        generateWords();
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

    }

    private void jsonToWords(JSONArray jWords) {
        words = new ArrayList<Word>();

        for (Object object : jWords) {
            words.add(new Word((JSONObject) object));
        }

    }

    public JSONObject toJSON() {
        JSONObject jSentence = new JSONObject();

        jSentence.put(TEXT, text);
        jSentence.put(WORDS, wordsToJSON());

        return jSentence;
    }

    private JSONArray wordsToJSON() {
        JSONArray jWords = new JSONArray();
        if (words != null && !words.isEmpty()) {
            for (Word word : words) {
                if(!word.isStopword())
                    jWords.add(word.toJSON());
            }
        }
        return jWords;
    }


    public String getText() {
        return text;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWords(List<Word> words) {
        this.words = words;
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
    
}
