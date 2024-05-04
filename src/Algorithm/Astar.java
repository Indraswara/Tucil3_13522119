package src.Algorithm;

import java.util.*;


public class Astar extends Algorithm{
     public SearchResult process(String startWord, String endWord, List<String> dictionary){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Set<String> visitedWords = new HashSet<>(); 

        GreedyBest gbfs = new GreedyBest();
        priorityQueue.add(new Node(startWord, gbfs.heuristic(startWord, endWord), null));
        while(!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll(); 
            String currWord = currNode.getWord(); 
            int currCost = currNode.getCost();
            visitedWords.add(currWord); 
            dictionary.remove(currWord);
            List<String> nextWords = getValidWords(currWord, dictionary);
            for(String nextWord : nextWords){
                int newCost = currCost + 1 + gbfs.heuristic(nextWord, endWord);
                if(!visitedWords.contains(nextWord)) {
                    visitedWords.add(nextWord); 
                    dictionary.remove(nextWord);
                    Node next = new Node(nextWord, newCost, currNode);
                    priorityQueue.add(next);
                    if(nextWord.equals(endWord)) {
                        return new SearchResult(next.reconstructPath(), visitedWords.size());
                    }
                }
            }
        } 
        return new SearchResult();
    }
}
