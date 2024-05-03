package src;
import javax.swing.SwingUtilities;

import src.GUI.*;


public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->{
            new GUI(); 
        });
    } 

}
