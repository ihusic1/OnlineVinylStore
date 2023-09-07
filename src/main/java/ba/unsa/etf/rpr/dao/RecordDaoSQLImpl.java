package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Ilma Husic
 */

public class RecordDaoSQLImpl extends AbstractDao<Record> implements RecordDao {

    private static RecordDaoSQLImpl instance = null;
    private RecordDaoSQLImpl()
    {
        super("Records");
    }

    /**
     * @author Ilma Husic
     * @return MedicineDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'Records' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static RecordDaoSQLImpl getInstance()
    {
        if(instance==null)
            instance = new RecordDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance!=null)
            instance=null;
    }


    /**
     * @param rs The result set from the database query
     * @return A Record object with properties set according to the values in the result set
     * @throws RecordException if there is an error when retrieving values from the result set
     */
    @Override
    public Record row2object(ResultSet rs) throws RecordException
    {
        try {
            Record r = new Record();
            r.setId(rs.getInt("id"));
            r.setTitle(rs.getString("title"));
            r.setArtist(rs.getString("artist"));
            r.setPrice(rs.getDouble("price"));
            r.setGenre(DaoFactory.genreDao().getById(rs.getInt("genresId")));
            return r;
        } catch (Exception e) {
            throw new RecordException(e.getMessage(), e);
        }
    }


    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Record object)
    {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("title", object.getTitle());
        item.put("artist", object.getArtist());
        item.put("price", object.getPrice());
        item.put("genresId", object.getGenre().getId());
        return item;
    }



    /**
     * @param text search string for record
     * @return list of records
     * @author Ilma Husic
     */
    @Override
    public List<Record> searchByText(String text) throws RecordException
    {
        return executeQuery("SELECT * FROM Records WHERE title LIKE concat('%', ?, '%')", new Object[]{text});
    }

    /**
     * @param genre search string for record
     * @return list of records
     * @author Ilma Husic
     */
    @Override
    public List<Record> searchByGenre(Genre genre) throws RecordException
    {
        return executeQuery("SELECT * FROM Records WHERE genresId = ?", new Object[]{genre.getId()});
    }

}
