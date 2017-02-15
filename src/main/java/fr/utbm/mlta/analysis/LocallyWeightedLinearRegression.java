package fr.utbm.mlta.analysis;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Locally weighted linear regression (LWLR) implementation
 * @author Alexandre Lombard
 */
public class LocallyWeightedLinearRegression implements ILinearRegression {

    private double x = 0;
    private double sigma = 0.3;

    public LocallyWeightedLinearRegression() {
        //
    }

    public LocallyWeightedLinearRegression(double x) {
        this.x = x;
    }

    public LocallyWeightedLinearRegression(double x, double sigma) {
        this.x = x;
        this.sigma = sigma;
    }

    @Override
    public double[] compute(double[] x, double[] y) {
        final RealMatrix xMat = MatrixUtils.createColumnRealMatrix(x);
        final RealMatrix yMat = MatrixUtils.createColumnRealMatrix(y);

        // Compute weights
        final double[] weights = new double[x.length];
        for(int idx = 0; idx < weights.length; idx++) {
            weights[idx] = weight(this.x, x[idx], sigma);
        }

        final RealMatrix weightMat = MatrixUtils.createRealDiagonalMatrix(weights);

        final RealMatrix theta =
                new LUDecomposition(xMat.transpose().multiply(weightMat).multiply(xMat)).getSolver().getInverse().multiply(xMat.transpose()).multiply(weightMat).multiply(yMat);

        return theta.getColumnVector(0).toArray();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    private double weight(double v, double input, double tau) {
        return Math.exp(-Math.pow(input - v, 2) / (2 * tau));
    }
}
