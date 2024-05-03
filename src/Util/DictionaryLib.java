package src.Util;

import java.util.*;

public class DictionaryLib{
    private List<String> dictLib;
    public DictionaryLib(String path){
       this.dictLib = ReadFile.readFile(path);
    }

    public Boolean isInDict(String word){
        return dictLib.contains(word.toLowerCase());
    }

    public List<String> filterDict(String word1, String word2){
        if(word1.length() != word2.length()){
            return null;
        }

        List<String> ans = new ArrayList<>();
        for(var dictWord : dictLib){
            if(dictWord.length() == word1.length()){
                ans.add(dictWord);
            }
        }
        return ans;
    }

    public List<String> getDict(){
        return this.dictLib;
    }
    public void printDic() {
        System.out.print("[");
        int size = dictLib.size();
        int count = 0;
        
        for (var word : dictLib) {
            System.out.print(word);
            count++;
            
            if (count < size) {
                System.out.print(",");
            }
        }
        
        System.out.println("]");
    }

}