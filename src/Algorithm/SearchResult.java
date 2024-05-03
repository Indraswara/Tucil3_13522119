package src.Algorithm;

import java.util.*;

public class SearchResult{
    private List<String> path;
    private int visitedWordCount;

    public SearchResult(){
        this.path = null; 
        this.visitedWordCount = 0;
    }

    public SearchResult(List<String> path, int visitedWordCount) {
        this.path = path;
        this.visitedWordCount = visitedWordCount;
    }

    public List<String> getPath() {
        return path;
    }

    public int getVisitedWordCount() {
        return visitedWordCount;
    }
}