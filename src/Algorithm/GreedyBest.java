package src.Algorithm;

import java.util.*;
import src.Util.DictionaryLib;


public class GreedyBest extends Algorithm{
    public List<String> process(String startWord, String endWord, DictionaryLib dicrionary){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Map<String, Integer> costMap = new HashMap<>(); 
        Set<String> visitedWords = new HashSet<>(); 
        Map<String, String> path = new HashMap<>();

        priorityQueue.add(new Node(startWord, heuristic(startWord, endWord)));
        costMap.put(startWord, heuristic(startWord, endWord));

        while(!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll(); 
            String currWord = currNode.getWord(); 
            visitedWords.add(currWord); 
            List<String> nextWords = generateValidWords(currWord, startWord, endWord, dicrionary); 
            for(String nextWord : nextWords){
                int newCost = heuristic(nextWord, endWord);
                // System.out.println(nextWord + " : " +  newCost);
                if (!visitedWords.contains(nextWord) && (!costMap.containsKey(nextWord) || newCost < costMap.get(nextWord))) {
                    costMap.put(nextWord, newCost); 
                    path.put(nextWord, currWord);
                    priorityQueue.add(new Node(nextWord, newCost));
                    if (nextWord.equals(endWord)) {
                        return reconstructPath(path, startWord, endWord);
                    }
                }
            }
        } 
        return Collections.emptyList();
    }

    public int heuristic(String word, String endWord){
        int distance = 0; 
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != endWord.charAt(i)){
                distance += 10;
            }
        }
        return distance;

    }
}
