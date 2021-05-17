import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Word {
    private String whole; // The whole word
    private String current; // The current status
    private List remainingCharacters = new ArrayList<Character>();
    private List chosenCharacters = new ArrayList<Character>();
    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public Word() {
        whole = "";
        current = "";
        remainingCharacters = Arrays.asList(alphabet);
    }
}
