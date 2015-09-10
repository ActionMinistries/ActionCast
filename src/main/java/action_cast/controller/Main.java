package action_cast.controller;

import action_cast.model.Performance;
import action_cast.model.Song;
import action_cast.views.PerformanceTableView;

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
    private PerformanceTableView performanceTableView1;
    private JTable table1;

    private List<Performance> performances = new ArrayList<>();

    public void initialize() {
        performances.add(new Performance(new Song("The First Song", "It goes like this na na na, na na, na na na na"), "First Performance", "First Venue", new Date()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        main.initialize();
        main.performanceTableView1.setData(main.performances);

       // main.panel1 = new JPanel();
      //  main.table1 = new PerformanceTableView();

//        main.panel1.add(main.table1);
        //main.list1.setListData(listData);
        //main.table1 = new JTable(new DefaultTableModel(new Object[]{"one", "two"}, 2));
//        DefaultTableColumnModel columns = new DefaultTableColumnModel();
//        TableColumn one = new TableColumn();
//        one.setHeaderValue("one");
//        TableColumn two = new TableColumn();
//        two.setHeaderValue("one");
//
//        columns.addColumn(one);
//        columns.addColumn(two);
//        main.table1.setColumnModel(columns);
        frame.setVisible(true);

    }
}
