/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;

import java.util.ArrayList;
import java.util.List;
import nlp.TextHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static nlp.TextHandler.removeDuplicatedSymbols;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class TopicItem {

    private static final String TEXT = "text";
    private static final String SENTENCES = "sentences";

    private String text;
    private List<Sentence> sentences;

   /* public TopicItem(String text, List<Sentence> sentences) {
        this.text = text;
        this.sentences = sentences;
    }
*/

    public TopicItem(String text) {
        this.text = text;
    }

    public TopicItem(JSONObject jTopicItem) {
        text = (String) jTopicItem.get(TEXT);
        jsonToSentences((JSONArray) jTopicItem.get(SENTENCES));
    }

    private void jsonToSentences(JSONArray jSentences) {
        sentences = new ArrayList<Sentence>();

        if (!jSentences.isEmpty()) {
            for (Object object : jSentences) {
                sentences.add(new Sentence((JSONObject) object));
            }
        }
    }

    public JSONObject toJSON() {
        JSONObject jTopicItem = new JSONObject();

        jTopicItem.put(TEXT, text);
        jTopicItem.put(SENTENCES, sentencesToJSON());

        return jTopicItem;
    }

    private JSONArray sentencesToJSON() {
        JSONArray jsentences = new JSONArray();

        if (sentences != null && !sentences.isEmpty()) {
            for (Sentence sentence : sentences) {
                jsentences.add(sentence.toJSON());
            }
        }
        return jsentences;
    }

    public String getText() {
        return text;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void generateSentences() {
        sentences = new ArrayList<Sentence>();
        text = TextHandler.removeDuplicatedSymbols(text);
        List<String> strings = TextHandler.separateBySeparators(text);
        List<Sentence> sentences = new ArrayList<Sentence>();

        for (String string : strings) {
            sentences.add(new Sentence(string));
        }

        this.sentences = sentences;

    }

}
