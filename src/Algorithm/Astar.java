package src.Algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


import src.Util.DictionaryLib;

public class Astar extends Algorithm{
     public List<String> process(String startWord, String endWord, DictionaryLib dicrionary){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Map<String, Integer> costMap = new HashMap<>(); 
        Set<String> visitedWords = new HashSet<>(); 
        Map<String, String> path = new HashMap<>();

        GreedyBest gbfs = new GreedyBest();
        priorityQueue.offer(new Node(startWord, gbfs.heuristic(startWord, endWord)));
        costMap.put(startWord, gbfs.heuristic(startWord, endWord));
        while(!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll(); 
            String currWord = currNode.getWord(); 
            int currCost = currNode.getCost();
            visitedWords.add(currWord); 
            List<String> nextWords = generateValidWords(currWord, startWord, endWord, dicrionary); 
            for(String nextWord : nextWords){
                int newCost = currCost + 1 + gbfs.heuristic(nextWord, endWord);
                if (!visitedWords.contains(nextWord) && (!costMap.containsKey(nextWord) || newCost < costMap.get(nextWord))) {
                    costMap.put(nextWord, newCost);
                    path.put(nextWord, currWord);
                    priorityQueue.offer(new Node(nextWord, newCost));
                    if (nextWord.equals(endWord)) {
                        return reconstructPath(path, startWord, endWord);
                    }
                }
            }
        } 
        return Collections.emptyList();
    }

    public int funcCost(String nextWord, String endWord){
        GreedyBest gbfs = new GreedyBest();
        int heuristic = gbfs.heuristic(nextWord, endWord);
        
        return heuristic;
    } 
}
