package math.function;

import math.interpolation.InterpolationPointProvider;

import java.awt.*;

/**
 * Displays interpolation points
 */
public class InterpolationPointFunction implements FunctionInterface {

    protected InterpolationPointProvider pointProvider;
    protected int pointIndex;

    /**
     * Class constructor
     *
     * @param pointProvider
     */
    public InterpolationPointFunction(InterpolationPointProvider pointProvider) {
        this.pointProvider = pointProvider;
    }

    /**
     * Gets the function's title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Interpoliavimo taskai";
    }

    /**
     * Gets the right-most x of the function
     *
     * @return
     */
    @Override
    public double getLeftMostX() {
        // this is not important
        return 0;
    }

    /**
     * Gets the left-most x of the function
     *
     * @return
     */
    @Override
    public double getRightMostX() {
        // this is not important
        return 0;
    }

    /**
     * Gets function's value at the specified point
     *
     * @param x x to calculate function value
     * @return
     */
    @Override
    public double getValue(double x) {
        if (pointIndex == pointProvider.getPointCount()) {
            pointIndex = 0;
        }

        return pointProvider.getPoint(pointIndex++).y;
    }

    /**
     * Checks if distances between x values are even
     *
     * @return
     */
    @Override
    public boolean notEvenXIntervals() {
        return true;
    }

    /**
     * Gets the specified x value (only works if x distances are not even)
     *
     * @return
     */
    @Override
    public double getXValue() {
        return pointProvider.getPoint(pointIndex).x;
    }

    /**
     * Gets the function's point count
     *
     * @return
     */
    @Override
    public int getPointCount() {
        return pointProvider.getPointCount();
    }

    /**
     * Gets the function's color
     *
     * @return
     */
    @Override
    public Color getColor() {
        return Color.BLACK;
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
        return true;
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
