package math.interpolation;

import math.function.PolynomialFunctionBuilderInterface;

/**
 * Interpolates a function
 */
public interface InterpolatorInterface {

    /**
     * Performs interpolation
     *
     * @param pointProvider
     */
    void interpolate(InterpolationPointProvider pointProvider);

    /**
     * Gets the interpolated function's expression
     *
     * @return
     */
    String getInterpolatedFunctionExpression();

    /**
     * Gets the interpolated function's builder
     *
     * @return
     */
    PolynomialFunctionBuilderInterface getFunctionBuilder();
}
