package src.Algorithm;

import java.util.ArrayList;
import java.util.List;


public class Algorithm {

    // this shit is brute force as fuck
    public List<String> generateValidWords(String word, String endWord, List<String> dictionary) {
        List<String> validWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char originalChar = word.charAt(i);
            char endChar = endWord.charAt(i); 
            if(endChar != originalChar){
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] modifiedWord = word.toCharArray();
                    modifiedWord[i] = c;
                    String modifiedWordStr = String.valueOf(modifiedWord);
                    if (dictionary.contains(modifiedWordStr)) {
                        validWords.add(modifiedWordStr);
                    }
                }            
            }
        }
        return validWords;
    }

    public List<String> getValidWords(String currWord, List<String> dictionaryLib){
        List<String> validWords = new ArrayList<>(); 
        for(String word : dictionaryLib){
            if(!word.equals(currWord) && isOneChar(word, currWord)){
                validWords.add(word);
            }
        }
        return validWords;
    }

    public boolean isOneChar(String s1, String s2){
        int diff = 0; 
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
                if(diff > 1){
                    return false;
                }
            }
        }
        return true;
    }

}