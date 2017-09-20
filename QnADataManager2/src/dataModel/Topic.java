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
public class Topic {
    private static final String SUBJECT = "subject";
    private static final String QUESTION = "question";
    private static final String ANSWERS = "answers";
    
    private TopicItem subject;
    private TopicItem question;
    private List<TopicItem> answers;

    public Topic(TopicItem subject, TopicItem question, List<TopicItem> answers) {
        this.subject = subject;
        this.question = question;
        this.answers = answers;
         
    }
    
    public Topic(JSONObject jTopic){
        subject = new TopicItem((JSONObject) jTopic.get(SUBJECT));
        question = new TopicItem((JSONObject) jTopic.get(QUESTION));
        jsonToAnswers((JSONArray) jTopic.get(ANSWERS));
    }
    
    private void jsonToAnswers(JSONArray jAnswers){
        answers = new ArrayList<TopicItem>();
        
        if(!jAnswers.isEmpty() && jAnswers != null){
            for(Object object: jAnswers){
                answers.add(new TopicItem((JSONObject) object));
            }
        }
    }
    
    public JSONObject toJSON(){
        JSONObject jTopic = new JSONObject();
        
        jTopic.put(SUBJECT, subject.toJSON());
        jTopic.put(QUESTION, question.toJSON());
        jTopic.put(ANSWERS, answersToJSON());
        
        return jTopic;
    }
    
    private JSONArray answersToJSON(){
        JSONArray jAnswers = new JSONArray();
        
        if(answers != null && !answers.isEmpty()){
            for(TopicItem answer: answers){
                jAnswers.add(answer.toJSON());
            }
        }
        return jAnswers;
    }

    public TopicItem getSubject() {
        return subject;
    }

    public TopicItem getQuestion() {
        return question;
    }

    public List<TopicItem> getAnswers() {
        return answers;
    }

    public void setSubject(TopicItem subject) {
        this.subject = subject;
    }

    public void setQuestion(TopicItem question) {
        this.question = question;
    }

    public void setAnswers(List<TopicItem> answers) {
        this.answers = answers;
    }
    
    
}
