package main.java.edu.guilford;

import javax.swing.JFrame;

public class RecursiveCircles {
    public static void main(String[] args) {
        JFrame recursiveWindow = new JFrame("Recursive circles!");
        recursiveWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RecursiveCirclePanel panel = new RecursiveCirclePanel(800, 1200);
        recursiveWindow.add(panel);

        recursiveWindow.pack();
        recursiveWindow.setVisible(true);
    }
}

