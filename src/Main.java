package src;
import java.util.*;
import src.Util.*;
import src.Algorithm.*;


public class Main {
    public static void main(String[] args){
        DictionaryLib dict = new DictionaryLib("src/Data/dict.txt");
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Selamat datang di Word Ladder: ");
        System.out.print("Start word: "); 
        String startWord = scanner.nextLine();
        System.out.print("End Word: ");
        String endWord = scanner.nextLine();

        UCS ucs = new UCS();
        GreedyBest gbfs = new GreedyBest();
        Astar astar = new Astar();
        List<String> ans = Collections.emptyList();
        long startTime = 0, endTime = 0;
        if(dict.isInDict(endWord) && dict.isInDict(startWord)){
            //filter word
            List<String> validWords = new ArrayList<String>();
            for(String word : dict.getDict()){
                if(word.length() == startWord.length()){
                    validWords.add(word);
                }
            }
            System.out.println("Pilih algoritma: ");
            System.out.println("1. UCS");
            System.out.println("2. Greedy Best First Search");
            System.out.println("3. AStar");
            System.out.print("> ");
            int input = validator(1, 3);
            startTime = System.currentTimeMillis();
            switch (input) {
                case 1:
                    ans = ucs.process(startWord, endWord, validWords);    
                    break;
                case 2: 
                    ans = gbfs.process(startWord, endWord, validWords);
                    break;
                case 3: 
                    ans = astar.process(startWord, endWord, validWords);
                    break;
                default:
                    break;
            }
            endTime = System.currentTimeMillis();
        }
        System.out.println(ans);
        System.out.println((endTime-startTime) +  " ms");
        scanner.close();
    } 

    private static int validator(int start, int end){
        Scanner scanner = new Scanner(System.in); 
        int input = scanner.nextInt();
        while(input < start || input > end ){
            System.out.println("Input invalid masukkan antara 1-3: ");
            System.out.print("> ");
            input = scanner.nextInt();
        }
        scanner.close();
        return input;
    }
}
