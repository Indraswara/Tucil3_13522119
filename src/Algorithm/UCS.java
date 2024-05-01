package src.Algorithm;

import java.util.*;

public class UCS {
    public List<String> Process(String startWord, String endWord, List<String> Dictionary){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost)); 
        priorityQueue.offer(new Node(startWord, 0));
        
        Set<String> visited = new HashSet<>();
        visited.add(startWord);

        Map<String, String> parentMap = new HashMap<>();

        while (!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll();
            String currWord = currNode.getWord(); 
            int currCost = currNode.getCost();

            System.out.println(currWord);

            if(currWord.equals(endWord)){
                return makePath(parentMap, startWord, endWord);
            }
            List<String> nextWords = getNextWords(currWord, Dictionary);
            for (String nextWord : nextWords) {
                int nextCost = currCost + 1; 
                
                if (!visited.contains(nextWord) || nextCost < getCost(nextWord, priorityQueue)) {
                    visited.add(nextWord);
                    parentMap.put(nextWord, currWord);
                    priorityQueue.offer(new Node(nextWord, nextCost));
                }
            }
        }
        return Collections.emptyList();
    }

    private int getCost(String word, PriorityQueue<Node> priorityQueue) {
        for (Node node : priorityQueue) {
            if (node.getWord().equals(word)) {
                return node.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }
    private static List<String> getNextWords(String endWord, List<String> dictionary) {
        List<String> nextWords = new ArrayList<>();
        for (String dictWord : dictionary) {
            if (isOneCharDifference(dictWord, endWord)) {
                nextWords.add(dictWord);
            }
        }
        return nextWords;
    }

    private static boolean isOneCharDifference(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int differencesCount = 0;
        int diffIndex = -1;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                differencesCount++;
                if (differencesCount > 1) {
                    return false; 
                }
                diffIndex = i; 
            }
        }

        return differencesCount == 1 && word1.charAt(diffIndex) != word2.charAt(diffIndex);
    }

    private List<String> makePath(Map<String, String> parentMap, String startWord, String endWord){
        List<String> newPath = new ArrayList<>(); 
        String current = endWord; 
        while(current != null){
            newPath.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(newPath);
        return newPath;
    }
    
    private class Node{
        private String word; 
        private int cost; 
    
        public Node(String word, int cost){
            this.word = word; 
            this.cost = cost;
        }

        public String getWord(){
            return this.word;
        }
        public int getCost(){
            return this.cost;
        }
    }
}
