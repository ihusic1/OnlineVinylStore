package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.util.List;

/**
 * Dao interface for Record domain bean
 *
 * @author Ilma Husic
 */
public interface RecordDao extends Dao<Record>{

    /**
     * Returns all records that contains given text.
     *
     * @param text search string for records
     * @return list of records
     */
    List<Record> searchByText(String text) throws RecordException;

    /**
     * Returns all records that contains given text.
     *
     * @param genre search string for records
     * @return list of records
     */
    List<Record> searchByGenre(Genre genre) throws RecordException;


}
