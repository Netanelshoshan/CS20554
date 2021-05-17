/**
 * Name : netanel shoshan
 * Main class for running the application
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;

public class main extends JFrame {
    private static NotesFrame notesFrame;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    protected static HashMap<Date, String> data;

    public static void main(String[] args) {
        data = new HashMap<Date, String>();
        int answer = JOptionPane.showConfirmDialog(null,
                "Import notes from file?", "Notes", JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            openFile();
            readRecords();
        }
        start();

    }


    public static void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            System.exit(1);
        Path path = fileChooser.getSelectedFile().toPath();
        try {
            input = new ObjectInputStream(Files.newInputStream(path));
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error opening file.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @SuppressWarnings(value = "unchecked")
    public static void readRecords() {
        try {
            while (true) {
                        data = (HashMap<Date, String>) input.readObject();
            }
        } catch (EOFException eofException) {
            return;
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error reading from file. Terminating.");
            System.exit(1);
        } catch (ClassNotFoundException classNotFoundException) {
            JOptionPane.showMessageDialog(null, "Invalid object type. Terminating.");
            System.exit(1);
        }
    }

    public static void start() {
        notesFrame = new NotesFrame(data);
        notesFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        notesFrame.pack();
        notesFrame.setMinimumSize(new Dimension(notesFrame.getWidth(), notesFrame.getHeight()));
        notesFrame.setVisible(true);
        notesFrame.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent notesFrameEvent) {
                int res = JOptionPane.showConfirmDialog(notesFrame,
                        "Would you like to save your notes?", "Close notesFrame?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (res == JOptionPane.NO_OPTION || res == JOptionPane.CLOSED_OPTION)
                    System.exit(1);
                if (res == JOptionPane.YES_OPTION)
                    writeToFile();
            }
        });
    }

    public static void writeToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        if (fileChooser.showSaveDialog(notesFrame) == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            if (!filename.endsWith(".ser"))
                filename += ".ser";
            try {
                File file = new File(filename);
                output = new ObjectOutputStream(new FileOutputStream(file));
                output.writeObject(notesFrame.data);
                output.close();
                System.exit(1);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(notesFrame, "Couldn't save file", "Bad Input", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

    }

    public static void closeInputFile() {
        try {
            if (input != null)
                input.close();
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error closing file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}





