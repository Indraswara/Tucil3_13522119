package src;

import java.util.*;
import src.Util.*;

public class Main {
    public static void main(String[] args){
        DictionaryLib dict = new DictionaryLib("src/Data/dict.txt");
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Selamat datang di Word Ladder: ");
        System.out.print("Start word: "); 
        String startWord = scanner.nextLine();
        System.out.print("End Word: ");
        String endWord = scanner.nextLine();

        if(dict.isInDict(endWord)){
            System.out.println("start");
        }

        scanner.close();
    } 
}
