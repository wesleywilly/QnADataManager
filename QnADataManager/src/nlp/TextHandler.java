/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp;

import dataModel.Sentence;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author @wesleywilly [https://github.com/wesleywilly]
 */
public class TextHandler {

    private static final String[] SYMBOLS = {" ",".","!","?","\n","\r","\t","-",","};
    private static final String[] SEPARATORS = {"\n","\r", ";", ".","?","!",":"};

    public static String removeSymbolsFromBegin(String text){
        boolean end = false;
        
        while(!end){
            boolean match = false;
            for(int i=0; i<SYMBOLS.length && !match; i++){
                if(text.startsWith(SYMBOLS[i])){
                    text = text.substring(1);
                    match = true;
                }
            }
            if(!match){
                end = true;
            }
        }
        return text;
        
    }
    
    public static String removeDuplicatedSymbols(String text){
        String final_text = text;
        for(String symbol: SYMBOLS){
            final_text = removeDuplicate(final_text, symbol);
        }
        return final_text;
    }
    
    private static String removeDuplicate(String text,String symbol) {

        int i1 = 0;

        boolean end = false;

        while (!end) {
            int i2 = text.indexOf(symbol, i1 + 1);

            if (i2 - 1 == i1) {
                text = text.substring(0, i1) + text.substring(i2, text.length());
            } else {

                if (i2 > 0 && i2 < text.length()) {
                    int i3 = text.indexOf(symbol, i2 + 1);
                    if (i3 - 1 == i2) {
                        text = text.substring(0, i2) + text.substring(i3, text.length());
                    }
                    i1 = i2;
                } else {
                    end = true;
                }
            }
        }

        return text;
    }

    private static List<String> separate(String text, int separator_index) {
        List<String> sentences = new ArrayList<String>();

        if (separator_index == 0) {
            if (text.length() > 0) {
                boolean end = false;
                while (!end) {
                    int index = text.indexOf(SEPARATORS[separator_index], 1);
                    if (index > 0 && index < text.length()) {
                        sentences.add(removeSymbolsFromBegin(text.substring(0, index)));
                        text = text.substring(index + 1, text.length());
                    } else {
                        if(text.length()>0){
                            sentences.add(text);
                        }
                        end = true;
                    }
                }

            } else {
                sentences.add(text);
            }
        } else {
            List<String> aux_sentences = separate(text, separator_index - 1);

            for (String aux_text : aux_sentences) {
                if (aux_text.length() > 0) {
                    boolean end = false;
                    while (!end) {
                        int index = aux_text.indexOf(SEPARATORS[separator_index], 1);
                        if (index > 0 && index < aux_text.length()) {
                            sentences.add(removeSymbolsFromBegin(aux_text.substring(0, index)));
                            aux_text = aux_text.substring(index + 1, aux_text.length());
                        } else {
                            if(aux_text.length()>0){
                                sentences.add(aux_text);
                            }
                            end = true;
                        }
                    }

                } else {
                    sentences.add(text);
                }
            }

        }
        return sentences;
    }

    public static List<String> separateBySeparators(String text){
        return separate(text, SEPARATORS.length-1);
    }

    public static List<String> separateWords(String text){
        List<String> words = new ArrayList<String>();
        
        boolean end = false;
        
        int i1 = 0;
        
        while(!end){
            int i2 = text.indexOf(" ", i1+1);
            if(i2>0 && i2<text.length()){
                words.add(text.substring(i1, i2));
                i1=i2+1;
            }else{
                end = true;
                if(i1<text.length()){
                    words.add(text.substring(i1));
                }
            }
            
        }
        
        return words;
        
    }
    
    public static String tagString(String text){
        String taggers_url = "POSTagger/stanford-postagger-full-2015-12-09/models/";
        String trained_tagger = "english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggers_url+trained_tagger);
        
        String taggedString = tagger.tagString(text.toLowerCase());
        
        return taggedString;
    }
    
    

}
