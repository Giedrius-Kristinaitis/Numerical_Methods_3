package main;

import action.GraphAction;
import action.NewtonInterpolationAction1;
import action.NewtonInterpolationAction2;
import math.function.FunctionInterface;
import math.function.InterpolationFunction1;

import javax.swing.*;

/**
 * Main class
 */
@SuppressWarnings("Duplicates")
public class Main {

    /**
     * Entry point of the program
     *
     * @param args arguments for the program
     */
    public static void main(String[] args) {
        new Main();
    }

    // function data
    private FunctionInterface interpolationFunction1;

    // actions
    private GraphAction newtonInterpolationAction1;
    private GraphAction newtonInterpolationAction2;

    /**
     * Default class constructor
     */
    private Main() {
        createData();
        showWindow();
    }

    /**
     * Creates data for functions
     */
    private void createData() {
        interpolationFunction1 = new InterpolationFunction1();

        newtonInterpolationAction1 = new NewtonInterpolationAction1(interpolationFunction1);
        newtonInterpolationAction2 = new NewtonInterpolationAction2(interpolationFunction1);
    }

    /**
     * Shows application's window
     */
    private void showWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("L3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(64, 64, 720, 586);
        frame.setLayout(null);
        initializeUI(frame);
        frame.setVisible(true);
    }

    /**
     * Initializes user interface
     *
     * @param frame frame to put components into
     */
    private void initializeUI(JFrame frame) {
        JButton first = new JButton("Interpoliavimas daugianariu (Niutono baze) taskai pasiskirste tolygiai");
        JButton second = new JButton("Interpoliavimas daugianariu (Niutono baze) taskai apskaiciuoti naudojant Ciobysevo abscises");

        first.addActionListener(newtonInterpolationAction1);
        second.addActionListener(newtonInterpolationAction2);

        frame.add(first).setBounds(0, 0, 720, 50);
        frame.add(second).setBounds(0, 50, 720, 50);
    }
}
