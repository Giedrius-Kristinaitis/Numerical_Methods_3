package action;

import math.function.FunctionInterface;

/**
 * Plots a function
 */
public class FunctionPlotAction extends GraphAction {

    /**
     * Default class constructor
     */
    public FunctionPlotAction(FunctionInterface function) {
        super(function);
    }

    /**
     * Gets chart title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return "Funkcijos grafikas";
    }
}
