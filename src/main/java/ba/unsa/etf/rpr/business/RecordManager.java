package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.util.List;

/**
 * Business Logic Layer for Records
 *
 * @author Ilma Husic
 */
public class RecordManager {

    public List<Record> getAll() throws RecordException
    {
        return DaoFactory.recordDao().getAll();
    }

    public  List<Record> searchRecord(String text) throws RecordException
    {
        return DaoFactory.recordDao().searchByText(text);
    }

    public void delete(int id) throws RecordException
    {
        DaoFactory.recordDao().delete(id);
    }

    public Record getById(int id) throws RecordException
    {
        return DaoFactory.recordDao().getById(id);
    }

    public void update(Record r) throws RecordException
    {
        DaoFactory.recordDao().update(r);
    }

    public Record add(Record r) throws RecordException
    {
        return DaoFactory.recordDao().add(r);
    }

    public List<Record> searchByGenre(Genre genre) throws RecordException{
        return DaoFactory.recordDao().searchByGenre(genre);
    }


}