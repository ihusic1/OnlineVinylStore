package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.IntermediateManager;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Ilma Husic
 */
public class DaoFactory {

    private static final GenreDao genreDao = GenreDaoSQLImpl.getInstance();

    private static final RecordDao recordDao = RecordDaoSQLImpl.getInstance();

    private static final OrderDao orderDao = OrderDaoSQLImpl.getInstance();

    private static final UserDao userDao = UserDaoSQLImpl.getInstance();

    private static final IntermediateDao intermediateDao = IntermediateDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static GenreDao genreDao(){
        return genreDao;
    }

    public static RecordDao recordDao(){
        return recordDao;
    }

    public static OrderDao orderDao(){
        return orderDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

    public static IntermediateDao intermediateDao(){
        return intermediateDao;
    }

}