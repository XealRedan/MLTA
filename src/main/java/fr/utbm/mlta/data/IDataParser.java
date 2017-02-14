package fr.utbm.mlta.data;

import java.io.IOException;
import java.io.InputStream;

/**
 * Data parser interface
 * @author Alexandre Lombard
 */
public interface IDataParser<T> {
    /**
     * Returns the dataset from the given stream
     * @param is the input stream
     * @return the dataset as a T vector
     * @throws IOException thrown on file access issue
     */
    T[] parse(InputStream is) throws IOException;
}
