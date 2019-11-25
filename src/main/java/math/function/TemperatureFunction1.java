package math.function;

import java.awt.*;

/**
 * Temperature data
 */
public class TemperatureFunction1 implements FunctionInterface {

    /**
     * Temperature data
     */
    protected static final double[] TEMPERATURE = {
            10.5617,
            11.1504,
            14.4338,
            16.9788,
            21.0081,
            24.7199,
            28.526,
            27.9448,
            25.7463,
            23.1029,
            16.7226,
            11.5084
    };

    /**
     * Gets the function's title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "";
    }

    /**
     * Gets the right-most x of the function
     *
     * @return
     */
    @Override
    public double getLeftMostX() {
        return 1;
    }

    /**
     * Gets the left-most x of the function
     *
     * @return
     */
    @Override
    public double getRightMostX() {
        return 12;
    }

    /**
     * Gets function's value at the specified point
     *
     * @param x x to calculate function value
     * @return
     */
    @Override
    public double getValue(double x) {
        double step = (getRightMostX() - getLeftMostX()) / (getPointCount() - 1);

        int valueIndex = (int) ((x - getLeftMostX()) / step);

        return TEMPERATURE[valueIndex];
    }

    /**
     * Gets the function's point count
     *
     * @return
     */
    @Override
    public int getPointCount() {
        return 12;
    }

    /**
     * Gets the function's color
     *
     * @return
     */
    @Override
    public Color getColor() {
        return Color.RED;
    }

    /**
     * Does any actions the function requires
     */
    @Override
    public void initialize() {

    }

    /**
     * Checks if the function points' shapes are visible
     *
     * @return
     */
    @Override
    public boolean areShapesVisible() {
        return false;
    }

    /**
     * Checks if the function's lines are visible
     *
     * @return
     */
    @Override
    public boolean areLinesVisible() {
        return false;
    }

    /**
     * Checks if only the function's bounds need to be rendered
     *
     * @return
     */
    @Override
    public boolean onlyRenderBounds() {
        return false;
    }
}
