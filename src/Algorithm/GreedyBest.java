package src.Algorithm;

import java.util.*;


public class GreedyBest extends Algorithm{
    public List<String> process(String startWord, String endWord, List<String> dictionary){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<String> visitedWords = new HashSet<>(); 

        priorityQueue.add(new Node(startWord, heuristic(startWord, endWord), null));

        while(!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll(); 
            String currWord = currNode.getWord(); 
            // visitedWords.add(currWord); 
            // dictionary.remove(currWord);
            List<String> nextWords = getValidWords(currWord, dictionary); 
            for(String nextWord : nextWords){
                if(!visitedWords.contains(nextWord)){
                    visitedWords.add(nextWord); 
                    dictionary.remove(nextWord);
                    Node next = new Node(nextWord, heuristic(nextWord, endWord), currNode);
                    priorityQueue.add(next);
                    if (nextWord.equals(endWord)) {
                        return next.reconstructPath();
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
                distance += 1;
            }
        }
        return distance;
    }
}
