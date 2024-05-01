package src.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import src.Util.DictionaryLib;


public class Algorithm {
    public static class Node {
        private String word;
        private int cost;

        public Node(String word, int cost) {
            this.word = word;
            this.cost = cost;
        }

        public String getWord() {
            return this.word;
        }

        public int getCost() {
            return this.cost;
        }
    }

    public List<String> generateValidWords(String word, String startWord, String endWord, DictionaryLib dictionary) {
        List<String> validWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char originalChar = word.charAt(i);
            char endChar = endWord.charAt(i); 
            if(endChar != originalChar){
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != originalChar) {
                        StringBuilder modifiedWordBuilder = new StringBuilder(word);
                        modifiedWordBuilder.setCharAt(i, c);
                        String modifiedWord = modifiedWordBuilder.toString();
                        if (dictionary.isInDict(modifiedWord)) {
                            validWords.add(modifiedWord);
                        }
                    }
                }            
            }
        }
        return validWords;
    }

    public List<String> reconstructPath(Map<String, String> parentMap, String startWord, String endWord) {
        List<String> path = new ArrayList<>();
        String current = endWord;

        while (!current.equals(startWord)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(startWord);
        Collections.reverse(path);
        return path;
    }

}
