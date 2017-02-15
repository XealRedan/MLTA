package fr.utbm.mlta.analysis;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Locally weighted linear regression (LWLR) implementation
 * @author Alexandre Lombard
 */
public class LocallyWeightedLinearRegression implements ILinearRegression {

    private double tau = 0.3;

    public LocallyWeightedLinearRegression() {
        //
    }

    public LocallyWeightedLinearRegression(double tau) {
        this.tau = tau;
    }

    @Override
    public double[] compute(double[] x, double[] y) {
        final RealMatrix xMat = MatrixUtils.createColumnRealMatrix(x);
        final RealMatrix yMat = MatrixUtils.createColumnRealMatrix(y);

        // Compute weights
        final double[] weights = new double[x.length];
        for(int idx = 0; idx < weights.length; idx++) {
            weights[idx] = weight(0, x[idx], tau);
        }

        final RealMatrix weightMat = MatrixUtils.createRealDiagonalMatrix(weights);

        final RealMatrix theta =
                new LUDecomposition(xMat.transpose().multiply(weightMat).multiply(xMat)).getSolver().getInverse().multiply(xMat.transpose()).multiply(weightMat).multiply(yMat);

        return theta.getColumnVector(0).toArray();
    }

    public double getTau() {
        return tau;
    }

    public void setTau(double tau) {
        this.tau = tau;
    }

    private double weight(double v, double input, double tau) {
        return Math.exp(-Math.pow(input - v, 2) / (2 * tau));
    }
}
