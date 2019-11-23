package math.interpolation;

import math.Point;

/**
 * Provides interpolation points
 */
public class AbscissaPointProvider extends AbstractPointProvider {

    /**
     * Gets the total point count
     *
     * @return
     */
    @Override
    public int getPointCount() {
        return POINT_COUNT;
    }

    /**
     * Gets the specified point
     *
     * @param index point index
     * @return
     */
    @Override
    public Point getPoint(int index) {
        double x = ((x2 - x1) / 2) * Math.cos((Math.PI * (2 * index + 1)) / (2 * POINT_COUNT)) + ((x2 + x1) / 2);
        double y = function.getValue(x);

        return new Point(x, y);
    }
}
