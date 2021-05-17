import javax.swing.*;
import java.util.*;

public class HangManRunFile extends JFrame {

    public static void main(String[] args) {
        Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        List<char[]> chosenCharacters = Arrays.asList(alphabet);
        System.out.println(chosenCharacters);
        /*HangMan hangMan = new HangMan();
        hangMan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangMan.setVisible(true);*/
    }

}
