import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReadServerFile extends JFrame {
    private JTextField enterField;
    private JEditorPane contentsArea;

    public ReadServerFile() {
        super("Simple Web Browser");

        enterField = new JTextField("Enter File URL here");
        enterField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getThePage(e.getActionCommand());
            }
        });
        add(enterField, BorderLayout.NORTH);

        contentsArea = new JEditorPane();
        contentsArea.setEditable(false);
        contentsArea.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                    getThePage(e.getURL().toString());
            }
        });
        add(new JScrollPane(contentsArea),BorderLayout.CENTER);
        setSize(400,300);
        setVisible(true);
    }

    public void getThePage(String location) {
        try {
            contentsArea.setPage(location);
            enterField.setText(location);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(this, "Error while retriving url", "bad url", JOptionPane.ERROR_MESSAGE);
        }
    }

}
