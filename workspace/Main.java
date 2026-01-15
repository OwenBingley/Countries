// Owen Bingley
// Date: january 10, 2026
// This is the main class that runs the GUI application to display country information,
// quiz the user on capitals, and allow review of country details.


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main 
{
    // array of Country objects
    private Country[] countryArray = new Country[10];
    private int index = 0;

    // quiz tracking
    private String quizType = "capital"; // can expand later

    // GUI elements
    private JFrame jFrame = new JFrame("Countries");
    private ImageIcon img;
    private JLabel imageLabel;
    private JLabel outputLabel;
    private JTextArea userInput;
    //precondition: none
   // postcondition: starts the GUI application
 public static void main(String[] args) {
        Main gui = new Main();
        gui.loadCountries();
        gui.showCountry();
    }
    //precondition: none
   // postcondition: Loads country data from a CSV file into the countryArray
    // Loads country data from a CSV file into the countryArray
    public void loadCountries() {
        File file = new File("/workspaces/Countries/workspace/countries-data.csv");
       // Read the file and populate countryArray
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;

            while (scanner.hasNextLine() && i < countryArray.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                countryArray[i] = new Country(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3]
                );
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
// precondition: countryArray is populated
   // postcondition: displays the current country image and prompts for capital
    // Displays the current country image and prompts for capital
    public void showCountry() {
        Country c = countryArray[index];
        String imagefile = c.getImageFile();

        img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
        imageLabel.setIcon(img);

        outputLabel.setText("What is the capital of this country?");
    }
// precondition: none
   // postcondition: moves to the next country in the array
    // Next button
    public void nextButtonClick() {
        index++;
        if (index >= countryArray.length) {
            index = 0;
        }
        outputLabel.setText("");
        userInput.setText("");
        showCountry();
    }
// precondition: none
   // postcondition: displays the current country's details
 // Review button
    public void reviewButtonClick() {
        Country c = countryArray[index];
        String info = c.toString();
        System.out.println(info);
        outputLabel.setText(info);
    }
    //precondition: none
   // postcondition: checks the user's answer for the quiz
    // Quiz button
    public void quizButtonClick() {
        Country c = countryArray[index];
        String answer = userInput.getText().trim();

        if (answer.equalsIgnoreCase(c.getCapital())) {
            outputLabel.setText("Correct!");
        } else {
            outputLabel.setText(
                "Incorrect. The capital is " + c.getCapital()
            );
        }
        userInput.setText("");
    }
    // precondition: none
   // postcondition: sets up the GUI components
    // Constructor to set up the GUI
    public Main() {
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton nextButton = new JButton("Next");

        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(nextButton);

        img = new ImageIcon("worldmap.jpg");
        imageLabel = new JLabel(img);
        outputLabel = new JLabel();

        jFrame.add(imageLabel);

        userInput = new JTextArea(1, 40);
        jFrame.add(userInput);
        jFrame.add(outputLabel);

        jFrame.setVisible(true);

        reviewButton.addActionListener(e -> reviewButtonClick());
        quizButton.addActionListener(e -> quizButtonClick());
        nextButton.addActionListener(e -> nextButtonClick());
    }
}