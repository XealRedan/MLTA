package fr.utbm.mlta.presenter;

import fr.utbm.mlta.analysis.LocallyWeightedLinearRegression;
import fr.utbm.mlta.analysis.NormalEquationsLinearRegression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Application presenter (Model-View-Presenter model)
 * @author Alexandre Lombard
 */
public class ApplicationPresenter implements Initializable {

    // Model attributes
    @Inject private double[] dataX;
    @Inject private double[] dataY;

    // View attributes
    @FXML private Slider xSlider;
    @FXML private Slider sigmaSlider;
    @FXML private XYChart<Double, Double> chart;

    // MV (local) elements
    private final XYChart.Series<Double, Double> normalEquations = new XYChart.Series<>();
    private final XYChart.Series<Double, Double> locallyWeighted = new XYChart.Series<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Compute regressions
        refreshNormalEquationsSeries();
        refreshLocallyWeightedLinearRegressionSeries();

        // Plot raw data
        final XYChart.Series<Double, Double> raw = new XYChart.Series<>();
        raw.setName("Input");

        for(int idx = 0; idx < dataX.length; idx++) {
            raw.getData().add(new XYChart.Data<>(dataX[idx], dataY[idx]));
        }

        chart.getData().add(raw);

        // Plot normal equations result
        normalEquations.setName("Normal equations LR");

        chart.getData().add(normalEquations);

        // Plot LWLR results
        locallyWeighted.setName("Locally weighted LR");

        chart.getData().add(locallyWeighted);
    }

    /**
     * Refresh the LWLR
     */
    public void refreshNormalEquationsSeries() {
        // Compute regression
        double[] normalEquationsResult = computeNormalEquationsLinearRegression();

        // Update series
        normalEquations.getData().clear();

        final ObservableList<XYChart.Data<Double, Double>> data = FXCollections.observableArrayList();

        for (double aDataX : dataX) {
            data.add(new XYChart.Data<>(aDataX, normalEquationsResult[0] * aDataX));
        }

        normalEquations.setData(data);
    }

    /**
     * Refresh the LWLR
     */
    public void refreshLocallyWeightedLinearRegressionSeries() {
        // Compute regression
        double[] locallyWeightedLinearRegressionResult = computeLocallyWeightedLinearRegression(
                xSlider.getValue(), sigmaSlider.getValue());

        // Update series
        this.locallyWeighted.getData().clear();

        final ObservableList<XYChart.Data<Double, Double>> data = FXCollections.observableArrayList();

        for (double aDataX : dataX) {
            data.add(new XYChart.Data<>(aDataX, locallyWeightedLinearRegressionResult[0] * aDataX));
        }

        locallyWeighted.setData(data);
    }

    private double[] computeNormalEquationsLinearRegression() {
        return new NormalEquationsLinearRegression().compute(dataX, dataY);
    }

    private double[] computeLocallyWeightedLinearRegression(double x, double tau) {
        return new LocallyWeightedLinearRegression(x, tau).compute(dataX, dataY);
    }
}
