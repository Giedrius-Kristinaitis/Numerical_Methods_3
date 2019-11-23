package math.interpolation;

import math.Point;

/**
 * Provides interpolation points by even intervals
 */
public class EvenIntervalPointProvider extends AbstractPointProvider {

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
        double pieceLength = (x2 - x1) / POINT_COUNT;

        double x = x1 + pieceLength * index;
        double y = function.getValue(x);

        return new Point(x, y);
    }
}
