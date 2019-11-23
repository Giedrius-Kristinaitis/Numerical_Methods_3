package math.interpolation;

import math.Point;
import math.function.FunctionInterface;

/**
 * Provides interpolation points
 */
public interface InterpolationPointProvider {

    int POINT_COUNT = 8;

    /**
     * Gets the total point count
     * @return
     */
    int getPointCount();

    /**
     * Gets the specified point
     *
     * @param index point index
     * @return
     */
    Point getPoint(int index);

    /**
     * Sets point interval bounds
     *
     * @param x1 start
     * @param x2 end
     */
    void setBounds(double x1, double x2);

    /**
     * Function to calculate point y values
     *
     * @param function function
     */
    void setFunction(FunctionInterface function);
}
