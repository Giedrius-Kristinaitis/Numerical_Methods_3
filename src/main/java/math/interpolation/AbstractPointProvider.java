package math.interpolation;

import math.function.FunctionInterface;

/**
 * Point provider abstraction
 */
public abstract class AbstractPointProvider implements InterpolationPointProvider {

    protected FunctionInterface function;
    protected double x1;
    protected double x2;

    /**
     * Sets point interval bounds
     *
     * @param x1 start
     * @param x2 end
     */
    @Override
    public void setBounds(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    /**
     * Function to calculate point y values
     *
     * @param function function
     */
    @Override
    public void setFunction(FunctionInterface function) {
        this.function = function;
    }
}
