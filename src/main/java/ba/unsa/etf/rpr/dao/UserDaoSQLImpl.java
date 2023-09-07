package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.sql.*;
import java.util.*;

/**
 * MySQL's implementation of the DAO
 * @author Ilma Husic
 */

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {
    private static UserDaoSQLImpl instance = null;

    /**
     * Private constructor for the UserDaoSQLImpl class.
     */
    private UserDaoSQLImpl() {
        super("User");
    }


    /**
     * @author Ilma Husic
     * @return UserDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'User' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static UserDaoSQLImpl getInstance()
    {
        if(instance==null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance!=null)
            instance=null;
    }

    /**
     * Maps a row from the result set to a User object
     * @param rs The result set from the database query
     * @return A User object with properties set according to the values in the result set
     * @throws RecordException if there is an error when retrieving values from the result set
     */
    @Override
    public User row2object(ResultSet rs) throws RecordException
    {
        try {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setFirstName(rs.getString("firstname"));
            u.setLastName(rs.getString("lastname"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setPhone_number(rs.getInt("phone"));
            u.setAddress(rs.getString("address"));
            return u;
        } catch (Exception e) {
            throw new RecordException(e.getMessage(), e);
        }
    }

    /**
     * Takes in a User object and converts it into a Map of String keys and Object values.
     * @param object - a bean object for specific table
     * @return A Map containing the fields of the User object as key-value pairs.
     */
    @Override
    public Map<String, Object> object2row(User object)
    {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("firstname", object.getFirstName());
        item.put("lastname", object.getLastName());
        item.put("password", object.getPassword());
        item.put("email", object.getEmail());
        item.put("phone", object.getPhone_number());
        item.put("address", object.getAddress());
        return item;
    }

    /**
     * Checks if email already exists
     * @param emailField String
     * @throws RecordException in case of problems with database
     */
    @Override
    public User findEmail(String emailField) throws RecordException{
        String insert = "SELECT id FROM User where email=" + emailField;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return getById(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
