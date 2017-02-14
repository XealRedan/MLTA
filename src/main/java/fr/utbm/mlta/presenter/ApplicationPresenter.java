package fr.utbm.mlta.presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Application presenter (Model-View-Presenter model)
 * @author Alexandre Lombard
 */
public class ApplicationPresenter implements Initializable {

    // Model attributes
    @Inject private Double[] dataX;
    @Inject private Double[] dataY;

    // View attributes
    @FXML private ScatterChart<Double, Double> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Perform linear regression
        // TODO

        // Plot data
        final XYChart.Series<Double, Double> raw = new XYChart.Series<>();
        raw.setName("Input");

        for(int idx = 0; idx < dataX.length; idx++) {
            raw.getData().add(new XYChart.Data<>(dataX[idx], dataY[idx]));
        }

        chart.getData().add(raw);
    }
}
