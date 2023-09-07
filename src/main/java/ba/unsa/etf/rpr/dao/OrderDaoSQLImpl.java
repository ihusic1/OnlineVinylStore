package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.RecordException;
import java.sql.*;
import java.util.*;

public class OrderDaoSQLImpl extends AbstractDao<Order> implements OrderDao {
    private static OrderDaoSQLImpl instance = null;
    private OrderDaoSQLImpl()
    {
        super("OrderRecord");
    }


    /**
     * @author Ilma Husic
     * @return OrderDaoSQLImpl
     * We don't need more than one object for CRUD operations on table 'OrderRecord' so we will use Singleton
     * This method will call private constructor in instance==null and then return that instance
     */
    public static OrderDaoSQLImpl getInstance()
    {
        if(instance==null)
            instance = new OrderDaoSQLImpl();
        return instance;
    }

    public static void removeInstance()
    {
        if(instance!=null)
            instance=null;
    }

    /**
     * @param rs The result set from the database query
     * @return A Order object with properties set according to the values in the result set
     * @throws RecordException if there is an error when retrieving values from the result set
     */
    @Override
    public Order row2object(ResultSet rs) throws RecordException
    {
        try {
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setPayment_amount(rs.getDouble("paymentAmount"));
            // o.setAddress(rs.getString("address"));
            o.setUser(DaoFactory.userDao().getById(rs.getInt("userId")));
            return o;
        } catch (Exception e) {
            throw new RecordException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Order object)
    {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("paymentAmount", object.getPayment_amount());
        // item.put("address", object.getAddress());
        item.put("userId", object.getUser().getId());
        return item;
    }


}
