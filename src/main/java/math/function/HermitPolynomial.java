package math.function;

import math.Point;

import java.awt.*;

public class HermitPolynomial implements FunctionInterface {

    // data
    protected FunctionInterface function;
    protected Point[] points;

    /**
     * Sets interpolation function
     *
     * @param function
     */
    public void setFunction(FunctionInterface function) {
        this.function = function;
    }

    /**
     * Sets point data
     *
     * @param points
     */
    public void setPoints(Point[] points) {
        this.points = new Point[points.length + 2];

        this.points[0] = new Point(0, 9);
        this.points[points.length + 1] = new Point(13, 9);

        for (int i = 0; i < points.length; i++) {
            this.points[i + 1] = points[i];
        }
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
        double value = 0;

        for (int j = 0; j < points.length; j++) {
            value += getU(j, x) * points[j].y + getV(j, x) * getPointDerivativeValue(j);
        }

        return value;
    }

    /**
     * Gets U value
     *
     * @param i
     * @return
     */
    protected double getU(int i, double x) {
        return (1 - 2 * getLDerivative(i) * (x - points[i].x)) * Math.pow(getL(i, x), 2);
    }

    /**
     * Gets V value
     *
     * @param i
     * @return
     */
    protected double getV(int i, double x) {
        return (x - points[i].x) * Math.pow(getL(i, x), 2);
    }

    /**
     * Gets L
     *
     * @param i
     * @return
     */
    protected double getL(int i, double x) {
        double value = 1;

        for (int j = 0; j < points.length; j++) {
            if (i == j) {
                continue;
            }

            value *= (x - points[j].x) / (points[i].x - points[j].x);
        }

        return value;
    }

    /**
     * Gets L'
     *
     * @param i
     * @return
     */
    protected double getLDerivative(int i) {
        double value = 0;

        for (int j = 0; j < points.length; j++) {
            if (i == j) {
                continue;
            }

            value += 1 / (points[i].x - points[j].x);
        }

        return value;
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
