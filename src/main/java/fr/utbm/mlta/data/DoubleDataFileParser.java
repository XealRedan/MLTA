package fr.utbm.mlta.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data parser, creates dataset vector from a file
 * @author Alexandre Lombard
 */
public class DoubleDataFileParser implements IDataParser<Double> {

    public DoubleDataFileParser() {
        //
    }

    @Override
    public Double[] parse(InputStream is) throws IOException {
        final List<Double> data = new ArrayList<>();

        try(final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while((line = br.readLine()) != null) {
                data.add(Double.parseDouble(line));
            }
        }

        return data.toArray(new Double[data.size()]);
    }

}
