package src.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
    public static List<String> readFile(String path){
        List<String> arrayOfStrings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line; 
            while((line = reader.readLine()) != null){
                String[] arrayOfWord = line.split("\\s+");

                for(var Word : arrayOfWord){
                    arrayOfStrings.add(Word);
                }
            }
        } catch(IOException e){
            System.err.println("Error baca file: " + e.getMessage());
        }

        return arrayOfStrings;
    }

}
