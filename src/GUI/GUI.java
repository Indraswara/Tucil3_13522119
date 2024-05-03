package src.GUI;

import java.util.*;
import src.Algorithm.*;
import src.Util.DictionaryLib;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI implements ActionListener {

    //main frame
    private JFrame frame;
    //text area
    private JTextField startWord;
    private JTextField endWord;
    //button for submit
    private JButton button;
    //for result
    private JPanel displayPanel; 
    private JTextArea wordDisplay;

    //button for choosing algorithm; 
    private ButtonGroup groupAlgo; // groping algorithm
    private JRadioButton ucsRadioButton; 
    private JRadioButton gbfsRadioButton; 
    private JRadioButton aStarRadioButton;

    public GUI() {
        // Initialize frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WordLadder GUI");

        //make Main PANEL 
        JPanel mainPanel = new JPanel(); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create text fields
        startWord = new JTextField(20);
        endWord = new JTextField(20);

        // Create button
        button = new JButton("Start");
        button.addActionListener(this);
    
        // Create Algorithm Button 
        ucsRadioButton = new JRadioButton("UCS"); 
        gbfsRadioButton = new JRadioButton("Greedy-Best First Search"); 
        aStarRadioButton = new JRadioButton("AStar");

        //
        groupAlgo = new ButtonGroup(); 
        groupAlgo.add(ucsRadioButton);
        groupAlgo.add(gbfsRadioButton);
        groupAlgo.add(aStarRadioButton);

        // Create panel algo button 
        JPanel algoPanel = new JPanel(); 
        algoPanel.setLayout(new BoxLayout(algoPanel, BoxLayout.Y_AXIS));
        algoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //add algorithm button to algorithPanel so they will appear in screen ji
        algoPanel.add(new JLabel("Select Algorithm")); 
        algoPanel.add(ucsRadioButton); 
        algoPanel.add(gbfsRadioButton);
        algoPanel.add(aStarRadioButton);

        // Create panel for text fields
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //add start word field into panel
        textFieldPanel.add(new JLabel("Start Word:"));
        textFieldPanel.add(startWord);
        //add spacing
        textFieldPanel.add(Box.createVerticalStrut(10)); 
        //add end word field into panel
        textFieldPanel.add(new JLabel("End Word:"));
        textFieldPanel.add(endWord);

        // Create panel for button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(button);

        //create panel to display result 
        displayPanel = new JPanel(new BorderLayout()); 
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel result = new JLabel("Result: ");
        result.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        displayPanel.add(result);

        //display the result
        wordDisplay = new JTextArea(10, 20);
        wordDisplay.setEditable(false); // make it not editable
        JScrollPane scrollPane = new JScrollPane(wordDisplay); //biar bisa di-scroll;

        displayPanel.add(scrollPane, BorderLayout.CENTER);
        // Add panels to frame
        mainPanel.add(textFieldPanel, BorderLayout.NORTH);
        mainPanel.add(algoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(displayPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        // Pack and display the frame
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        DictionaryLib dict = new DictionaryLib("src/Data/dict.txt");


        if (e.getSource() == button) {
            String start = startWord.getText(); //ini buat ditaruh di-fungsi 
            String end = endWord.getText(); // ini buat ditaruh di-fungsi
            long startTime, endTime; 
            if(start.length() == end.length()){

                UCS ucs = new UCS();
                GreedyBest gbfs = new GreedyBest();
                Astar astar = new Astar();
    
                String selectedAlgo = ""; 
                if(ucsRadioButton.isSelected()){
                    selectedAlgo = "UCS";
                }else if(gbfsRadioButton.isSelected()){
                    selectedAlgo = "GBFS"; 
                }else{
                    selectedAlgo = "AStar";
                }
    
                SearchResult ans = new SearchResult();
                startTime = System.currentTimeMillis();
                switch (selectedAlgo){
                    case "UCS":
                        ans = ucs.process(start, end, dict.filterDict(start, end));
                        break;
                    case "GBFS": 
                        ans = gbfs.process(start, end, dict.filterDict(start, end));
                        break;
                    case "AStar": 
                        ans = astar.process(start, end, dict.filterDict(start, end));
                        break;
                    default:
                        break;
                }
                endTime = System.currentTimeMillis();
                
    
                wordDisplay.setText("");
                if(ans.getPath() == null){
                    wordDisplay.append("Maaf gak ketemu");
                }
                else{
                    wordDisplay.append("Lama waktu: " + (endTime - startTime) + " ms\n");
                    wordDisplay.append("Panjang node: " + ans.getPath().size() + "\n");
                    wordDisplay.append("Banyak Node divisit: " + ans.getVisitedWordCount()+ "\n");
    
                    wordDisplay.append("\nLIST NODE: \n");
                    for(var word : ans.getPath()){
                        wordDisplay.append(word + "\n");
                    }
                }
                // Implement action here based on button click
                System.out.println("Start Word: " + start);
                System.out.println("End Word: " + end);
            }
        }
    }

    public static void main(String[] args) {
        // Create GUI object
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }
}
