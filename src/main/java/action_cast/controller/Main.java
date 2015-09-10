package action_cast.controller;

import javax.swing.*;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class Main {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private EditSessionForm editSessionForm1;
    private JPanel Session;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();

        frame.setContentPane(main.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
