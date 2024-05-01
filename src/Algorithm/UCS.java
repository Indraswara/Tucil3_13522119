package src.Algorithm;

import java.util.*;
import src.Util.DictionaryLib;

public class UCS extends Algorithm{
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

            
            visitedWords.add(currWord);
            
            List<String> nextWords = generateValidWords(currWord, startWord, endWord, dictionary);
            for (String nextWord : nextWords) {
                int newCost = calculateCost(currWord, nextWord);

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