package fr.utbm.mlta.analysis;

/**
 * Linear regression interface
 * @author Alexandre Lombard
 */
public interface ILinearRegression {
    double[] compute(double[] x, double[] y);
}
