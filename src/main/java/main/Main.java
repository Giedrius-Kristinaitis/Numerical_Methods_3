package main;

import action.*;
import math.function.FunctionInterface;
import math.function.InterpolationFunction1;
import math.function.TemperatureFunction1;

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
    private FunctionInterface temperatureFunction;

    // actions
    private GraphAction newtonInterpolationAction1;
    private GraphAction newtonInterpolationAction2;
    private GraphAction newtonTemperatureAction1;
    private GraphAction hermitTemperatureAction1;
    private GraphAction hermitCountryBorderAction10;
    private GraphAction hermitCountryBorderAction20;
    private GraphAction hermitCountryBorderAction50;
    private GraphAction hermitCountryBorderAction100;

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
        temperatureFunction = new TemperatureFunction1();

        newtonInterpolationAction1 = new NewtonInterpolationAction1(interpolationFunction1);
        newtonInterpolationAction2 = new NewtonInterpolationAction2(interpolationFunction1);
        newtonTemperatureAction1 = new NewtonTemperatureInterpolationAction1(temperatureFunction);
        hermitTemperatureAction1 = new HermiteTemperatureInterpolationAction1(temperatureFunction);

        hermitCountryBorderAction10 = new HermiteCountryBorderAction(10);
        hermitCountryBorderAction20 = new HermiteCountryBorderAction(20);
        hermitCountryBorderAction50 = new HermiteCountryBorderAction(50);
        hermitCountryBorderAction100 = new HermiteCountryBorderAction(100);
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
        JButton third = new JButton("Interpoliavimas daugianariu (Niutono baze) temperaturos");
        JButton fourth = new JButton("Interpoliavimas Ermito splainu temperaturos");
        JButton fifth = new JButton("Parametrinis interpoliavimas Ermito splainu Kipro konturas (10 interpoliavimo tasku)");
        JButton sixth = new JButton("Parametrinis interpoliavimas Ermito splainu Kipro konturas (20 interpoliavimo tasku)");
        JButton seventh = new JButton("Parametrinis interpoliavimas Ermito splainu Kipro konturas (50 interpoliavimo tasku)");
        JButton eighth = new JButton("Parametrinis interpoliavimas Ermito splainu Kipro konturas (100 interpoliavimo tasku)");

        first.addActionListener(newtonInterpolationAction1);
        second.addActionListener(newtonInterpolationAction2);
        third.addActionListener(newtonTemperatureAction1);
        fourth.addActionListener(hermitTemperatureAction1);
        fifth.addActionListener(hermitCountryBorderAction10);
        sixth.addActionListener(hermitCountryBorderAction20);
        seventh.addActionListener(hermitCountryBorderAction50);
        eighth.addActionListener(hermitCountryBorderAction100);

        frame.add(first).setBounds(0, 0, 720, 50);
        frame.add(second).setBounds(0, 50, 720, 50);
        frame.add(third).setBounds(0, 100, 720, 50);
        frame.add(fourth).setBounds(0, 150, 720, 50);
        frame.add(fifth).setBounds(0, 200, 720, 50);
        frame.add(sixth).setBounds(0, 250, 720, 50);
        frame.add(seventh).setBounds(0, 300, 720, 50);
        frame.add(eighth).setBounds(0, 350, 720, 50);
    }
}
