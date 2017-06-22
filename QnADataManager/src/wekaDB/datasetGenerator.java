/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekaDB;

import dataAccess.FileManager;
import dataModel.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import weka.core.*;

/**
 *
 * @author wesley
 */
public class datasetGenerator {

    public static final String YES = "Y";
    public static final String NO = "N";

    public static final int TAG = 0;
    public static final int LENGTH = 1;
    public static final int CATEGORY = 2;
    public static final int CATEGORY_INSTANCE = 3;
    public static final int RELATION = 4;

    public static Instances run() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Subs");
        labels.add("Verbo");
        labels.add("Adjetivo");
        labels.add("Artigo");
        Attribute tag = new Attribute("POS Tag", labels);
        labels = new ArrayList<>();
        labels.add("y");
        labels.add("n");
        Attribute issub = new Attribute("Is Sub", labels);
        labels = new ArrayList<>();
        labels.add("y");
        labels.add("n");
        Attribute iscategory = new Attribute("Is Category", labels);
        labels = new ArrayList<>();
        labels.add("y");
        labels.add("n");
        Attribute isrelation = new Attribute("Is Relation", labels);
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(tag);
        attributes.add(iscategory);
        attributes.add(isrelation);
        attributes.add(issub);

        Instances dataset = new Instances("DB", attributes, 0);

        dataset.setClassIndex(3);

        // Inserindo valores
        double[] values = new double[dataset.numAttributes()];
        values[0] = dataset.attribute(0).indexOfValue("Verbo");
        values[1] = dataset.attribute(1).indexOfValue("n");
        values[2] = dataset.attribute(2).indexOfValue("n");
        values[3] = dataset.attribute(3).indexOfValue("n");

        dataset.add(new DenseInstance(1.0, values));

        values = new double[dataset.numAttributes()];
        values[0] = dataset.attribute(0).indexOfValue("Subs");
        values[1] = dataset.attribute(1).indexOfValue("n");
        values[2] = dataset.attribute(2).indexOfValue("n");
        values[3] = dataset.attribute(3).indexOfValue("y");
        dataset.add(new DenseInstance(1.0, values));

        return dataset;

    }

    public static Instances basicWekaDataSet(DataSet dataset, WekaDB wekadb) {

         
        if (wekadb.index == 0) {
            wekadb.instances = dataSetHeaderGenerator();
            

            for (int topic_index = 0; topic_index < dataset.getTopics().size(); topic_index++) {
                for (int topic_item_type_index = 1; topic_item_type_index <= 3; topic_item_type_index++) {
                    if (topic_item_type_index < 3) {

                        if (topic_item_type_index == TopicItemType.SUBJECT) {
                            wekadb.sentences.addAll(getRelevantSentences(dataset.getTopics().get(topic_index).getSubject()));
                        } else {
                            wekadb.sentences.addAll(getRelevantSentences(dataset.getTopics().get(topic_index).getQuestion()));
                        }

                    } else {
                        for (int topic_item_index = 0;
                                topic_item_index < dataset.getTopics().get(topic_index).getAnswers().size();
                                topic_item_index++) {
                            wekadb.sentences.addAll(getRelevantSentences(dataset.getTopics().get(topic_index).getAnswers().get(topic_item_index)));
                        }
                    }
                }
            }
        }

        removeDuplicated(wekadb.sentences);
        rotulateSentences(wekadb);

        return wekadb.instances;

    }

    private static List<Sentence> getRelevantSentences(TopicItem ti) {
        List<Sentence> sentences = new ArrayList<>();
        for (int sentence_index = 0;
                sentence_index < ti.getSentences().size();
                sentence_index++) {

            //Search for categories
            boolean category_found = false;
            int word_index = 0;

            while (word_index < ti.getSentences().get(sentence_index)
                    .getWords().size()
                    && !category_found) {

                if (ti.getSentences().get(sentence_index)
                        .getWords().get(word_index).isContainedInACategory()) {
                    sentences.add(ti.getSentences().get(sentence_index));
                    category_found = true;
                }
                word_index++;
            }

        }
        return sentences;
    }

    private static void removeDuplicated(List<Sentence> sentences) {
        for (int i = sentences.size() - 1; i >= 0; i--) {
            for (int j = (i - 1); j >= 0; j--) {
                if (sentences.get(i).getText().equals(sentences.get(j).getText())) {
                    sentences.remove(i);
                }
            }

        }
    }

    private static void rotulateSentences(WekaDB wekadb) {

        System.out.println("There are " + (wekadb.sentences.size()-wekadb.index) + " sentences to rotulate.\n"
                + "Do it now?\n1 - YES\n2 - NO");
        Scanner s = new Scanner(System.in);

        if (s.nextInt() == 1) {
            while(wekadb.index < wekadb.sentences.size()) {//sentences.size()
                System.out.println("\n===== "+wekadb.index + "/" + wekadb.sentences.size()+"=====\n");
                wekadb.sentences.get(wekadb.index).rotulate(wekadb.instances);
                wekadb.index++;
                FileManager.save(wekadb);
            }
        }

    }

    public static Instances dataSetHeaderGenerator(List<Sentence> sentences) {
        List<String> pos_tag_values = getTags(sentences);

        List<String> boolean_values = new ArrayList<>();
        boolean_values.add(YES);
        boolean_values.add(NO);

        Attribute tag = new Attribute("Tag", pos_tag_values);
        Attribute length = new Attribute("Length");
        Attribute category_instance = new Attribute("Category", boolean_values);
        Attribute category = new Attribute("CategoryInstance", boolean_values);
        Attribute relation = new Attribute("Relation", boolean_values);
        Attribute c_tag = new Attribute("C.Tag", pos_tag_values);
        Attribute c_length = new Attribute("C.Length");
        Attribute c_category_instance = new Attribute("C.Category", boolean_values);
        Attribute c_category = new Attribute("C.CategoryInstance", boolean_values);
        Attribute c_relation = new Attribute("C.Relation", boolean_values);
        Attribute distance = new Attribute("Distance");
        Attribute sub_category = new Attribute("SubCategory", boolean_values);

        ArrayList<Attribute> attributes = new ArrayList<>();

        attributes.add(tag);
        attributes.add(length);
        attributes.add(category);
        attributes.add(category_instance);
        attributes.add(relation);
        attributes.add(c_tag);
        attributes.add(c_length);
        attributes.add(c_category);
        attributes.add(c_category_instance);
        attributes.add(c_relation);
        attributes.add(distance);
        attributes.add(sub_category);

        Instances instances = new Instances("WORDS", attributes, 0);
        instances.setClassIndex(attributes.size() - 1);
        return instances;
    }

    private static List<String> getTags(List<Sentence> sentences){
        List<String> tags = new ArrayList<>();
        
        for(Sentence sentence: sentences){
            for(Word word: sentence.getWords()){
                insert(word.getTag(), tags);
            }
        }
        
        return tags;
    }
    
    private static void insert(String tag, List<String> tags){
        boolean found = false;
        int i = 0;
        while(i<tags.size() && !found){
            if(tags.get(i).equals(tag)){
                found = true;
            }
            i++;
        }
        if(!found){
            tags.add(tag);
        }
    }
    
}
