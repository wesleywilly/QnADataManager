/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;

import WebCrawlers.Zenodotus;
import nlp.StopWords;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Represents a word from a sentence.
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class Word {

    private static final String VALUE = "value";
    private static final String TAG = "tag";
    private static final String LENGTH = "length";

    private static final String CONTAINED_IN_A_CATEGORY = "contained_in_a_category";
    private static final String CONTAINED_IN_A_CATEGORY_INSTANCE = "contained_in_a_category_instance";
    private static final String CONTAINED_IN_A_RELATION = "contained_in_a_relation";
    
    private static final String CATEGORIES = "categories";
    private static final String CATEGORY_INSTANCES = "category_instances";
    private static final String RELATIONS = "relations";


    private String value;
    private String tag;
    private long length;
    private boolean stopword;

    private boolean containedInACategory;
    private boolean containedInACategoryInstance;
    private boolean containedInARelation;

    private List<String> categories;
    private List<String> categoryInstances;
    private List<String> relations;

    /**
     * Builds the object.
     *
     * @param value = the word itself.
     * @param tag = Stores tag generated by the stanford parser.
     */
    public Word(String value, String tag) {
        this.value = value;
        this.tag = tag;
        this.length = value.length();
        semanticAnalysis();
    }

    /**
     * Builds the object. StanfordTag = null;
     *
     * @param value = the word itself.
     */
    public Word(String value) {
        this.value = value;
        this.tag = "";
        this.length = value.length();
        semanticAnalysis();
    }

    /**
     * Constructs the object from a JSONObject. The JSONObject with same data
     * structure can be generated by toJSON method in this class.
     *
     * @param jWord = JSONObject with a similar data structure
     */
    public Word(JSONObject jWord) {

        if (jWord.containsKey(VALUE)) {
            value = (String) jWord.get(VALUE);
        } else {
            value = null;
        }

        if (jWord.containsKey(TAG)) {
            tag = (String) jWord.get(TAG);
        } else {
            tag = null;
        }
    }

    /**
     * Returns a JSONObject with a similar data structure of this Object
     *
     * @return JSONObject
     */
    public JSONObject toJSON() {
        if(!stopword){
        JSONObject jWord = new JSONObject();
        if (!value.isEmpty() && value != null) {
            jWord.put(VALUE, value);
            jWord.put(LENGTH, length);
            if (!tag.isEmpty() && tag != null) {
                jWord.put(TAG, tag);
            }
            jWord.put(CONTAINED_IN_A_CATEGORY, isContainedInACategory());
            if (isContainedInACategory()) {
                jWord.put(CATEGORIES, stringListToJSONArray(categories));
            }
            jWord.put(CONTAINED_IN_A_CATEGORY_INSTANCE, isContainedInACategoryInstance());
            if(isContainedInACategoryInstance()){
                jWord.put(CATEGORY_INSTANCES, stringListToJSONArray(categoryInstances));
            }

            jWord.put(CONTAINED_IN_A_RELATION, isContainedInARelation());
            if(isContainedInARelation()){
                jWord.put(RELATIONS, stringListToJSONArray(relations));
            }
            
        }
        return jWord;
        }else{
            return null;
        }
    }

    public boolean isStopword() {
        return stopword;
    }

    
    /**
     * Returns the word value
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the tag generated by the stanford parser for this word.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag generated by the stanford parser for this word.
     *
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    private JSONArray stringListToJSONArray(List<String> stringList) {
        JSONArray jList = new JSONArray();

        for (String string : stringList) {
            jList.add(string);
        }

        return jList;
    }

    private List<String> jSONArrayToStringList(JSONArray jList) {
        List<String> stringList = new ArrayList<>();

        for (Object object : jList) {
            stringList.add((String) object);
        }

        return stringList;
    }

    public long getLength() {
        return length;
    }

    public boolean isContainedInACategory() {
        return containedInACategory;
    }

    public boolean isContainedInACategoryInstance() {
        return containedInACategoryInstance;
    }

    public boolean isContainedInARelation() {
        return containedInARelation;
    }


    public List<String> getCategories() {
        return categories;
    }

    public List<String> getCategoryInstances() {
        return categoryInstances;
    }

    public List<String> getRelations() {
        return relations;
    }


    private void semanticAnalysis() {
        //Stop-words
        if (!StopWords.contains(value)) {

            //Category
            containedInACategory = false;
            categories = new ArrayList<>();
            try {
                JSONArray categoryResult = Zenodotus.searchCategory(value);
                if (categoryResult != null && categoryResult.size() > 0) {
                    for (Object object : categoryResult) {
                        JSONObject jObject = (JSONObject) object;
                        categories.add((String) jObject.get("category_name"));
                    }
                    containedInACategory = true;
                    categoryResult = null;
                }

            } catch (Exception e) {
                System.out.println("[WORD] Error: While acessing NELL KB.");
                e.printStackTrace();
            }
            
            
            //Category Instance
            containedInACategoryInstance = false;
            categoryInstances = new ArrayList<>();
            try {
                JSONArray categoryInstanceResult = Zenodotus.searchCategoryInstance(value);
                if (categoryInstanceResult != null && categoryInstanceResult.size() > 0) {
                    for (Object object : categoryInstanceResult) {
                        JSONObject jObject = (JSONObject) object;
                        String instance_name = (String) jObject.get("instance_name");
                        categoryInstances.add(instance_name);
                        
                        
                    }
                    containedInACategoryInstance = true;
                    categoryInstanceResult = null;
                }

            } catch (Exception e) {
                System.out.println("[WORD] Error: While acessing NELL KB.");
                e.printStackTrace();
            }
            
            //Relation
            containedInARelation = false;
            relations = new ArrayList<>();
            try {
                JSONArray relationsResult = Zenodotus.searchRelation(value);
                if (relationsResult != null && relationsResult.size() > 0) {
                    for (Object object : relationsResult) {
                        JSONObject jObject = (JSONObject) object;
                        relations.add((String) jObject.get("relation_name"));
                    }
                    containedInARelation = true;
                    relationsResult = null;
                }

            } catch (Exception e) {
                System.out.println("[WORD] Error: While acessing NELL KB.");
                e.printStackTrace();
            }
        
        }else{
            stopword = true;
        }
    }


}
