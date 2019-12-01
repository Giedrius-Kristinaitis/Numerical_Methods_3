package math.function;

import math.Point;

import java.awt.*;

/**
 * Performs interpolation using Hermite polynomial
 */
public class HermitePolynomial implements FunctionInterface {

    // data
    protected Point[] points;

    /**
     * Sets point data
     *
     * @param points
     */
    public void setPoints(Point[] points) {
        this.points = points;
    }

    /**
     * Gets the function's title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Ermito interpoliacijos funkcija";
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
        int index = 0;

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].x <= x && points[i + 1].x >= x) {
                index = i + 1;
                break;
            } else if (i == points.length - 2) {
                index = points.length - 1;
            }
        }

        if (index == 0 || index == points.length - 1) {
            return points[index].y;
        }

        return (getU(index - 1, index, x) * points[index - 1].y + getV(index - 1, index, x) * getPointDerivativeValue(index - 1))
                + (getU(index, index - 1, x) * points[index].y + getV(index, index - 1, x) * getPointDerivativeValue(index));
    }

    /**
     * Gets U value
     *
     * @return
     */
    protected double getU(int start, int end, double x) {
        return (1 - 2 * getLDerivative(start, end) * (x - points[start].x)) * Math.pow(getL(start, end, x), 2);
    }

    /**
     * Gets V value
     *
     * @return
     */
    protected double getV(int start, int end, double x) {
        return (x - points[start].x) * Math.pow(getL(start, end, x), 2);
    }

    /**
     * Gets L
     *
     * @return
     */
    protected double getL(int start, int end, double x) {
        if (start == 0 || start == points.length - 1 || end == 0 || end == points.length - 1) {
            return 1;
        }

        double divisionResult = (x - points[end].x) / (points[start].x - points[end].x);

        if (!Double.isFinite(divisionResult)) {
            return 1;
        }

        return divisionResult;
    }

    /**
     * Gets L'
     *
     * @return
     */
    protected double getLDerivative(int start, int end) {
        if (start == 0 || start == points.length - 1 || end == 0 || end == points.length - 1) {
            return 1;
        }

        double divisionResult = 1 / (points[start].x - points[end].x);

        if (!Double.isFinite(divisionResult)) {
            return 1;
        }

        return divisionResult;
    }

    /**
     * Gets point derivative value
     *
     * @param pointIndex
     * @return
     */
    protected double getPointDerivativeValue(int pointIndex) {
        if (pointIndex == 0) {
            return (points[pointIndex + 2].y - points[pointIndex].y) / (points[pointIndex + 2].x - points[pointIndex].x);
        } else if (pointIndex >= points.length - 1) {
            return (points[pointIndex].y - points[pointIndex - 2].y) / (points[pointIndex].x - points[pointIndex - 2].x);
        }

        return (points[pointIndex + 1].y - points[pointIndex - 1].y) / (points[pointIndex + 1].x - points[pointIndex - 1].x);
    }

    /**
     * Gets the function's point count
     *
     * @return
     */
    @Override
    public int getPointCount() {
        return 200;
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
        return true;
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
