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
public class NewtonInterpolationAction2 extends GraphAction {

    protected FunctionInterface interpolationFunction;
    protected InterpolationPointProvider abscissaPointProvider;

    /**
     * Class constructor
     *
     * @param function
     */
    public NewtonInterpolationAction2(FunctionInterface function) {
        this.interpolationFunction = function;

        abscissaPointProvider = new AbscissaPointProvider();
        abscissaPointProvider.setFunction(function);
        abscissaPointProvider.setBounds(function.getLeftMostX(), function.getRightMostX());
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        functions.clear();

        System.out.println("*************** INTERPOLIAVIMAS DAUGIANARIU (Niutono bazine funkcija) TASKAI APSKAICIUOTI NAUDOJANT CIOBYSEVO ABSCISES ***************");

        InterpolatorInterface interpolator = interpolate(abscissaPointProvider);

        // process results
        System.out.println("Interpoliavimo tasku skaicius: " + abscissaPointProvider.getPointCount());
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
        functions.add(new InterpolationPointFunction(abscissaPointProvider));

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
        return "Interpoliavimas daugianariu (Niutono bazine funkcija) taskai apskaiciuoti naudojant Ciobysevo abscises";
    }
}