package edu.csula.datascience.aquisition;

import java.util.Collection;
import java.util.List;

public interface Collector<T, R> {
    /**
     * Mungee method is to clean data. e.g. remove data rows with errors
     */
	
    Collection<T> mungee(Collection<R> src);

    void save(String path , String mongoDBCollection);

	List<SimpleModel> mungee(Object next);
}
