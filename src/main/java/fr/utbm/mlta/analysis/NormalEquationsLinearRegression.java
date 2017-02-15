package fr.utbm.mlta.analysis;

import org.apache.commons.math3.linear.*;

/**
 * Linear regression implementation based on normal equations
 * @author Alexandre Lombard
 */
public class NormalEquationsLinearRegression implements ILinearRegression {
    @Override
    public double[] compute(double[] x, double[] y) {
        final RealMatrix xMat = MatrixUtils.createColumnRealMatrix(x);
        final RealMatrix yMat = MatrixUtils.createColumnRealMatrix(y);

        final RealMatrix theta =
                new LUDecomposition(xMat.transpose().multiply(xMat)).getSolver().getInverse().multiply(xMat.transpose()).multiply(yMat);

        return theta.getColumnVector(0).toArray();
    }
}
