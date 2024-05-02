package src.Algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Astar extends Algorithm{
     public List<String> process(String startWord, String endWord, List<String> dictionary){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Set<String> visitedWords = new HashSet<>(); 

        GreedyBest gbfs = new GreedyBest();
        UCS ucs = new UCS();
        priorityQueue.add(new Node(startWord, gbfs.heuristic(startWord, endWord), null));
        while(!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll(); 
            String currWord = currNode.getWord(); 
            int currCost = currNode.getCost();
            visitedWords.add(currWord); 
            List<String> nextWords = getValidWords(currWord, dictionary);
            for(String nextWord : nextWords){
                int newCost = currCost + ucs.calculateCost(nextWord, endWord) + gbfs.heuristic(nextWord, endWord);
                if(visitedWords.contains(nextWord)){
                    continue;
                }
                if (!visitedWords.contains(nextWord)) {
                    Node next = new Node(nextWord, newCost, currNode);
                    priorityQueue.add(next);
                    if (nextWord.equals(endWord)) {
                        return next.reconstructPath();
                    }
                }
            }
        } 
        return Collections.emptyList();
    }
}
