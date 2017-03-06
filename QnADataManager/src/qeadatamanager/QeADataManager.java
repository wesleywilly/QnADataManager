/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qeadatamanager;

import dataModel.DataSet;
import dataModel.Topic;
import dataModel.TopicItem;
import java.util.ArrayList;
import java.util.List;
import nlp.TextHandler;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class QeADataManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TopicItem subject = new TopicItem("Esse texto representa um assunto?");
        TopicItem question = new TopicItem("Este texto representa a pergunta completa. The quick brown fox jumps over the lazy dog.");
        
        List<TopicItem> answers = new ArrayList<TopicItem>();
        answers.add(new TopicItem("Esse texto é um exemplo de resposta."));
        answers.add(new TopicItem("Os exemplos são:\nExemplo 1;\nExemplo 2;\ne Exemplo 3."));
        answers.add(new TopicItem("Esse texto é um outro exemplo de resposta."));
        
        Topic topic = new Topic(subject, question, answers);
        
        List<Topic> topics = new ArrayList<Topic>();
        topics.add(topic);
        
        DataSet dataSet = new DataSet(topics);
        
        System.out.println(dataSet.toJSON());
        
        
    }

}
