package action;

import math.Point;
import math.function.FunctionInterface;
import math.function.HermitePolynomial;
import math.function.InterpolationPointFunction;
import math.interpolation.EvenIntervalPointProvider;

import java.awt.event.ActionEvent;

/**
 * Hermite interpolation for temperature data
 */
@SuppressWarnings("Duplicates")
public class HermiteTemperatureInterpolationAction1 extends GraphAction {

    protected FunctionInterface interpolationFunction;
    protected EvenIntervalPointProvider equalIntervalPointProvider;

    /**
     * Class constructor
     *
     * @param function
     */
    public HermiteTemperatureInterpolationAction1(FunctionInterface function) {
        this.interpolationFunction = function;

        equalIntervalPointProvider = new EvenIntervalPointProvider();
        equalIntervalPointProvider.setFunction(function);
        equalIntervalPointProvider.setBounds(function.getLeftMostX(), function.getRightMostX());
        equalIntervalPointProvider.setPointCountFunction(function);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        functions.clear();

        interpolate();

        functions.add(new InterpolationPointFunction(equalIntervalPointProvider));

        super.actionPerformed(event);
    }

    /**
     * Performs interpolation
     */
    protected void interpolate() {
        Point[] points1 = collectPoints();

        HermitePolynomial polynomial = new HermitePolynomial();

        Point[] points = new Point[points1.length + 2];

        points[0] = new Point(0, 9);
        points[points1.length + 1] = new Point(13, 9);

        for (int i = 0; i < points1.length; i++) {
            points[i + 1] = points1[i];
        }

        polynomial.setPoints(points);

        functions.add(polynomial);
    }

    /**
     * Collects interpolation points
     *
     * @return
     */
    protected Point[] collectPoints() {
        Point[] points = new Point[interpolationFunction.getPointCount()];

        for (int i = 0; i < equalIntervalPointProvider.getPointCount(); i++) {
            points[i] = equalIntervalPointProvider.getPoint(i);
        }

        return points;
    }

    /**
     * Gets chart title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Interpoliavimas Ermito splainu - temperaturos Kipras 2004";
    }
}
