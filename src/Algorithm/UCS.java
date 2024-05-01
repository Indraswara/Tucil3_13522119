package src.Algorithm;

import java.util.*;

public class UCS {
    public List<String> Process(String startWord, String endWord, List<String> Dictionary){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost)); 
        priorityQueue.offer(new Node(startWord, 0));
        
        boolean[] visited = new boolean[Dictionary.size()];
        Arrays.fill(visited, false);

        Map<String, Integer> parentMap = new HashMap<>();
        parentMap.put(startWord, -1);
        while (!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll();
            String currWord = currNode.getWord(); 
            int currCost = currNode.getCost();

            
            
            int index = Dictionary.indexOf(currWord);
            visited[index] = true;
            
            List<String> nextWords = generateValidWords(currWord, endWord, Dictionary);
            for(String nextWord : nextWords){
                int nextCost = currCost + 1;
                int nextIndex = Dictionary.indexOf(nextWord);
                
                if (!visited[nextIndex] || nextCost < getCost(nextWord, priorityQueue)) {
                    visited[nextIndex] = true;
                    parentMap.put(nextWord, index);
                    priorityQueue.offer(new Node(nextWord, nextCost));

                    if(nextWord.equals(endWord)){
                        return makePath(parentMap, startWord, endWord, Dictionary);
                    }
                }
            }
            
        }
        return Collections.emptyList();
    }

    private static List<String> generateValidWords(String word, String endWord, List<String> dict) {
        List<String> validWords = new ArrayList<>();

        for (int i = word.length() - 1; i >= 0; i--) {
            char originalChar = word.charAt(i);
            char endChar = endWord.charAt(i);
            if(originalChar != endChar){
                for (char c = 'a'; c <= 'z'; c++) {
                    
                    if (c != originalChar) {
                        StringBuilder modifiedWordBuilder = new StringBuilder(word);
                        modifiedWordBuilder.setCharAt(i, c);
                        String modifiedWord = modifiedWordBuilder.toString();
    
                        if (dict.contains(modifiedWord)) {
                            validWords.add(modifiedWord);
                        }
                    }
                }
            }
        }

        return validWords;
    }

    private int getCost(String word, PriorityQueue<Node> priorityQueue) {
        for (Node node : priorityQueue) {
            if (node.getWord().equals(word)) {
                return node.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }



    private List<String> makePath(Map<String, Integer> parentMap, String startWord, String endWord, List<String> dict) {
        List<String> path = new ArrayList<>();
        String current = endWord;

        while (!current.equals(startWord)) {
            path.add(current);
            int parentIndex = parentMap.get(current);
            current = dict.get(parentIndex);
        }
        path.add(startWord);
        Collections.reverse(path);
        return path;
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
