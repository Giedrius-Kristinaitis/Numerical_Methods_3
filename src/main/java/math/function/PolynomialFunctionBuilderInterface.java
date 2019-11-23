package math.function;

import java.awt.*;

/**
 * Creates functions
 */
public interface PolynomialFunctionBuilderInterface {

    /**
     * Sets function title
     *
     * @param title
     * @return
     */
    PolynomialFunctionBuilderInterface setTitle(String title);

    /**
     * Sets left most x
     *
     * @param x
     * @return
     */
    PolynomialFunctionBuilderInterface setLeftMostX(double x);

    /**
     * Sets right most x
     *
     * @param x
     * @return
     */
    PolynomialFunctionBuilderInterface setRightMostX(double x);

    /**
     * Sets point count
     *
     * @param pointCount
     * @return
     */
    PolynomialFunctionBuilderInterface setPointCount(int pointCount);

    /**
     * Sets color
     *
     * @param color
     * @return
     */
    PolynomialFunctionBuilderInterface setColor(Color color);

    /**
     * Adds a polynomial piece a(x - xn[0])(x - xn[1])...(x - xn[n - 1])
     *
     * @param a
     * @param xn
     * @return
     */
    PolynomialFunctionBuilderInterface addPolynomialPiece(double a, double[] xn);

    /**
     * Builds the function
     *
     * @return
     */
    FunctionInterface build();
}
