/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekaDB;

/**
 *
 * @author wesley
 */
public class WordIndex {
    public int topic_index;
    public TopicItemType topic_item;
    public int topic_item_index; //Onli for Answers
    public int sentence_index;
    public int index;

    public WordIndex(int topic_index, TopicItemType topic_item, int topic_item_index, int sentence_index, int index) {
        this.topic_index = topic_index;
        this.topic_item = topic_item;
        this.topic_item_index = topic_item_index;
        this.sentence_index = sentence_index;
        this.index = index;
    }
    
    
}
