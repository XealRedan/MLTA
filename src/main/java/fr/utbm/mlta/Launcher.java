package fr.utbm.mlta;

import com.airhacks.afterburner.injection.Injector;
import fr.utbm.mlta.analysis.ILinearRegression;
import fr.utbm.mlta.analysis.NormalEquationsLinearRegression;
import fr.utbm.mlta.data.DoubleDataFileParser;
import fr.utbm.mlta.data.IDataParser;
import fr.utbm.mlta.view.MainFrameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Launcher class
 */
public class Launcher extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Gets the data from the files
        final IDataParser<Double> parser = new DoubleDataFileParser();
        final Double[] xArray =  parser.parse(Launcher.class.getResourceAsStream("q2x.dat"));
        final Double[] yArray =  parser.parse(Launcher.class.getResourceAsStream("q2y.dat"));

        // Compute regression
        final ILinearRegression linearRegression = new NormalEquationsLinearRegression();
        final double[] normalEquationsResult =
                linearRegression.compute(ArrayUtils.toPrimitive(xArray), ArrayUtils.toPrimitive(yArray));

        // Build the context
        final Map<Object, Object> context = new HashMap<>();
        context.put("dataX", xArray);
        context.put("dataY", yArray);
        context.put("normalEquationsResult", normalEquationsResult);

        Injector.setConfigurationSource(context::get);

        // Build the frame
        final MainFrameView mainFrameView = new MainFrameView();
        final Scene scene = new Scene(mainFrameView.getView());

        primaryStage.setTitle("MLTA");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
