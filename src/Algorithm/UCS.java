package src.Algorithm;

import java.util.*;
import src.Util.DictionaryLib;
public class UCS {
    public List<String> process(String startWord, String endWord, DictionaryLib dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Map<String, Integer> costMap = new HashMap<>();
        Set<String> visitedWords = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        priorityQueue.offer(new Node(startWord, 0));
        costMap.put(startWord, 0);

        while (!priorityQueue.isEmpty()) {
            Node currNode = priorityQueue.poll();
            String currWord = currNode.getWord();
            int currCost = currNode.getCost();

            
            visitedWords.add(currWord);
            
            List<String> nextWords = generateValidWords(currWord, endWord, dictionary);
            for (String nextWord : nextWords) {
                int newCost = currCost + calculateCost(nextWord, endWord);
                
                if (!visitedWords.contains(nextWord) && (!costMap.containsKey(nextWord) || newCost < costMap.get(nextWord))) {
                    costMap.put(nextWord, newCost);
                    parentMap.put(nextWord, currWord);
                    priorityQueue.offer(new Node(nextWord, newCost));
                    if (nextWord.equals(endWord)) {
                        return reconstructPath(parentMap, startWord, endWord);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private List<String> generateValidWords(String word, String endWord, DictionaryLib dictionary) {
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

    private List<String> reconstructPath(Map<String, String> parentMap, String startWord, String endWord) {
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
    
    private int calculateCost(String word1, String word2) {
        if (word1.length() != word2.length()) {
            throw new IllegalArgumentException("Words must have the same length");
        }

        int cost = 0;
        for (int i = 0; i < word1.length(); i++) {
            char char1 = word1.charAt(i);
            char char2 = word2.charAt(i);

            // Calculate absolute difference in ASCII values
            int charDiff = Math.abs((int) char1 - (int) char2);

            // Accumulate cost based on character difference
            cost += charDiff;
        }

        return cost;
    }
    private static class Node {
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
}