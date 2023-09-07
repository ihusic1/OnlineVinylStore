package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.util.List;

public class UserManager {
    public static User findEmail(String emailField) throws RecordException {
        return DaoFactory.userDao().findEmail(emailField);
    }

    public void delete(int UserId) throws RecordException {
        try {
            DaoFactory.userDao().delete(UserId);
        } catch (RecordException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new RecordException("NO");
            }
            throw e;
        }

    }
    public static User add(User u) throws RecordException {
        return DaoFactory.userDao().add(u);
    }

    public User getById(int id) throws RecordException {
        return DaoFactory.userDao().getById(id);
    }

    public User update(User u) throws RecordException {
        return DaoFactory.userDao().update(u);
    }

    public List<User> getAll() throws RecordException {
        return DaoFactory.userDao().getAll();
    }

}