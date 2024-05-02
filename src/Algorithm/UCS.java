package src.Algorithm;

import java.util.*;
import src.Util.DictionaryLib;

public class UCS extends Algorithm{
    public List<String> process(String startWord, String endWord, List<String> dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node -> Node.getCost()));
        ArrayList<String> visitedWords = new ArrayList<>();

        priorityQueue.add(new Node(startWord, 0, null));

        while (!priorityQueue.isEmpty()) {
            Node currNode = priorityQueue.poll();
            String currWord = currNode.getWord();
            int currCost = currNode.getCost();
            List<String> nextWords = getValidWords(currWord, dictionary);
            for (String nextWord : nextWords) {
                int newCost = currCost + 1;
                if(visitedWords.contains(nextWord)){
                    continue;
                }
                else{
                    Node next = new Node(nextWord, newCost, currNode);
                    priorityQueue.add(next);
                    if(nextWord.equals(endWord)){
                        return next.reconstructPath();
                    }
                }
            }
        }

        return null;
    }
    public int calculateCost(String word1, String word2) {
        if (word1.length() != word2.length()) {
            throw new IllegalArgumentException("Words must have the same length");
        }
        int cost = 0;
        for (int i = 0; i < word1.length(); i++) {
            char char1 = word1.charAt(i);
            char char2 = word2.charAt(i);
            int charDiff = Math.abs((int) char1 - (int) char2);
            cost += charDiff;
        }
    
        return cost;
    }
}