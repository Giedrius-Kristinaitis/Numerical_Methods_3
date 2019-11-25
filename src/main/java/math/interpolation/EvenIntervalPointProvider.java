package math.interpolation;

import math.Point;
import math.function.FunctionInterface;

/**
 * Provides interpolation points by even intervals
 */
public class EvenIntervalPointProvider extends AbstractPointProvider {

    // function to get points from
    protected FunctionInterface pointCountFunction;

    /**
     * Sets the function to get points from
     *
     * @param function function
     */
    public void setPointCountFunction(FunctionInterface function) {
        this.pointCountFunction = function;
    }

    /**
     * Gets the total point count
     *
     * @return
     */
    @Override
    public int getPointCount() {
        return pointCountFunction == null ? POINT_COUNT : pointCountFunction.getPointCount();
    }

    /**
     * Gets the specified point
     *
     * @param index point index
     * @return
     */
    @Override
    public Point getPoint(int index) {
        double pieceLength = (x2 - x1) / (getPointCount() - 1);

        double x = x1 + pieceLength * index;
        double y = function.getValue(x);

        return new Point(x, y);
    }
}
