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
public class DataSet {
    private static final String TOPICS = "topics";
    
    private List<Topic> topics;

    public DataSet(List<Topic> topics) {
        this.topics = topics;
    }

    public DataSet(JSONObject jDataSet) {
        jsonToTopics((JSONArray) jDataSet.get(TOPICS));
    }
    
    private void jsonToTopics(JSONArray jTopics){
        topics = new ArrayList<Topic>();
        
        if(jTopics != null && !jTopics.isEmpty()){
            for(Object object: jTopics){
                topics.add(new Topic((JSONObject) object));
            }
        }
        
    }
    
    public JSONObject toJSON(){
        JSONObject jDataSet = new JSONObject();
        
        jDataSet.put(TOPICS, topicsToJSON());
        
        return jDataSet;
    }
    
    private JSONArray topicsToJSON(){
        JSONArray jTopics = new JSONArray();
        
        if(topics != null && !topics.isEmpty()){
            for(Topic topic: topics){
                jTopics.add(topic.toJSON());
            }
        }
        return jTopics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    
    
    
    
    
    
}
