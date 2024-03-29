package action;

import math.Point;
import math.function.HermitePolynomial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Draws a country border using Hermite's spline interpolation
 */
public class HermiteCountryBorderAction extends GraphAction {

    // x coordinate data
    protected double[] x = {
            33.782, 33.781, 33.780, 33.776, 33.771, 33.769, 33.765, 33.759, 33.761, 33.766, 33.774, 33.778, 33.783, 33.786, 33.791, 33.794, 33.793, 33.788, 33.785, 33.782, 33.743, 33.740, 33.739, 33.740, 33.741, 33.746, 33.752, 33.752, 33.747, 33.743, 33.703, 33.702, 33.680, 33.654, 33.647, 33.640, 33.635, 33.633, 33.635, 33.633, 33.623, 33.617, 33.610, 33.606, 33.590, 33.557, 33.544, 33.535, 33.525, 33.513, 33.500, 33.468, 33.439, 33.415, 33.400, 33.399, 33.371, 33.297, 33.282, 33.273, 33.260, 33.246, 33.174, 33.102, 33.086, 33.072, 33.026, 33.016, 32.991, 32.970, 32.963, 32.956, 32.954, 32.966, 32.97, 32.9900, 32.988, 32.955, 32.942, 32.936, 32.93, 32.917, 32.914, 32.885, 32.866, 32.86, 32.86, 32.854, 32.846, 32.841, 32.836, 32.832, 32.827, 32.823, 32.819, 32.806, 32.799, 32.779, 32.767, 32.76, 32.761, 32.749, 32.707, 32.666, 32.626, 32.575, 32.557, 32.536, 32.493, 32.476, 32.459, 32.419, 32.413, 32.407, 32.403, 32.402, 32.379, 32.368, 32.347, 32.338, 32.329, 32.323, 32.323, 32.331, 32.312, 32.31, 32.31, 32.306, 32.284, 32.276, 32.272, 32.275, 32.287, 32.307, 32.337, 32.353, 32.372, 32.414, 32.45, 32.48, 32.502, 32.517, 32.528, 32.543, 32.545, 32.548, 32.554, 32.559, 32.566, 32.568, 32.575, 32.585, 32.593, 32.607, 32.622, 32.634, 32.646, 32.653, 32.66, 32.692, 32.698, 32.704, 32.713, 32.728, 32.755, 32.775, 32.796, 32.807, 32.81, 32.81, 32.816, 32.826, 32.835, 32.861, 32.873, 32.877, 32.883, 32.891, 32.9, 32.906, 32.922, 32.935, 32.948, 32.967, 32.985, 33.003, 33.026, 33.037, 33.047, 33.072, 33.098, 33.123, 33.137, 33.143, 33.15, 33.174, 33.189, 33.203, 33.216, 33.229, 33.239, 33.252, 33.262, 33.279, 33.301, 33.309, 33.314, 33.315, 33.328, 33.348, 33.357, 33.373, 33.399, 33.416, 33.421, 33.421, 33.425, 33.432, 33.443, 33.457, 33.459, 33.46, 33.459, 33.459, 33.453, 33.446, 33.449, 33.459, 33.47, 33.486, 33.493, 33.497, 33.503, 33.51, 33.52, 33.527, 33.527, 33.534, 33.546, 33.564, 33.572, 33.581, 33.592, 33.603, 33.612, 33.62, 33.63, 33.64, 33.662, 33.674, 33.685, 33.701, 33.703, 33.898, 33.911, 33.921, 33.931, 33.945, 33.962, 33.98, 33.989, 34.002, 34.012, 34.017, 34.022, 34.036, 34.051, 34.062, 34.073, 34.087, 34.099, 34.085, 34.059, 34.035, 34.023, 34.012, 33.957, 33.91, 33.9, 33.892, 33.864, 33.846, 33.838, 33.85, 33.859, 33.859, 33.84, 33.829, 33.83, 33.822, 33.799, 33.804, 33.814, 33.827, 33.835, 33.841, 33.855, 33.868, 33.875, 33.88, 33.898
    };

    // y coordinate data
    protected double[] y = {
            34.976, 34.976, 34.979, 34.980, 34.980, 34.984, 34.986, 34.989, 34.990, 34.990, 34.989, 34.985, 34.985, 34.985, 34.982, 34.978, 34.978, 34.977, 34.976, 34.976, 35.001, 35.003, 35.005, 35.010, 35.014, 35.015, 35.014, 35.004, 35.003, 35.001, 34.988, 34.973, 34.966, 34.945, 34.932, 34.916, 34.898, 34.880, 34.861, 34.852, 34.849, 34.845, 34.827, 34.822, 34.818, 34.818, 34.815, 34.808, 34.796, 34.785, 34.781, 34.774, 34.763, 34.758, 34.773, 34.745, 34.731, 34.718, 34.712, 34.707, 34.701, 34.698, 34.705, 34.698, 34.695, 34.687, 34.651, 34.634, 34.634, 34.632, 34.625, 34.629, 34.643, 34.652, 34.646, 34.646, 34.673, 34.677, 34.667, 34.658, 34.662, 34.667, 34.66, 34.667, 34.679, 34.688, 34.701, 34.699, 34.699, 34.699, 34.7, 34.701, 34.697, 34.691, 34.688, 34.686, 34.671, 34.673, 34.676, 34.669, 34.653, 34.65, 34.645, 34.652, 34.666, 34.693, 34.699, 34.704, 34.707, 34.713, 34.722, 34.748, 34.753, 34.782, 34.787, 34.796, 34.849, 34.857, 34.869, 34.876, 34.886, 34.898, 34.911, 34.924, 34.95, 34.961, 34.983, 35.001, 35.032, 35.048, 35.073, 35.091, 35.097, 35.086, 35.059, 35.048, 35.041, 35.043, 35.059, 35.083, 35.109, 35.14, 35.157, 35.166, 35.171, 35.176, 35.178, 35.177, 35.173, 35.172, 35.171, 35.173, 35.161, 35.154, 35.152, 35.154, 35.161, 35.174, 35.187, 35.184, 35.157, 35.144, 35.134, 35.127, 35.12, 35.11, 35.103, 35.095, 35.08, 35.071, 35.063, 35.059, 35.059, 35.065, 35.072, 35.077, 35.088, 35.092, 35.088, 35.081, 35.072, 35.072, 35.079, 35.095, 35.109, 35.116, 35.128, 35.136, 35.138, 35.135, 35.131, 35.131, 35.136, 35.14, 35.149, 35.163, 35.163, 35.158, 35.154, 35.153, 35.149, 35.137, 35.126, 35.118, 35.114, 35.116, 35.121, 35.129, 35.137, 35.155, 35.157, 35.156, 35.158, 35.16, 35.157, 35.145, 35.13, 35.124, 35.114, 35.101, 35.095, 35.078, 35.061, 35.042, 35.019, 35.001, 34.988, 34.978, 34.978, 34.982, 34.997, 35.026, 35.031, 35.037, 35.044, 35.037, 35.013, 35, 34.995, 35.001, 35.014, 35.02, 35.018, 35.012, 35.014, 35.017, 35.014, 35.01, 35, 34.999, 34.997, 34.989, 34.988, 35.061, 35.057, 35.053, 35.051, 35.051, 35.057, 35.057, 35.052, 35.047, 35.049, 35.052, 35.057, 35.047, 35.033, 35.006, 34.988, 34.972, 34.965, 34.964, 34.967, 34.972, 34.983, 34.988, 34.982, 34.97, 34.965, 34.958, 34.963, 34.96, 34.964, 34.973, 34.975, 34.992, 34.993, 35.001, 35.023, 35.03, 35.039, 35.05, 35.056, 35.061, 35.057, 35.051, 35.053, 35.06, 35.068, 35.073, 35.061
    };

    // how many points are used for interpolation
    protected int interpolationPointCount;

    /**
     * Class constructor
     *
     * @param interpolationPointCount
     */
    public HermiteCountryBorderAction(int interpolationPointCount) {
        this.interpolationPointCount = interpolationPointCount;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        List<Point> points = new ArrayList<Point>();

        interpolate(points);

        setPointData(
                points,
                "Kipro konturas",
                Color.RED,
                false,
                true,
                34.7,
                35.1,
                32.3,
                34.1);

        super.actionPerformed(event);
    }

    /**
     * Interpolates country border and fills point list with data
     *
     * @param points
     */
    protected void interpolate(List<Point> points) {
        Point[] xy = extractPointData();
        double[] params = getParams(xy.length);

        Point[] tx = extractTxPointData(xy, params);
        Point[] ty = extractTyPointData(xy, params);

        HermitePolynomial polyX = new HermitePolynomial();
        polyX.setPoints(tx);

        HermitePolynomial polyY = new HermitePolynomial();
        polyY.setPoints(ty);

        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                double t = i + (j / 100D);
                points.add(new Point(polyX.getValue(t), polyY.getValue(t)));
            }
        }
    }

    /**
     * Extracts (t, x)[] data
     *
     * @param xy
     * @param params
     * @return
     */
    protected Point[] extractTxPointData(Point[] xy, double[] params) {
        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < xy.length; i++) {
            points.add(new Point(params[i], xy[i].x));
        }

        Object[] array = points.toArray();

        return Arrays.copyOf(array, array.length, Point[].class);
    }

    /**
     * Extracts (t, y)[] data
     *
     * @param xy
     * @param params
     * @return
     */
    protected Point[] extractTyPointData(Point[] xy, double[] params) {
        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < xy.length; i++) {
            points.add(new Point(params[i], xy[i].y));
        }

        Object[] array = points.toArray();

        return Arrays.copyOf(array, array.length, Point[].class);
    }

    /**
     * Gets t[] param array
     *
     * @return
     */
    protected double[] getParams(int length) {
        double[] params = new double[length];
        int value = 1;

        for (int i = 0; i < length; i++) {
            params[i] = value++;
        }

        return params;
    }

    /**
     * Extracts point data from x and y arrays
     *
     * @return
     */
    protected Point[] extractPointData() {
        List<Point> points = new ArrayList<Point>();

        int step = x.length / interpolationPointCount;

        for (int i = 0; i < x.length; i += step) {
            if (i >= y.length) {
                continue;
            }

            points.add(new Point(x[i], y[i]));
        }

        Object[] array = points.toArray();

        return Arrays.copyOf(array, array.length, Point[].class);
    }

    /**
     * Gets chart title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Kipro konturas";
    }
}
