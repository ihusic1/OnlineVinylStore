package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

/**
 * MySQL implementation of the DAO
 * @author Ilma Husic
 */
public class GenreDaoSQLImpl extends AbstractDao<Genre> implements GenreDao{
    private static  GenreDaoSQLImpl instance = null;
    private GenreDaoSQLImpl() {
        super("Genres");
    }

    public static GenreDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new GenreDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Genre row2object(ResultSet rs) throws RecordException {
        try {
            Genre cat = new Genre();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            return cat;
        } catch (SQLException e) {
            throw new RecordException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Genre object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        return row;
    }

    @Override
    public List<Genre> searchGenreId(String genreName) throws RecordException
    {
        return executeQuery("SELECT * FROM Genres WHERE name = ?", new Object[]{genreName});
    }
}
