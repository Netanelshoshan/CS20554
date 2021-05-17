import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HangMan extends JFrame {
    //private final Word word; //the word of the game
    //private panel hanginng_man_panel; // the panel of the hangging man
    private JButton startGameButton, chooseFromFileButton, sendButton, resetButton;// the button to try next char
    private JFrame frame; // the fareme where the game is happening.
    private JPanel statusLayout, inputField, optionsLeftField;// layers for the game control of the game
    private TextField textField; //text fot input
    private int noOfHits; //number of hit for the game
    private JPanel globalLayout; // layout object
    private static Scanner input;
    private static ArrayList<String> wordsArray;
    private static String completeWord, guessedWord;
    private static ArrayList<Character> chosenCharacters, remainingCharacters;
    private HangingManPanel hangingManPanel;
    private JPanel getStatusLayout;

    // no-argument constructor
    public HangMan() {
        frame = new JFrame("Hanging Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,450);

        globalLayout = new JPanel();
        globalLayout.setLayout(new BoxLayout(globalLayout,BoxLayout.Y_AXIS));

        statusLayout = new JPanel();
        statusLayout.setLayout(new FlowLayout());
        statusLayout.add(new JLabel("Status: "));
        //getStatusLayout = new JLabel(guessedWord)


        /*
        START GAME OPTION
         */
        startGameButton = new JButton("Start Game");
        add(startGameButton);
        startGameButton.addActionListener(e -> {
            globalLayout.setLayout(new FlowLayout());
            openFile();//open the default "words.txt" file
            readRecords();
            closeFile();
        });

        /*
        CHOOSE FROM FILE OPTION
         */
        chooseFromFileButton = new JButton("Choose from file");
        add(chooseFromFileButton);
        chooseFromFileButton.addActionListener(e -> {
            //globalLayout.setLayout(new FlowLayout);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.CANCEL_OPTION)
                System.exit(1);
            Path path = fileChooser.getSelectedFile().toPath();
            try {
                input = new Scanner(path);
            } catch (IOException ioException) {
                System.err.println("File improperly formed. Terminating.");
            }
            readRecords();
            closeFile();
            //globalLayout.layoutContainer(container);
        });
        hangingManPanel = new HangingManPanel();


    }

    // open file clients.txt
    public static void openFile() {
        try {
            input = new Scanner(Paths.get("words.txt"));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }

    // read record from file
    public static void readRecords() {
        wordsArray = new ArrayList<>();
        try {
            while (input.hasNext()) // while there is more to read
            {
                wordsArray.add(input.next());
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }
    } // end method readRecords

    // close file and terminate application
    public static void closeFile() {
        if (input != null)
            input.close();
    }


    private class HangingManPanel extends JPanel {
        public HangingManPanel() {
            super();
        }

        private int drawingCounter = 0;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width, height, deltaH, deltaW, stageH, stageW;
            height = getHeight();
            width = getWidth();
            deltaW = width / 2;
            deltaH = height / 2;
            stageH = height / 100;
            stageW = width / 100;
            g.clearRect(0, 0, width, height);
            g.setColor(new Color(255, 171, 3));
            g.drawLine(deltaW - 45 * stageW, 98 * stageH, deltaW + 40 * stageW, 98 * stageH);
            g.drawLine(deltaW - 40 * stageW, 98 * stageH, deltaW - 40 * stageW, 5 * stageH);
            g.drawLine(deltaW - 40 * stageW, 5 * stageH, deltaW, 5 * stageH);
            g.drawLine(deltaW, 5 * stageH, deltaW, 10 * stageH);
            g.setColor(Color.black);

            for (int i = 0; i < drawingCounter; i++) {
                switch (i) {
                    case 0:
                        g.drawOval(deltaW - 10 * stageW, 10 * stageH, 20 * stageW, 20 * stageH);
                        break;
                    case 1:
                        g.drawLine(deltaW, 30 * stageH, deltaW, 75 * stageH);
                        break;
                    case 2:
                        g.drawLine(deltaW, 45 * stageH, deltaW + 15 * stageW, 60 * stageH);
                        break;
                    case 3:
                        g.drawLine(deltaW, 45 * stageH, deltaW - 15 * stageW, 60 * stageH);
                        break;
                    case 4:
                        g.drawLine(deltaW, 75 * stageH, deltaW + 20 * stageW, 95 * stageH);
                        break;
                    case 5:
                        g.drawLine(deltaW, 75 * stageH, deltaW - 20 * stageW, 95 * stageH);
                        break;
                    case 6:
                        g.setColor(Color.red);
                        g.drawLine(deltaW - 20 * stageW, 10 * stageH, deltaW + 20 * stageW, 95 * stageH);
                        g.setColor(Color.black);
                        break;
                }
            }

        }

        public void incrementDrawingCounter() {
            drawingCounter++;
        }

        public boolean isFinishedDrawing() {
            return drawingCounter >= 7;
        }

    }

    private static int setChar(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            char newC = Character.toLowerCase(c);
            if (!chosenCharacters.contains(newC)) {
                chosenCharacters.add(newC);
            }
            if (remainingCharacters.contains(newC)) {
                remainingCharacters.remove(remainingCharacters.indexOf(newC));
            }
            guessedWord = "";
            for (int i = 0; i < completeWord.length(); i++) {
                if (chosenCharacters.contains(guessedWord.toLowerCase().charAt(i))) {
                    guessedWord += completeWord.charAt(i) + " ";
                } else {
                    guessedWord += "_ ";
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Char must be in range:\n[a-z,A-Z]", "Bad Input", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return (completeWord.toLowerCase().contains(Character.toLowerCase(c) + "")) ? 1 : -1;
    }

    public String getRandomWord() {
        Random rand = new Random();
        int i = rand.nextInt(wordsArray.size());
        return wordsArray.get(i);

    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputChar = textField.getText();
            if (inputChar.length() == 1) {
                noOfHits += 1;
                int res = setChar(inputChar.charAt(0));
                if (!hangingManPanel.isFinishedDrawing() && res == -1) {
                    hangingManPanel.incrementDrawingCounter();
                }
                hangingManPanel.repaint();
                if (guessedWord.equals(completeWord)) {
                    JOptionPane.showMessageDialog(null, "Congrats!!\nYou finish the game after: " + noOfHits + " attempts.", "End of game Word: " + completeWord, JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                if (hangingManPanel.isFinishedDrawing()) {
                    JOptionPane.showMessageDialog(null, "You have hit too many time so it is game over.\nThe Word was " + completeWord, "Game Over", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Pleas type ONE char.", "Bad Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} // end class PanelFrame
