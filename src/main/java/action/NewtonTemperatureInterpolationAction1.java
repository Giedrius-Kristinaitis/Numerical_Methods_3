package action;

import math.function.DifferenceFunction;
import math.function.FunctionInterface;
import math.function.InterpolationPointFunction;
import math.function.PolynomialFunctionBuilderInterface;
import math.interpolation.EvenIntervalPointProvider;
import math.interpolation.InterpolationPointProvider;
import math.interpolation.InterpolatorInterface;
import math.interpolation.NewtonBaseInterpolator;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interpolates temperature data
 */
@SuppressWarnings("Duplicates")
public class NewtonTemperatureInterpolationAction1 extends GraphAction {

    protected FunctionInterface interpolationFunction;
    protected EvenIntervalPointProvider equalIntervalPointProvider;

    /**
     * Class constructor
     *
     * @param function
     */
    public NewtonTemperatureInterpolationAction1(FunctionInterface function) {
        this.interpolationFunction = function;

        equalIntervalPointProvider = new EvenIntervalPointProvider();
        equalIntervalPointProvider.setFunction(function);
        equalIntervalPointProvider.setBounds(function.getLeftMostX(), function.getRightMostX());
        equalIntervalPointProvider.setPointCountFunction(function);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("*************** INTERPOLIAVIMAS DAUGIANARIU (Niutono bazine funkcija) TEMPERATUROS KIPRAS 2004 ***************");

        InterpolatorInterface interpolator = interpolate(equalIntervalPointProvider);

        // process results
        System.out.println("Interpoliavimo tasku skaicius: " + equalIntervalPointProvider.getPointCount());
        System.out.println("Interpoliacines funkcijos israiska: " + interpolator.getInterpolatedFunctionExpression());

        PolynomialFunctionBuilderInterface builder = interpolator.getFunctionBuilder();
        builder.setColor(Color.RED)
                .setTitle("Interpoliavimo funkcija")
                .setLeftMostX(interpolationFunction.getLeftMostX())
                .setRightMostX(interpolationFunction.getRightMostX())
                .setColor(Color.RED)
                .setPointCount(200);

        FunctionInterface polynomial = builder.build();

        functions.add(polynomial);
        functions.add(new InterpolationPointFunction(equalIntervalPointProvider));

        super.actionPerformed(event);
    }

    /**
     * Performs interpolation
     */
    protected InterpolatorInterface interpolate(InterpolationPointProvider pointProvider) {
        NewtonBaseInterpolator interpolator = new NewtonBaseInterpolator();

        interpolator.interpolate(pointProvider);

        return interpolator;
    }

    /**
     * Gets chart title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Interpoliavimas daugianariu (Niutono bazine funkcija) temperaturos Kipras 2004";
    }
}
