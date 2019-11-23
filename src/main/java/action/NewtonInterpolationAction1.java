package action;

import math.function.DifferenceFunction;
import math.function.FunctionInterface;
import math.function.InterpolationPointFunction;
import math.function.PolynomialFunctionBuilderInterface;
import math.interpolation.*;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Performs interpolation
 */
@SuppressWarnings("Duplicates")
public class NewtonInterpolationAction1 extends GraphAction {

    protected FunctionInterface interpolationFunction;
    protected InterpolationPointProvider equalIntervalPointProvider;

    /**
     * Class constructor
     *
     * @param function
     */
    public NewtonInterpolationAction1(FunctionInterface function) {
        this.interpolationFunction = function;

        equalIntervalPointProvider = new EvenIntervalPointProvider();
        equalIntervalPointProvider.setFunction(function);
        equalIntervalPointProvider.setBounds(function.getLeftMostX(), function.getRightMostX());
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("*************** INTERPOLIAVIMAS DAUGIANARIU (Niutono bazine funkcija) TASKAI PASISKIRSTE TOLYGIAI ***************");

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
                .setPointCount(interpolationFunction.getPointCount());

        FunctionInterface polynomial = builder.build();

        functions.add(polynomial);
        functions.add(interpolationFunction);
        functions.add(new DifferenceFunction(interpolationFunction, polynomial));
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
        return "Interpoliavimas daugianariu (Niutono bazine funkcija) taskai pasiskirste tolygiai";
    }
}
