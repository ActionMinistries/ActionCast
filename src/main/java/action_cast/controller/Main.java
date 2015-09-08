package action_cast.controller;

import action_cast.model.Performance;
import action_cast.model.Song;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JButton button1;
    private JTable table1;

    private static List<Performance> performances = new ArrayList<>();

    public static void initialize() {
        performances.add(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();
        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        initialize();
        String [] listData = new String[performances.size()];
        for (int i = 0; i < performances.size(); ++i) {
            listData[i] = performances.get(i).getName();
        }
        //main.list1.setListData(listData);

        frame.setVisible(true);
    }
}
