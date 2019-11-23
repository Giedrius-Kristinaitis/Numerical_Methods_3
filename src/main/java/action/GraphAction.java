package action;

import graph.GraphDataProvider;
import math.function.FunctionInterface;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Shows a window with a graph
 */
public abstract class GraphAction implements ActionListener, GraphDataProvider {

    // displayed functions
    protected List<FunctionInterface> functions = new ArrayList<FunctionInterface>();

    /**
     * Default class constructor
     *
     * @param functions displayed functions
     */
    public GraphAction(FunctionInterface... functions) {
        this.functions.addAll(Arrays.asList(functions));
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        for (FunctionInterface function : functions) {
            function.initialize();
        }

        showChart();
        System.out.println();
    }

    /**
     * Shows the graph chart on the screen
     */
    private void showChart() {
        // create window
        ApplicationFrame frame = new ApplicationFrame(getWindowTitle());

        // create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                getTitle(),
                getXLabel(),
                getYLabel(),
                getDataset(),
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        // create chart container
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(512, 512));

        // create renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // set line colors
        for (int i = 0; i < functions.size(); i++) {
            renderer.setSeriesPaint(i, functions.get(i).getColor());
            renderer.setSeriesShapesVisible(i, functions.get(i).areShapesVisible());
            renderer.setSeriesLinesVisible(i, functions.get(i).areLinesVisible());
        }

        // apply renderer
        chart.getXYPlot().setRenderer(renderer);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);
        chart.getXYPlot().setRangeZeroBaselineVisible(true);

        // apply chart container
        frame.setContentPane(panel);
        frame.pack();

        // show window
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    /**
     * Gets the dataset
     *
     * @return
     */
    private XYDataset getDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        fillSeriesCollection(dataset);

        return dataset;
    }

    /**
     * Fills series collection with series
     *
     * @param collection collection to fill
     */
    private void fillSeriesCollection(XYSeriesCollection collection) {
        for (FunctionInterface function : functions) {
            XYSeries series = new XYSeries(function.getTitle());
            fillSeries(series, function);
            collection.addSeries(series);
        }
    }

    /**
     * Fills xy series with point data
     *
     * @param series   series to fill
     * @param function function
     */
    private void fillSeries(XYSeries series, FunctionInterface function) {
        if (function.onlyRenderBounds()) {
            series.add(function.getLeftMostX(), 0);
            series.add(function.getRightMostX(), 0);
            return;
        }

        double step = (function.getRightMostX() - function.getLeftMostX()) / (double) function.getPointCount();

        for (int i = 0; i < function.getPointCount(); i++) {
            double x = function.notEvenXIntervals() ? function.getXValue() : function.getLeftMostX() + step * i;
            series.add(x, function.getValue(x));
        }
    }

    /**
     * Gets chart window title
     *
     * @return
     */
    @Override
    public String getWindowTitle() {
        return "L3";
    }

    /**
     * Gets X axis label
     *
     * @return
     */
    @Override
    public String getXLabel() {
        return "";
    }

    /**
     * Gets Y axis label
     *
     * @return
     */
    @Override
    public String getYLabel() {
        return "";
    }
}
