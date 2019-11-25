package math.interpolation;

import math.Point;
import math.function.NewtonPolynomialFunctionBuilder;
import math.function.PolynomialFunctionBuilderInterface;

/**
 * Performs interpolation using Newton's base function
 */
public class NewtonBaseInterpolator implements InterpolatorInterface {

    protected String expression;
    protected PolynomialFunctionBuilderInterface functionBuilder;

    /**
     * Performs interpolation
     *
     * @param pointProvider
     */
    @Override
    public void interpolate(InterpolationPointProvider pointProvider) {
        /**
         * WARNING: THE FOLLOWING CODE MIGHT CAUSE BRAIN DAMAGE
         *
         * READER DISCRETION IS ADVISED
         */

        // create some builders
        functionBuilder = new NewtonPolynomialFunctionBuilder();
        StringBuilder polynomialExpression = new StringBuilder();

        // perform interpolation
        Point firstPoint = pointProvider.getPoint(0);

        double a = firstPoint.y;
        double[] prevY = new double[pointProvider.getPointCount() - 1];
        double[] prevX = new double[pointProvider.getPointCount() - 1];
        prevX[0] = firstPoint.x;
        prevY[0] = firstPoint.y;

        polynomialExpression.append(String.format("%.2f", a));
        polynomialExpression.append(" + ");

        functionBuilder.addPolynomialPiece(a, new double[] {});

        for (int i = 1; i < pointProvider.getPointCount(); i++) {
            Point point = pointProvider.getPoint(i);

            a = 0;

            // calculate coefficient a
            for (int k = 0; k <= i; k++) {
                double x = k == i ? point.x : prevX[k];
                double y = k == i ? point.y : prevY[k];

                double bottom = 1;

                for (int c = 0; c <= i; c++) {
                    if (k == c) {
                        continue;
                    }

                    double xi = c == i ? point.x : prevX[c];

                    bottom *= x - xi;
                }

                a += y / bottom;
            }

            polynomialExpression.append(String.format("%.2f", a));

            double[] xn = new double[i];

            for (int j = 0; j < i; j++) {
                polynomialExpression.append("(x - ");
                polynomialExpression.append(String.format("%.2f", prevX[j]));
                polynomialExpression.append(")");
                xn[j] = prevX[j];
            }

            if (i != pointProvider.getPointCount() - 1) {
                polynomialExpression.append(" + ");
                prevX[i] = point.x;
                prevY[i] = point.y;
            }

            functionBuilder.addPolynomialPiece(a, xn);
        }

        // extract results
        expression = polynomialExpression.toString();
    }

    /**
     * Gets the interpolated function's expression
     *
     * @return
     */
    @Override
    public String getInterpolatedFunctionExpression() {
        return expression;
    }

    /**
     * Gets the interpolated function's builder
     *
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface getFunctionBuilder() {
        return functionBuilder;
    }
}
