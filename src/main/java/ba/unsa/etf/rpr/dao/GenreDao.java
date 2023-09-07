package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.util.List;

/**
 * Dao interface for Genre domain bean
 *
 * @author Ilma Husic
 */

public interface GenreDao extends Dao<Genre>{
    List<Genre> searchGenreId(String genreName) throws RecordException;
}
