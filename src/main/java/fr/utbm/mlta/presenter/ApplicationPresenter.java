package fr.utbm.mlta.presenter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Application presenter (Model-View-Presenter model)
 * @author Alexandre Lombard
 */
public class ApplicationPresenter implements Initializable {

    // Model attributes
    @Inject private Double[] xData;
    @Inject private Double[] yData;

    // View attributes
    @FXML private LineChart<Double, Double> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}
