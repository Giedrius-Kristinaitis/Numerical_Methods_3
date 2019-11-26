package action;

import math.Point;
import math.function.FunctionInterface;
import math.function.HermitPolynomial;
import math.function.InterpolationPointFunction;
import math.interpolation.EvenIntervalPointProvider;

import java.awt.event.ActionEvent;

/**
 * Hermit's interpolation for temperature data
 */
@SuppressWarnings("Duplicates")
public class HermitTemperatureInterpolationAction1 extends GraphAction {

    protected FunctionInterface interpolationFunction;
    protected EvenIntervalPointProvider equalIntervalPointProvider;

    /**
     * Class constructor
     *
     * @param function
     */
    public HermitTemperatureInterpolationAction1(FunctionInterface function) {
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
        interpolate();

        functions.add(new InterpolationPointFunction(equalIntervalPointProvider));

        super.actionPerformed(event);
    }

    /**
     * Performs interpolation
     */
    protected void interpolate() {
        Point[] points = collectPoints();

        HermitPolynomial polynomial = new HermitPolynomial();

        polynomial.setFunction(interpolationFunction);
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
