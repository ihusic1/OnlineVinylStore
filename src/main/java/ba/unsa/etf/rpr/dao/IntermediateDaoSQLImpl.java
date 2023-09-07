package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.IntermediateManager;
import ba.unsa.etf.rpr.domain.IntermediateTable;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Ilma Husic
 */

public class IntermediateDaoSQLImpl extends AbstractDao<IntermediateTable> implements IntermediateDao {

    private static IntermediateDaoSQLImpl instance = null;

    private IntermediateDaoSQLImpl() {
        super("IntermediateTable");
    }


    /**
     * @return MedicineDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'Record' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     * @author Ilma Husic
     */
    public static IntermediateDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new IntermediateDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    /**
     * @param rs The result set from the database query
     * @return A intermediate  object with properties set according to the values in the result set
     * @throws RecordException if there is an error when retrieving values from the result set
     */

    @Override
    public IntermediateTable row2object(ResultSet rs) throws RecordException {
        try {
            IntermediateTable q = new IntermediateTable();
            //q.setId(rs.getInt("id"));
            q.setRecord(DaoFactory.recordDao().getById(rs.getInt("recordId")));
            q.setOrder(DaoFactory.orderDao().getById(rs.getInt("orderId")));
            return q;
        } catch (Exception e) {
            throw new RecordException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(IntermediateTable object) {
        Map<String, Object> item = new TreeMap<>();
        //  item.put("id", object.getId());
        item.put("recordId", object.getRecord().getId());
        item.put("orderId", object.getOrder().getId());
        return item;
    }

    /**
     * Fetches all orders from database with given idUser
     *
     * @param idUser int
     * @return List of Records
     * @throws RecordException in case of problems with database
     */
    @Override
    public List<IntermediateTable> getByUser(int idUser) throws RecordException {

        return executeQuery("SELECT IntermediateTable.*\n" +
                "FROM IntermediateTable\n" +
                "INNER JOIN OrderRecord ON IntermediateTable.orderId = OrderRecord.id\n" +
                "WHERE OrderRecord.userId = ?",new Object[]{idUser});
    }

}