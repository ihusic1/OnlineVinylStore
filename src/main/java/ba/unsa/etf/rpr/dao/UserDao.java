package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;

/**
 * Dao interface for Genre domain bean
 * @author Ilma Husic
 */
public interface UserDao extends Dao<User> {
    User findEmail(String emailField) throws RecordException;

}