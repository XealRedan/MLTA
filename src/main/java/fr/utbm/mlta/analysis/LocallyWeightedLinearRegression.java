package fr.utbm.mlta.analysis;

/**
 * Locally weighted linear regression (LWLR) implementation
 * @author Alexandre Lombard
 */
public class LocallyWeightedLinearRegression implements ILinearRegression {
    @Override
    public double[] compute(double[] x, double[] y) {
        double theta = 0;

        for(int idx = 0; idx < x.length; idx++) {

        }

        return new double[0];
    }

    private double weight(double v, double input, double tau) {
        return Math.exp(-Math.pow(input - v, 2) / (2 * tau));
    }
}
