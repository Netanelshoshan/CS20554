/**
 * Name: netanel shoshan
 * This class represents the notes frame and all the relevant components.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class NotesFrame extends JFrame implements ActionListener {

    private JComboBox<Integer> yearBox,dayBox,monthBox; // combo's components
    public HashMap<Date, String> data;
    private JTextArea textArea;
    private Date date;

    //empty constructor
    public NotesFrame() {
        this(new HashMap<>());
    }


    public NotesFrame(HashMap<Date, String> Data) {
        super("My Notes");
        this.data = Data;
        date = new Date(1, 1, 2021);
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());

        //create a Jcombobox for the year
        yearBox = new JComboBox<>();
        for (int i = 1940; i <= LocalDateTime.now().getYear(); i++) {
            yearBox.addItem(i);
        }
        yearBox.addActionListener(this);

        //create a Jcombobox for the month
        monthBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        monthBox.addActionListener(this);

        //create a Jcombobox for the day
        dayBox = new JComboBox<>();
        dayBox.addActionListener(this);


        //Start with current year selected
        yearBox.setSelectedIndex(yearBox.getItemCount() - 1);
        monthBox.setSelectedIndex(monthBox.getItemCount() - monthBox.getItemCount());
        dayBox.setSelectedIndex(dayBox.getItemCount() - dayBox.getItemCount());
        date.set((int) yearBox.getSelectedItem(), (int) monthBox.getSelectedItem(), (int) dayBox.getSelectedItem());

        //Days
        header.add(new JLabel("Day:"));
        header.add(dayBox);
        //Months
        header.add(new JLabel("Month:"));
        header.add(monthBox);
        //years
        header.add(new JLabel("Year:"));
        header.add(yearBox);
        //Show notes
        JButton show = new JButton("Show Notes");
        header.add(show);
        show.addActionListener(e -> {
            extractData();
        });

        //the header contains the day,month,year and the 'show notes' button
        add(header, BorderLayout.NORTH);


        //text area
        textArea = new JTextArea(10, 10);
        add(textArea, BorderLayout.CENTER);

        //save button
        JButton save = new JButton("Save");
        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.put(date, textArea.getText());
                textArea.setText("");
                JOptionPane.showMessageDialog(null, "Saved.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(save, BorderLayout.SOUTH);


    }

    //extract the data function
    private void extractData() {
        String s = data.get(date);
        textArea.setText(s);
        System.out.printf("Data:%s%n", s);
    }

    //listener that will set the days and the month
    public void actionPerformed(ActionEvent e) {
        int year = (int) yearBox.getSelectedItem();
        int month = (int) monthBox.getSelectedItem();
        int daysInThisMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        int previousSelection = dayBox.getSelectedItem() != null ? (int) dayBox.getSelectedItem() : 1;
        dayBox.removeAllItems();
        for (int i = 1; i <= daysInThisMonth; i++) {
            dayBox.addItem(i);
        }
        if (previousSelection >= dayBox.getItemCount())
            //select last index of month
            dayBox.setSelectedIndex(dayBox.getItemCount() - 1);
        else
            dayBox.setSelectedItem(previousSelection);

        date.set(year, month, (int) dayBox.getSelectedItem());
    }

}
