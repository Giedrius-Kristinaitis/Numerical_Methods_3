package math.function;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Creates functions
 */
public class PolynomialFunctionBuilder implements PolynomialFunctionBuilderInterface {

    // buildable function
    protected Function function;

    /**
     * Class constructor
     */
    public PolynomialFunctionBuilder() {
        function = new Function();
    }

    /**
     * Sets function title
     *
     * @param title
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface setTitle(String title) {
        function.title = title;
        return this;
    }

    /**
     * Sets left most x
     *
     * @param x
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface setLeftMostX(double x) {
        function.x1 = x;
        return this;
    }

    /**
     * Sets right most x
     *
     * @param x
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface setRightMostX(double x) {
        function.x2 = x;
        return this;
    }

    /**
     * Sets point count
     *
     * @param pointCount
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface setPointCount(int pointCount) {
        function.pointCount = pointCount;
        return this;
    }

    /**
     * Sets color
     *
     * @param color
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface setColor(Color color) {
        function.color = color;
        return this;
    }

    /**
     * Adds a polynomial piece a(x - xn[0])(x - xn[1])...(x - xn[n - 1])
     *
     * @param a
     * @param xn
     * @return
     */
    @Override
    public PolynomialFunctionBuilderInterface addPolynomialPiece(double a, double[] xn) {
        function.addPolynomialPiece(a, xn);
        return this;
    }

    /**
     * Builds the function
     *
     * @return
     */
    @Override
    public FunctionInterface build() {
        FunctionInterface function = this.function;

        this.function = new Function();

        return function;
    }

    /**
     * Buildable function
     */
    protected class Function implements FunctionInterface {

        protected String title;
        protected Color color;
        protected double x1;
        protected double x2;
        protected int pointCount;
        protected List<PolynomialPiece> pieces = new ArrayList<PolynomialPiece>();

        /**
         * Gets the function's title
         *
         * @return
         */
        @Override
        public String getTitle() {
            return title;
        }

        /**
         * Gets the right-most x of the function
         *
         * @return
         */
        @Override
        public double getLeftMostX() {
            return x1;
        }

        /**
         * Gets the left-most x of the function
         *
         * @return
         */
        @Override
        public double getRightMostX() {
            return x2;
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

            for (PolynomialPiece piece : pieces) {
                value += piece.getValue(x);
            }

            return value;
        }

        /**
         * Gets the function's point count
         *
         * @return
         */
        @Override
        public int getPointCount() {
            return pointCount;
        }

        /**
         * Gets the function's color
         *
         * @return
         */
        @Override
        public Color getColor() {
            return color;
        }

        /**
         * Does any actions the function requires
         */
        @Override
        public void initialize() {
            // nothing to do here
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

        /**
         * Adds a polynomial piece a(x - xn[0])(x - xn[1])...(x - xn[n - 1])
         *
         * @param a
         * @param xn
         */
        public void addPolynomialPiece(double a, double[] xn) {
            pieces.add(new PolynomialPiece(a, xn));
        }

        // Polynomial piece a(x - xn[0])(x - xn[1])...(x - xn[n - 1])
        protected class PolynomialPiece {

            protected double a;
            protected double[] xn;

            /**
             * Class constructor
             *
             * @param a
             * @param xn
             */
            protected PolynomialPiece(double a, double[] xn) {
                this.a = a;
                this.xn = xn;
            }

            /**
             * Gets the value of the piece
             *
             * @return
             */
            protected double getValue(double x) {
                double value = a;

                for (double xi : xn) {
                    value *= x - xi;
                }

                return value;
            }
        }
    }
}
